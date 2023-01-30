<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div align="center">
<%@ include file="navbar.jsp" %>
<form method="POST" action="LoginServlet">
		E-Mail: <input type="email" name="email"> <br /> 
		Passwort: <input type="password" name="passwort"> <br /> 
		<input type="submit" value="Absenden!">
	</form>
	${ error }
</div>
</body>
</html>
