<%@page import="java.util.List"%>
<%@page import="fr.eni.ecole.trocenchere.bo.Categorie" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Utilisateur" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="US-ASCII">
    <title>Page de connexion</title>
    <link rel="stylesheet" type="text/css" href="Connexion.css">
</head>
<body>
<div class="container">
    <h1>Connecte toi</h1>
<div class="saisir">
<% List<String> lstErreur = (List<String>) request.getAttribute("lstErreur"); 
// Verification si il y a des erreurs
if (lstErreur != null) {
		if (lstErreur.size()> 0){
			
			//Si oui on affiche les différentes erreurs rencontrées
	   %> <h5>Erreur :</h5> <%
	 		for (String message : lstErreur){
		 %> <p><%=message%></p> <%
		 }
		}
	}
%>

<form action="./ServletConnexion" method="post" id="connexion">

    <p>Nom d'utilisateur:</p> <input class="champ" type="text" name="name">
    <br>
    <p>Mot de passe:</p> <input class="champ" type="password" name="password">
</form>
<div class="bouteboute">
<input class="bout" type="submit" value="Connexion" form="connexion">
<a class="bout" href="./ServletInscription">Inscription</a>
</div>
</div>
</div>
</body>
</html>	