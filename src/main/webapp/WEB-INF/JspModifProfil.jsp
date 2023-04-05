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
<title>Modifier Profil</title>
 <link rel="stylesheet" type="text/css" href="ModifProfil.css">
</head>

<body>
<jsp:include page="NavBarConnecte.html"></jsp:include>
<div class="container">
<div class="card">
<div class="titre">
<h1>Mon Profil</h1>
</div>

	<%Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
	%>
	
	
<form class="infos" action="./ServletModificationProfil" method="post" id="modifProfil">
<label for="pseudo">Pseudo : 
<input type="text" id="pseudo" name="pseudo" minlength="2" maxlength="25" value="<%=user.getPseudo()%>" required>
</label>
<label for="nom">Nom : 
<input type="text" id="nom" name="nom" minlength="2" maxlength="25" value= "<%=user.getNom()%>" required>
</label>
<label for="prenom">Prenom : 
<input type="text" id="prenom" name="prenom" minlength="2" maxlsength="25" value="<%=user.getPrenom()%>" required>
</label>
<label for="email">Email : 
<input type="email" id="email" name="email" minlength="10" maxlength="75" value="<%=user.getEmail()%>" required>
</label>
<label for="telephone">Téléphone : 
<input type="number" id="telephone" name="telephone" minlength="10" maxlength="10" value="<%=user.getTelephone()%>" required>
</label>
<label for="rue">Rue : 
<input type="text" id="rue" name="rue" value="<%=user.getRue()%>" required>
</label>
<label for="codePostal">Code Postal : 
<input type="number" id="codePostal" name="codePostal" minlength="5" maxlength="5" value="<%=user.getCodePostal()%>" required>
</label>
<label for="ville">Ville : 
<input type="text" id="ville" name="ville" value="<%=user.getVille()%>" required>
</label>
<label for="mdp">Mot de passe actuel : 
<input pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" type="password" id="mdpActuel" name="mdpActuel" minlength="8" required>
</label>
<label for="mdp">Nouveau mot de passe : 
<input pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" type="password" id="nouveauMdp" name="nouveauMdp" minlength="8">
</label>
<label for="confirmMdp">Confirmation : 
<input pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" type="password" id="confirmMdp" name="confirmMdp" minlength="8">
</label>
<p><%="Crédit : "+user.getCredit()%></p>
</form>
<div class="boutons">
<form method="post" action="./ServletModificationProfil">
<button class="bout" type="submit" form="modifProfil">Enregistrer</button>
</form>
<form method="get" action="./ServletSuppressionProfil">
<input class="bout" type="submit" value="Supprimer mon compte">
</form>
</div>
</div>
<%List<String> lstErreurs = (List<String>)request.getAttribute("lstErreur");
	if(lstErreurs != null) {
		for(String message : lstErreurs){
			%> <p class="erreur"><%=message%></p>
			<%
		}
	}%>
</div>
</body>
</html>