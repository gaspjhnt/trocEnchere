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
 <link rel="stylesheet" type="text/css" href="Inscription.css">
</head>
<body>
	<%List<String> lstErreurs = (List<String>)request.getAttribute("lstErreurs");
	if(lstErreurs != null) {
		for(String message : lstErreurs){
			%> <p><%=message%></p>
			<%
		}
	}%>

<jsp:include page="NavBarDeconnecte.html"></jsp:include>
	
<div class="container">
<div class="card">
<div class="titre">
<h1>Mon profil</h1>
</div>
<form class="infos" action="./ServletInscription" method="post" id="inscription">

<label class="moninfo" for="pseudo">Pseudo : 
<input type="text" id="pseudo" name="pseudo" minlength="2" maxlength="25" required>
</label>
<label class="moninfo" for="nom">Nom : 
<input type="text" id="nom" name="nom" minlength="2" maxlength="25" required>
</label>
<label class="moninfo" for="prenom">Prenom : 
<input type="text" id="prenom" name="prenom" minlength="2" maxlength="25" required>
</label>
<label class="moninfo" for="email">Email : 
<input type="email" id="email" name="email" minlength="10" maxlength="75" required>
</label>
<label class="moninfo" for="telephone">Téléphone : 
<input type="number" id="telephone" name="telephone" minlength="10" maxlength="10" required>
</label>
<label class="moninfo" for="rue">Rue : 
<input type="text" id="rue" name="rue" required>
</label>
<label class="moninfo" for="codePostal">Code Postal : 
<input type="number" id="codePostal" name="codePostal" minlength="5" maxlength="5" required>
</label>
<label class="moninfo" for="ville">Ville : 
<input type="text" id="ville" name="ville" required>
</label>
<label class="moninfo" for="mdp">Mot de passe : 
<input type="password" id="mdp" name="mdp" minlength="8" required>
</label>
<label class="moninfo" for="confirmMdp">Confirmation du mot de passe : 
<input type="password" id="confirmMdp" name="confirmMdp" minlength="8" required>
</label>
</form>
<div class="bouteboute">
<button class="boute" type="submit" form="inscription">Créer</button>
<a href="http://localhost:8080/trocEnchere/ServletListeEnchere"><input class="boute" type="button" value="Annuler"></a>
</div>
</div>
</div>
</body>
</html>