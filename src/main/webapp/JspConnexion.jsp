<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="US-ASCII">
    <title>Page de connexion</title>
    <h1>Connecte toi</h1>
</head>
<body>

<form action="ServletConnexion" method="post">

    Nom d'utilisateur: <input type="text" name="name">
    <br>
    Mot de passe: <input type="password" name="password">
    <br><br>
    <input type="submit" value="Connexion">
</form>
</body>
</html>