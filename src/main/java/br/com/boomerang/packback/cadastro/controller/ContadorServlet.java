package br.com.boomerang.packback.cadastro.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.boomerang.packback.cadastro.ContadorPesquisasService;

@WebServlet(value = "/contador")
public class ContadorServlet extends HttpServlet {

	/**
	 * serialVersionUID = 1L
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ContadorPesquisasService contadorService = (ContadorPesquisasService) req.getSession().getAttribute("contadorService");
		
		req.setAttribute("usuarios", contadorService.getUsuarios());
		req.setAttribute("pesquisas", contadorService.getPesquisas());
		
		RequestDispatcher dispacher = req.getRequestDispatcher("contador.jsp");
		
		dispacher.forward(req, resp);
	}
}