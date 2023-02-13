<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bestellungen</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div align="center">
	<c:choose>
		<c:when test="${verlaufInhalt}">
	
			<table class="table table-hover" border="1" cellpadding="5">
            <caption><h2>Letzte Bestellungen</h2></caption>
            <tr>
                <th>Produktname</th>
                <th>Preis</th>
                <th>Farbe</th>
                <th>Größe</th>
                <th>Menge</th>
                <th>Datum</th>
            </tr>
            <c:forEach var="bestellungen" items="${bestelltListe}">
                <tr>
                    <td><c:out value="${bestellungen.name}" /></td>
                    <td><c:out value="${bestellungen.preis}" /></td>
                    <td><c:out value="${bestellungen.farbe}" /></td>
                    <td><c:out value="${bestellungen.groesse}" /></td>
                    <td><c:out value="${bestellungen.menge}" /></td>
                    <td><c:out value="${bestellungen.datum}" /></td>
                    <td> <form method="POST" action="ProduktDetailsServlet">
                    	<button class="btn btn-primary" name="details" value="${bestellungen.id}">Details</button>
                    </form></td>
                </tr>
            </c:forEach>
        </table>
		</c:when>
		<c:otherwise>
		<caption><h2>Sie haben noch keine Bestellung aufgegeben</h2></caption>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>