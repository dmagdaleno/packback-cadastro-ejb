<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!Doctype html>
<html lang="pt">
  <head>
    <title>PackBack - Usuários</title>

    <%@ include file="../header.jsp" %>
    
  </head>
  <body>
    
    <jsp:include page="../menu.jsp">
    	<jsp:param name="menu" value="1"/>
    </jsp:include>

    <div class="container">
      <h1>Usuários</h1>
      
      <c:if test="${not empty sucesso }">
		<div class="alert alert-success">${sucesso}</div>
	  </c:if>
	  <c:if test="${not empty erro }">
		<div class="alert alert-danger">${erro}</div>
	  </c:if>
	  
      <div class="table-responsive">
        <table class="table">
          <thead>
            <tr>
              <th scope="col">Tipo</th>
              <th scope="col">Email</th>
              <th scope="col">Nome</th>
              <th scope="col">Razão Social</th>
              <th scope="col">CPF</th>
              <th scope="col">CNPJ</th>
              <th scope="col" colspan="2" class="center">Ações</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${usuarios}" var="usuario">
	          <tr>
	            <td>${usuario.tipo}</td>
	            
	            <td>${usuario.email}</td>
	            
	            <td>${usuario.nome}</td>
	            
	            <td>${usuario.razaoSocial}</td>
	            
	            <td>${usuario.cpf}</td>
	            
	            <td>${usuario.cnpj}</td>
                
                <td class="center">
                  <c:url value="usuarios" var="link">
                    <c:param name="acao" value="editar"/>
                    <c:param name="id" value="${usuario.id}"/>
                  </c:url>
                  <a href="${link}" class="btn btn-primary btn-xs" title="Alterar registro">Editar</a>
                </td>
                
                <td class="center">
		          <form id="usuarioForm" action="${pageContext.servletContext.contextPath}/usuarios" method="post">
	          		<input type="hidden" name="acao" value="excluir" />
	          		<input type="hidden" name="id" value="${usuario.id}" />
	                <button type="submit" class="btn btn-danger btn-xs">Excluir</button>
		          </form>
                </td>
                
	          </tr>
	        </c:forEach>
          </tbody>
        </table>
      </div>
      
      <a href="${pageContext.servletContext.contextPath}/templates/cadastro/usuario.jsp">
        <button type="button" class="btn btn-primary">Adicionar</button>
      </a>
    </div>

    <%@ include file="../footer.jsp" %>
    
  </body>
</html>
