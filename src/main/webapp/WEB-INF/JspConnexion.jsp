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
</head>
<body>

    <h1>Connecte toi</h1>
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

<form action="./ServletConnexion" method="post">

    Nom d'utilisateur: <input type="text" name="name">
    <br>
    Mot de passe: <input type="password" name="password">
    <br><br>
    <input type="submit" value="Connexion">
</form>
<a href="./ServletInscription">Inscription</a>
</body>
</html>	