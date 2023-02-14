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
<!-- Klickt der Nutzer auf die Details eines Produkts, so wird das gewaehlte Produkt im Vollbild angezeigt -->
      
    <div align="center">
		
		<div class="container">
  			<caption><h2>Gewähltes Produkt</h2></caption>
  			<div class="card" style="width:400px">
  			<c:choose>
			<c:when test="${bildID == 2}">
			<img class="card-img-top" src="bilder/iPhoneSEred.jpg" alt="iPhone" style="width:100%">
			</c:when>
			
			<c:when test="${bildID == 3}">
			<img class="card-img-top" src="bilder/EastpakSpringer.jpg" alt="eastpak" style="width:100%">
			</c:when>
			
			<c:when test="${bildID == 4}">
			<img class="card-img-top" src="bilder/CasioTaschenrechner.jpg" alt="rechner" style="width:100%">
			</c:when>
			
			<c:when test="${bildID == 5}">
			<img class="card-img-top" src="bilder/ReishungerReiskocher.jpg" alt="reis" style="width:100%">
			</c:when>
			
			<c:when test="${bildID == 6}">
			<img class="card-img-top" src="bilder/Dyson.jpg" alt="dyson" style="width:100%">
			</c:when>
			
			<c:otherwise>
				Kein Bild vorhanden
			</c:otherwise>
			</c:choose>
    		<div class="card-body">
      		<h4 class="card-title"><c:out value="${aktuellesProdukt.name}"/></h4>
      		<p class="card-text">
      			<c:out value="${aktuellesProdukt.groesse}" /> <br>
      			<c:out value="${aktuellesProdukt.farbe}" /> <br>
      			<c:out value="${aktuellesProdukt.menge}" /> Stück auf Lager <br>
      			<c:out value="${aktuellesProdukt.preis}" /> Euro <br>
      		</p>
      			<form method="POST" action="MenuServlet">
                    	Menge (Min. 1): <input type="number" name="produktMenge" min="1" max="${aktuellesProdukt.menge}" required="required" value="${ inputMenge }"> <br />
                    	<button class="btn btn-primary" name="warenkorbHinzu" value="${aktuellesProdukt.id}">Warenkorb</button>
                </form>
    		</div>
  			</div>
		</div>
	</div>
</body>
</html>