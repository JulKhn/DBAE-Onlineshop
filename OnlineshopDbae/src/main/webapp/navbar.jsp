<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Navbar</title>
</head>
<body>
<!-- Ist der Benutzer eingeloggt, so sieht er diese Navbar -->
<!-- Ist der Benutzer noch nicht eingeloggt, so erhaelt er einen Hinweis fuer die Registrierung oder den Login  -->
<c:choose>
	<c:when test="${loginbool}">
	<nav class="navbar navbar-default">
	  <div class="container">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			<span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
  		<a class="navbar-brand" href="index.jsp">ShopHIt</a>
  		</div>
  		<!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li class="active"><a href="#">Hallo, ${benutzer.vorname} ${benutzer.nachname}!<span class="sr-only">(current)</span></a></li>
	      	<li class="active"><a href="meinKonto.jsp">Mein Konto<span class="sr-only">(current)</span></a></li>
	        <li class="active"><a href="menu.jsp">Menü<span class="sr-only">(current)</span></a></li>
	        <li class="active"><a href="warenkorb.jsp">Warenkorb<span class="sr-only">(current)</span></a></li>
    	 </ul>
    	  <form method="POST" action="LogoutServlet">
		  <input type="submit" value="Logout!">
		  </form>
  		</div>
  		</div><!-- /.navbar-collapse -->
	</nav>
	</c:when>
	<c:otherwise>
	<nav class="navbar navbar-default">
	  	<div class="container">
	    	<!-- Brand and toggle get grouped for better mobile display -->
	    	<div class="navbar-header">
	      		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
	        	<span class="icon-bar"></span>
	        	<span class="icon-bar"></span>
	        	<span class="icon-bar"></span>
	      		</button>
  				<a class="navbar-brand" href="index.jsp">ShopHIt</a>
  			</div>
  		<!-- Collect the nav links, forms, and other content for toggling -->
	    	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li class="active"><a href="#">Bitte einloggen oder registrieren!<span class="sr-only">(current)</span></a></li>
	      	<li class="active"><a href="index.jsp">Login<span class="sr-only">(current)</span></a></li>
	        <li class="active"><a href="registrierung.jsp">Registrierung<span class="sr-only">(current)</span></a></li>
	        <li class="active"><a href="#">Über uns<span class="sr-only">(current)</span></a></li>
    	 </ul>
  		</div>
  		</div><!-- /.navbar-collapse -->
	</nav>
	<c:if test="${not loginbool}">
	</c:if>
	</c:otherwise>
</c:choose>
	
	
<script src="js/jquery-3.6.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>