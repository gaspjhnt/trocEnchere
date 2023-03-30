<%@page import="java.util.List"%>
<%@page import="fr.eni.ecole.trocenchere.bo.Categorie" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulaire inscription</title>
</head>
<body>
<%List<String> lstErreurs = (List<String>)request.getAttribute("lstErreurs");
if(lstErreurs != null) {
for(String message : lstErreurs){
	%> <p><%=message%></p>
	<%
}
}%>
<h3>Mon profil</h3>

<form action="./ServletInscription" method="post" id="inscription">
<label for="pseudo">Pseudo : </label>
<input type="text" id="pseudo" name="pseudo" minlength="2" maxlength="25" required>
<br>
<label for="nom">Nom : </label>
<input type="text" id="nom" name="nom" minlength="2" maxlength="25" required>
<br>
<label for="prenom">Prenom : </label>
<input type="text" id="prenom" name="prenom" minlength="2" maxlength="25" required>
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
<label for="mdp">Mot de passe : </label>
<input type="password" id="mdp" name="mdp" minlength="8" required>
<br>
<label for="confirmMdp">Confirmation du mot de passe : </label>
<input type="password" id="confirmMdp" name="confirmMdp" minlength="8" required>
</form>
<button type="submit" form="inscription">Valider</button>
<a href="http://localhost:8080/trocEnchere/ServletListeEnchere"><input type="button" value="Annuler"></a>
</body>
</html>