package br.com.boomerang.packback.cadastro.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.boomerang.packback.cadastro.dao.UsuarioDAO;
import br.com.boomerang.packback.cadastro.domain.Usuario;

@Stateless
public class UsuarioService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<String> pesquisar(String termo) {
		List<String> resultado = new ArrayList<>();
		
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		
		Collection<Usuario> usuarios = dao.buscaPorNome(termo);
		
		for (Usuario usuario : usuarios) {
			resultado.add(usuario.getNome() + " (" + usuario.getTipo() + ")");
		}
		return resultado;
	}

	public Usuario buscaPor(Long id) {
		UsuarioDAO dao = new UsuarioDAO(entityManager);

		return dao.buscar(id);
	}

	public Collection<Usuario> listar() {
		UsuarioDAO dao = new UsuarioDAO(entityManager);

		return dao.listar();
	}
}