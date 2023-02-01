<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kasse</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<h1>�bersicht:</h1>
<!-- alle Produkte die im Warenkorb liegen werden ausgegeben -->
<div align="center">
        <table border="1" cellpadding="5">
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
			<button name="kaufAbschluss" value="${total}">Jetzt kaufen</button>
        </form>
</div>
</body>
</html>