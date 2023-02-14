<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kasse</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<!-- alle Produkte die im Warenkorb liegen werden ausgegeben -->
<div align="center">
<h1>Übersicht:</h1>
        <table class="table table-hover" border="1">
            <caption><h2>Ihre Produkte:</h2></caption>
            <tr>
                <th>Produktname</th>
                <th>Preis</th>
                <th>Menge</th>
            </tr>
            <c:forEach items="${warenKorb}" var="warenkorb">
                <tr>
                    <td><c:out value="${warenkorb.produkt.name}" /></td>
                    <td><c:out value="${warenkorb.menge * warenkorb.produkt.preis}" /></td>
                    <td><c:out value="${warenkorb.menge}" /></td>
                </tr>
            </c:forEach>
        </table>
        <h3>Gesamtsumme</h3>
        <c:set var="total" value="${0}"/>
        <c:forEach var="summe" items="${warenKorb}">
    		<c:set var="total" value="${total + (summe.produkt.preis * summe.menge)}" />
		</c:forEach>
		Summe: ${total}
        <form method="POST" action="KasseServlet">
			<button class="btn btn-primary" name="kaufAbschluss" value="${total}">Jetzt kaufen</button>
        </form>
        ${error}
        <c:if test="${fehler}">
        	<div align="center">
        		<a href=guthaben.jsp>Geld aufladen</a>
        	</div>
        </c:if>
</div>
</body>
</html>