package br.com.boomerang.packback.cadastro.dao;

import java.util.Collection;

import javax.persistence.EntityManager;

import br.com.boomerang.packback.cadastro.domain.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Long> {

	public UsuarioDAO(EntityManager em) {
		super(em);
	}
	
	public Collection<Usuario> buscaPorNome(String nome) {
		return em.createQuery("from Usuario u where u.nome like :nome", Usuario.class)
				.setParameter("nome", "%"+nome+"%")
				.getResultList();
	}

}
