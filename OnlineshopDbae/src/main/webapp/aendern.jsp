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

<h1>E-Mail ändern:</h1>
<div align="center">
<!-- Benutzer gibt seine neue E-Mail ein, welche er verwenden moechte -->

<form method="POST" action="AendernServlet">
	Bitte geben Sie Ihre neue E-Mail ein: <input type="number" name="email" required="required" value="${ inputEmail }"> <br />
	<input type="submit" value="Absenden!">
</form>
</div>
</body>
</html>