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
			
			se.getSession().setAttribute("historicoService", ic.lookup("java:module/HistoricoPesquisasService"));
			
			ContadorPesquisasService contadorService = (ContadorPesquisasService) ic.lookup("java:module/ContadorPesquisasService");
			
			contadorService.novoUsuario();
			
			se.getSession().setAttribute("contadorService", contadorService);
			  
		} catch (NamingException e) {
		    e.printStackTrace();
		}
	}
	
	public void sessionDestroyed(HttpSessionEvent se) {
		ContadorPesquisasService contadorService = (ContadorPesquisasService) se.getSession().getAttribute("contadorService");
		
		contadorService.usuarioSaiu();
	}
}