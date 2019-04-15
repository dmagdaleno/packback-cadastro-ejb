package br.com.boomerang.packback.cadastro;

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
		List<String> resultado = new ArrayList<String>();
		
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		
		Collection<Usuario> usuarios = dao.buscaPorNome(termo); 
//				this.entityManager.createQuery("select u from usuario u where u.nome like :nome", Usuario.class)
//				.setParameter("nome", "%"+termo+"%").getResultList();
		
		for (Usuario usuario : usuarios) {
			resultado.add(usuario.getNome() + " (" + usuario.getTipo() + ")");
		}
		return resultado;
	}
}