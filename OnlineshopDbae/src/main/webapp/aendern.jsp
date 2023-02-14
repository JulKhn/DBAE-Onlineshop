<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Mail Ändern</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div align="center">
<h1>E-Mail ändern:</h1>

<!-- Nutzer gibt seine neue E-Mail ein, welche er verwenden moechte -->

<form method="POST" action="AendernServlet">
	Bitte geben Sie Ihre neue E-Mail ein: <input type="email" name="email" required="required" value="${ inputEmail }"> <br />
	<button class="btn btn-primary" type="submit">Absenden!</button>
</form>
</div>
</body>
</html>