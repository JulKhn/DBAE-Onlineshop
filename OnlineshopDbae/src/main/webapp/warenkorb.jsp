<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Warenkorb</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>

<!-- Wenn der Benutzer Produkte im Warenkorb hat, dann werden diese hier angezeigt. -->
<c:choose>
<c:when test="${not leer}">
	<div align="center">
        <table class="table table-hover" border="1" cellpadding="5">
            <caption><h2>Ihr Warenkorb:</h2></caption>
            <tr>
                <th>Produktname</th>
                <th>Groesse</th>
                <th>Farbe</th>
                <th>Menge</th>
                <th>Preis</th>
            </tr>
            <c:forEach items="${warenKorb}" var="warenkorb">
			<tr>
			<td><c:out value="${warenkorb.produkt.name}" /></td>
			<td><c:out value="${warenkorb.produkt.groesse}" /></td>
			<td><c:out value="${warenkorb.produkt.farbe}" /></td>
			<td><c:out value="${warenkorb.menge}" /></td>
			<td><c:out value="${warenkorb.menge * warenkorb.produkt.preis}" /></td>
			<td><form method="POST" action="WarenkorbServlet">
				<button class="btn btn-primary" name="produktloeschen" value="${warenkorb.produkt.id}">Löschen</button>
				</form></td>
			</tr>
			</c:forEach>
        </table>
        <h3>Gesamtsumme</h3>
        <c:set var="total" value="${0}"/>
        <c:forEach var="summe" items="${warenKorb}">
    		<c:set var="total" value="${total + (summe.produkt.preis * summe.menge)}" />
		</c:forEach>
		Summe: ${total}
        <form method="POST" action="kasse.jsp">
			<button class="btn btn-primary" name="produktKaufen" value="${total}">Zur Kasse</button>
        </form>
    </div>
   </c:when>
   <c:otherwise>
   		<div align="center">
   			<caption><h2>Ihr Warenkorb ist leer!</h2></caption>
   		</div>
   </c:otherwise>
</c:choose>
   ${ error }
   ${ erfolg }
</body>
</html>