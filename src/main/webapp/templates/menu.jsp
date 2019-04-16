<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">

  <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/usuarios">PackBack</a>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
      	<c:set var="url" value="${pageContext.servletContext.contextPath}/usuarios" />
      	<c:set var="desc" value="Usuarios" />
      	<a class="nav-link active" href="${url}">${desc}<span class="sr-only">(atual)</span></a>
      </li>
    </ul>
  </div>
  
</nav>