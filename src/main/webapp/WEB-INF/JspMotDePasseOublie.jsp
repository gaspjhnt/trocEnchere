<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mot de passe oublié</title>
<link rel="stylesheet" type="text/css" href="Connexion.css">

</head>
<body>

<div class="container">
	<h1>Veuillez saisir votre email : </h1>
	<div class="saisir">
	<form method="get" action="/ServletMotDePasseOublie" >
	<p style="margin-top:50px;">Email:</p>
	<input type="email" id="email" name="email" minlength="10" maxlength="75" required>
	<input style="margin-top:10px" class="bout" class="bouteFiltre" type="submit" value="Envoyer">
	</form>
	</div>
	</div>
</body>
</html>