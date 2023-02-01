<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Geld aufladen:</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div align="center">
<h1>Guthaben aufladen:</h1>
<!-- Benutzer gibt den gewuenschten Betrag ein, welchen er aufladen moechte -->

<form method="POST" action="AufladenServlet">
	Bitte geben Sie den Betrag ein den Sie aufladen möchten: <input type="number" name="geld" required="required" value="${ inputGeld }"> <br />
	<input type="submit" value="Absenden!">
</form>
</div>
</body>
</html>