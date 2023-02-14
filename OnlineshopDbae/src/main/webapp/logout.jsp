<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<script>
	//Script in dem der Nutzer nach erfolgreichem logout innerhalb von 5 Sekunden wieder auf die Index JSP geleitet wird.
    var sekunden = 5;
    var seite = "index.jsp";

    function counter(){
        var timer = document.getElementById("timer");
        if(sekunden > 0){
        	sekunden--;
            setTimeout("counter()", 1000);
        }else{
            window.location.href = seite;
        }
    }
</script>
<body onload="counter()">
<%@ include file="navbar.jsp" %>
<div id="timer"></div>
<div align="center">
<h1>Auf Wiedersehen!</h1>

Sie haben sich erfolgreich ausgeloggt und werden in den nächsten 5 Sekunden auf die Startseite weitergeleitet.
</div>
</body>
</html>