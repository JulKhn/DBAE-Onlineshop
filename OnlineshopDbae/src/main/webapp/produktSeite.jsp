<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Details</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>
<!-- Klickt der Benutzer auf die Details eines Produkts, so wird das gewaehlte Produkt im Vollbild angezeigt -->
      
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Gewähltes Produkt</h2></caption>
            <tr>
                <th>Produktname</th>
                <th>Groesse</th>
                <th>Farbe</th>
                <th>Menge</th>
                <th>Preis</th>
            </tr>
            <tr>
            		<td><c:out value="${aktuellesProdukt.name}"/></td>
                    <td><c:out value="${aktuellesProdukt.groesse}" /></td>
                    <td><c:out value="${aktuellesProdukt.farbe}" /></td>
                    <td><c:out value="${aktuellesProdukt.menge}" /></td>
                    <td><c:out value="${aktuellesProdukt.preis}" /></td>
                    
                    <td><form method="POST" action="MenuServlet">
						Menge (Min. 1): <input type="number" name="produktMenge" min="1" max="${produkt.menge}" required="required" value="${ inputMenge }"> <br />
					<button name="warenkorbHinzu" value="${produkt.produktid}">Warenkorb</button>
					</form></td>
            </tr>
		</table>
		<c:choose>
			<c:when test="${bildID == 2}">
			<img src="bilder/iPhoneSEred.jpg" alt="iPhone">
			</c:when>
			
			<c:when test="${bildID == 3}">
			<img src="bilder/EastpakSpringer.jpg" alt="eastpak">
			</c:when>
			
			<c:when test="${bildID == 4}">
			<img src="bilder/CasioTaschenrechner.jpg" alt="rechner">
			</c:when>
			
			<c:when test="${bildID == 5}">
			<img src="bilder/ReishungerReiskocher.jpg" alt="reis">
			</c:when>
			
			<c:otherwise>
				Kein Bild vorhanden
			</c:otherwise>
		</c:choose>
		
	</div>
</body>
</html>