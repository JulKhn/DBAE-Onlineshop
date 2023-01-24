<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hauptmenu</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<header>ShopHIt</header>
<div align="center">
	<form method="POST" action="LoginServlet">
		<h2>Login <br />
		_
		</h2>
		E-Mail: <input type="email" name="email"> <br /> 
		Passwort: <input type="password" name="passwort"> <br /> 
		<input type="submit" value="Absenden!">
	</form>
	${ error }
</div>
</body>
</html>