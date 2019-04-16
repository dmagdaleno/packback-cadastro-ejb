package br.com.boomerang.packback.cadastro;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class PackbackCadastroListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		try {
			InitialContext ic =  new InitialContext();
			
			se.getSession().setAttribute("usuarioService", ic.lookup("java:module/UsuarioService"));
			  
		} catch (NamingException e) {
		    e.printStackTrace();
		}
	}
	
	public void sessionDestroyed(HttpSessionEvent se) {
	}
}