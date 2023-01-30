<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Guthaben aufladen</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div align="center">
<h1>Guthaben aufladen:</h1>

<!-- Der Benutzer muss IBAN und Passwort fuer die Validierung eingeben -->
Bitte geben Sie ihre IBAN und ihr Passwort ein:
<br />
<form method="POST" action="GuthabenServlet">
	IBAN: <input type="text" name="iban" required="required" value="${ inputIBAN }"> <br />
	Passwort: <input type="password" name="passwort" required="required" value="${ inputPasswort }"> <br />
	<input type="submit" value="Absenden!"> 
</form>
</div>
</body>
</html>