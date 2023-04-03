<%@page import="fr.eni.ecole.trocenchere.bo.Utilisateur" %>
<%@page import="java.util.List"%>
<%@page import="fr.eni.ecole.trocenchere.bo.Categorie" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>ça marche bien</h1>


<h3>Mon profil</h3>

	<%Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
	String credit=(String) request.getAttribute("credit");%>

<form action="./ServletModificationProfil" method="post" id="modifProfil">
<label for="pseudo">Pseudo : </label>
<input type="text" id="pseudo" name="pseudo" minlength="2" maxlength="25" required>
<br>
<label for="nom">Nom : </label>
<input type="text" id="nom" name="nom" minlength="2" maxlength="25" required>
<br>
<label for="prenom">Prenom : </label>
<input type="text" id="prenom" name="prenom" minlength="2" maxlsength="25" required>
<br>
<label for="email">Email : </label>
<input type="email" id="email" name="email" minlength="10" maxlength="75" required>
<br>
<label for="telephone">Téléphone : </label>
<input type="number" id="telephone" name="telephone" minlength="10" maxlength="10" required>
<br>
<label for="rue">Rue : </label>
<input type="text" id="rue" name="rue" required>
<br>
<label for="codePostal">Code Postal : </label>
<input type="number" id="codePostal" name="codePostal" minlength="5" maxlength="5" required>
<br>
<label for="ville">Ville : </label>
<input type="text" id="ville" name="ville" required>
<br>
<label for="mdp">Mot de passe actuel : </label>
<input type="password" id="mdpActuel" name="mdpActuel" minlength="8" required>
<br>
<label for="mdp">Nouveau mot de passe : </label>
<input type="password" id="nouveauMdp" name="nouveauMdp" minlength="8" required>
<br>
<label for="confirmMdp">Confirmation : </label>
<input type="password" id="confirmMdp" name="confirmMdp" minlength="8" required>
</form>
	<%="Crédit : "+user.getCredit()%>
<br>
<button type="submit" form="modifProfil">Enregistrer</button>

<form method="get" action="./ServletSuppressionProfil">
<input type="submit" value="Supprimer mon compte">
</form>
</body>
</html>