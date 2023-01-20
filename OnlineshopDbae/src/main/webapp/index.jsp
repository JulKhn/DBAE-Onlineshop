<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hauptmenu</title>
</head>
<body>
<header>ShopIt</header>
<div align="center">
	<form method="POST" action="LoginServlet">
		<h2>Login <br />
		_
		</h2>
		E-Mail: <input type="email" name="email"> <br /> 
		Passwort: <input type="password" name="passwort"> <br /> 
		<input type="submit" value="Absenden!">
	</form>
	${ error }
</div>
</body>
</html>