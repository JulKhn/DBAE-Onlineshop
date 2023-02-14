<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrierung</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div align="center">
<h1>Registrierung</h1>
<form method="POST" action="RegistrierungsServlet">
		Vorname: <input type="text" name="vorname" required="required" value="${ inputVorname }"> <br /> 
		Nachname: <input type="text" name="nachname" required="required" value="${ inputNachname }"> <br /> 
		Geburtsdatum: <input type="date" name="geburtsdatum" required="required" value="${ inputGeburtsdatum }"> <br /> 
		E-Mail: <input type="email" name="email" required="required" value="${ inputEmail }"> <br /> 
		Passwort: <input type="password" name="passwort" required="required"> <br /> 
		Passwort (Wiederholung): <input type="password" name="passwortWDH" required="required"> <br /> 
		IBAN: <input type="text" name="iban" required="required" value="${ inputIban }"> <br />
		<button class="btn btn-primary" type="submit">Absenden!</button>
	</form>
<!-- Sollten eingaben fehlen, dann erhaelt der Nutzer eine Fehlermeldung -->
	${ error }
</div>
</body>
</html>