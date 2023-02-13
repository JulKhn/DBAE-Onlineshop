<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>IBAN ändern</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div align="center">
	<h1>IBAN ändern:</h1>
	
	Bitte geben Sie ihre neue IBAN ein: <br />
	
	<form method="POST" action="IbanServlet">
		Neue IBAN: <input type="text" name="iban" required="required" value="${ inputIBAN }"> <br />
		<button class="btn btn-primary" type="submit">IBAN ändern</button>
	</form>

</div>
</body>
</html>