<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Mail ändern</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div align="center">
<h1>E-Mail ändern:</h1>

<!-- Der Benutzer muss sein Passwort fuer die Validierung eingeben -->
Bitte geben Sie ihr Passwort ein:
<br />
<form method="POST" action="EmailServlet">
	Passwort: <input type="password" name="passwort" required="required" value="${ inputPasswort }"> <br />
	<button class="btn btn-primary" type="submit">Absenden!</button>
</form>
</div>
</body>
</html>