package br.com.boomerang.packback.cadastro.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.boomerang.packback.cadastro.HistoricoPesquisasService;
import br.com.boomerang.packback.cadastro.domain.Usuario;
import br.com.boomerang.packback.cadastro.service.UsuarioService;

@WebServlet(value = "/usuarios")
public class UsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private UsuarioService usuarioService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		usuarioService = (UsuarioService) req.getSession().getAttribute("usuarioService");

		try {
			String acao = req.getParameter("acao");

			if(acao == null)
				throw new IllegalArgumentException("Ação não pode ser nula");

			switch (acao) {
				case "listar":
					listar(req, resp);
					break;

				case "editar":
					abrirFormularioEdicao(req, resp);
					break;

				default:
					break;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
	}

	private void abrirFormularioEdicao(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		try {
			Usuario usuario = usuarioService.buscaPor(id);
			req.setAttribute("usuario", usuario);
		} catch (Exception e) {
			req.setAttribute("erro", "Não foi possível carregar dados para edição.");
		}
		req.getRequestDispatcher("templates/edicao/peso.jsp").forward(req, resp);
	}

	private void listar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			Collection<Usuario> usuarios = usuarioService.listar();
			req.setAttribute("usuarios", usuarios);
		} catch (Exception e) {
			req.setAttribute("erro", "Não foi possível carregar os registros.");
		}
		req.getRequestDispatcher("templates/lista/peso.jsp").forward(req, resp);
	}
}