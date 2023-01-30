<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ihr Konto</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div align="center">
<h1>Ihre persönlichen Daten:</h1>
<br />
<table>
<!-- Alle Kundendaten werden einfach dargestellt. Wohnort und Guthaben koennen veraendert werden und es kann ein neues Produkt angeboten werden -->
<tr>
<td>Name: ${ konto.getVorname() } ${ konto.getNachname() }<td>
<tr>
<tr>
<td>E-Mail Adresse: ${ konto.getEmail() }<td> <a href=email.jsp>(E-Mail Ändern)</a><td> 
<tr>
<tr>
<td>Geburtsdatum: ${ konto.getGeburtsdatum() }<td>
<tr>
<tr>
<td>IBAN: ${ konto.getIban() }<td> 
<tr>
<tr>
<td>Kontostand: ${ konto.getKontostand() } <a href=guthaben.jsp>(hier Geld aufladen!)</a><td> 
<tr>
</table>
</body>
</html>