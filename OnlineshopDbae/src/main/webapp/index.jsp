<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hauptmenu - ShopHIt</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div align="center">
	<h1>ShopHIt</h1>
	<form method="POST" action="LoginServlet">
		<h2>Login <br />
		---
		</h2>
		E-Mail: <input type="email" name="email"> <br /> 
		Passwort: <input type="password" name="passwort"> <br /> 
		<button class="btn btn-primary" type="submit">Absenden!</button>
	</form>
	${ error }
	${ erfolg }
</div>
</body>
</html>