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
    <form action="FilterServlet" method="post">
  		<div class="form-group">
    		<label for="produktName">Produkt Name</label>
    		<input type="text" id="produktName" name="produktName">
    		<label for="category">Produkte nach Kategorie sortieren:</label>
    			<select name="kategorie" id="kategorie">
      				<option value=""></option>
      				<option value="Elektronik">Elektronik</option>
      				<option value="Fashion">Fashion</option>
      				<option value="Küche, Haushalt & Wohnen">Küche, Haushalt & Wohnen</option>
    			</select>
  			<label for="sortBy">Produkte nach Preis sortieren:</label>
  				<select name="sortieren" id="sortieren">
  					<option value=""></option>
    				<option value="aufsteigend">Aufsteigend</option>
    				<option value="absteigend">Absteigend</option>
  				</select>
  			<button type="submit" class="btn btn-primary">Filtern</button>
  		</div>
	</form>

        <table class="table table-hover" border="1" cellpadding="5">
  			<thead>
    		<tr>
    			<th>Produktname</th>
                <th>Preis</th>
                <th>Farbe</th>
                <th>Größe</th>
                <th>Menge</th>
   			</tr>
  			</thead>
  			<tbody>
    			<c:forEach var="produkte" items="${prodListe}">
                <tr>
                    <td><c:out value="${produkte.name}" /></td>
                    <td><c:out value="${produkte.preis}" /></td>
                    <td><c:out value="${produkte.farbe}" /></td>
                    <td><c:out value="${produkte.groesse}" /></td>
                    <td><c:out value="${produkte.menge}" /></td>
                    <td> <form method="POST" action="ProduktDetailsServlet">
                    	<button class="btn btn-primary" name="details" value="${produkte.id}">Details</button>
                    </form></td>
                    <td> <form method="POST" action="MenuServlet">
                    	Menge (Min. 1): <input type="number" name="produktMenge" min="1" max="${produkte.menge}" required="required" value="${ inputMenge }"> <br />
                    	<button class="btn btn-primary" name="warenkorbHinzu" value="${produkte.id}">Warenkorb</button>
                    </form></td>
                </tr>
            </c:forEach>
  		</tbody>
		</table>
	</div>
</body>
</html>