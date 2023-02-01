<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hauptmenü</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
${ erfolg }
<br />

     
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Produktübersicht</h2></caption>
            <tr>
                <th>Produktname</th>
                <th>Preis</th>
                <th>Farbe</th>
                <th>Größe</th>
                <th>Menge</th>
            </tr>
            <c:forEach var="produkte" items="${prodListe}">
                <tr>
                    <td><c:out value="${produkte.name}" /></td>
                    <td><c:out value="${produkte.preis}" /></td>
                    <td><c:out value="${produkte.farbe}" /></td>
                    <td><c:out value="${produkte.groesse}" /></td>
                    <td><c:out value="${produkte.menge}" /></td>
                    <td> <form method="POST" action="ProduktDetailsServlet">
                    	<button name="details" value="${produkte.id}">Details</button>
                    </form></td>
                    <td> <form method="POST" action="MenuServlet">
                    	Menge (Min. 1): <input type="number" name="produktMenge" min="1" max="${produkte.menge}" required="required" value="${ inputMenge }"> <br />
                    	<button name="warenkorbHinzu" value="${produkte.id}">Warenkorb</button>
                    </form></td>
                    
                </tr>
            </c:forEach>
        </table>
	</div>
</body>
</html>