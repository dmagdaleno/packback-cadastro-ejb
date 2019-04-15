package br.com.boomerang.packback.cadastro.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.boomerang.packback.cadastro.domain.Usuario;
import br.com.boomerang.packback.cadastro.domain.builder.UsuarioBuilder;
import br.com.boomerang.packback.cadastro.service.UsuarioService;

@WebServlet(value = "/usuarios")
public class UsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private UsuarioService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		service = (UsuarioService) req.getSession().getAttribute("service");

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
			Usuario usuario = service.buscaPor(id);
			req.setAttribute("usuario", usuario);
		} catch (Exception e) {
			req.setAttribute("erro", "Não foi possível carregar dados para edição.");
		}
		req.getRequestDispatcher("templates/edicao/peso.jsp").forward(req, resp);
	}

	private void listar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			Collection<Usuario> usuarios = service.listar();
			req.setAttribute("usuarios", usuarios);
		} catch (Exception e) {
			req.setAttribute("erro", "Não foi possível carregar os registros.");
		}
		req.getRequestDispatcher("templates/lista/peso.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		service = (UsuarioService) req.getSession().getAttribute("service");

		try {
			String acao = req.getParameter("acao");

			if(acao == null)
				throw new IllegalArgumentException("Ação não pode ser nula");

			switch (acao) {
				case "cadastrar":
					cadastrar(req, resp);
					break;

				case "editar":
					editar(req, resp);
					break;

				case "excluir":
					excluir(req, resp);
					break;

				default:
					break;
			}
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}

	}

	private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			Usuario usuario = constroiUsuario(req);

			service.cadastrar(usuario);

			req.setAttribute("sucesso", "Cadastro realizado com sucesso!");
			listar(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível realizar o cadastro.");
			req.getRequestDispatcher("templates/cadastro/usuario.jsp").forward(req, resp);
		}

	}

	private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Usuario usuario = constroiUsuario(req);

			service.atualizar(usuario);

			req.setAttribute("sucesso", "Registro editado com sucesso!");
			listar(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível editar o registro.");
			req.getRequestDispatcher("templates/edicao/peso.jsp").forward(req, resp);
		}
	}

	private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Long id = Long.parseLong(req.getParameter("id"));

			service.excluir(id);

			req.setAttribute("sucesso", "Registro excluído com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível excluir o registro.");
		}

		listar(req, resp);
	}

	private Usuario constroiUsuario(HttpServletRequest req) {
		Long id = Long.parseLong(req.getParameter("id"));
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		String cpf = req.getParameter("cpf");
		String cnpj = req.getParameter("cnpj");
		String razaoSocial = req.getParameter("razaoSocial");

		return new UsuarioBuilder().comId(id).comNome(nome).comCpf(cpf).comCnpj(cnpj).comEmail(email).comRazaoSocial(razaoSocial).constroi();
	}
}