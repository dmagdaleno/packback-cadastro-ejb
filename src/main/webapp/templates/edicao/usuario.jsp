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
      <h1>Editar Peso</h1>
      
      <c:if test="${not empty sucesso }">
		<div class="alert alert-success">${sucesso}</div>
	  </c:if>
	  <c:if test="${not empty erro }">
		<div class="alert alert-danger">${erro}</div>
	  </c:if>
	  
      <form id="pesoForm" action="${pageContext.servletContext.contextPath}/usuarios" method="post">
        
        <div class="form-group">
          <label for="email">E-mail</label>
          <input type="email" id="email" name="email" class="form-control" placeholder="E-mail" value="${usuario.email}">
        </div>
        
        <div class="form-group">
          <label for="nome">Nome</label>
          <input type="text" id="nome" name="nome" class="form-control" placeholder="Nome" value="${usuario.nome}">
        </div>
        
        <div class="form-group">
          <label for="nome">Razão Social</label>
          <input type="text" id="razaoSocial" name="razaoSocial" class="form-control" placeholder="Razão Social" value="${usuario.razaoSocial}">
        </div>
        
        <div class="form-group">
          <label for="nome">CPF</label>
          <input type="text" id="cpf" name="cpf" class="form-control" placeholder="CPF" value="${usuario.cpf}">
        </div>
        
        <div class="form-group">
          <label for="nome">CNPJ</label>
          <input type="text" id="cnpj" name="cnpj" class="form-control" placeholder="CNPJ" value="${usuario.cnpj}">
        </div>
        
        <input type="hidden" name="id" value="${usuario.id}" />
        <input type="hidden" name="acao" value="editar" />
        
        <button type="submit" class="btn btn-primary">Salvar</button>
        <a href="${pageContext.servletContext.contextPath}/usuarios" class="btn btn-danger">Cancelar</a>
        
      </form>
    </div>

    <%@ include file="../footer.jsp" %>
    
  </body>
</html>
