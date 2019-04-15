package br.com.boomerang.packback.cadastro.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.boomerang.packback.cadastro.HistoricoPesquisasService;
import br.com.boomerang.packback.cadastro.UsuarioService;

@WebServlet(value = "/usuarios")
public class UsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UsuarioService usuarioService = (UsuarioService) req.getSession().getAttribute("usuarioService");
		
		HistoricoPesquisasService historicoService = (HistoricoPesquisasService) req.getSession().getAttribute("historicoService");
		
		String termo = req.getParameter("termo");
		
		this.pesquisar(termo, req, usuarioService);
		this.registrarHistorico(termo, req, historicoService);
		
		RequestDispatcher dispacher = req.getRequestDispatcher("usuarios.jsp");
		dispacher.forward(req, resp);
	}
	
	private void pesquisar(String termo, HttpServletRequest req, UsuarioService usuarioService) {
		List<String> resultadoPesquisa = usuarioService.pesquisar(termo);
		
		StringBuilder sbResultado = new StringBuilder();
		
		for (String resultado : resultadoPesquisa) 
			sbResultado.append("<li>" + resultado + "</li>");
		
		req.setAttribute("termo", termo);
		req.setAttribute("encontrados", resultadoPesquisa.size());
		req.setAttribute("resultado", sbResultado.toString());
	}
	
	private void registrarHistorico(String termo, HttpServletRequest req, HistoricoPesquisasService historicoService) {
		historicoService.registrarPesquisa(termo);
		
		Set<String> pesquisas = historicoService.getHistorico();
		
		StringBuilder sbHistorico = new StringBuilder();
		
		for (String pesquisa : pesquisas) 
			sbHistorico.append("<li><i>" + pesquisa + "</i></li>");
		
		req.setAttribute("historico", sbHistorico);
	}
}