<%@page import="java.util.List"%>
<%@page import="fr.eni.ecole.trocenchere.bo.Categorie" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Article" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Utilisateur" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ENI-Enchères</title>
<link rel="stylesheet" type="text/css" href="ArticleVente.css">
</head>
<body>

<jsp:include page="../NavBarConnecte.html"></jsp:include>	
	
	<% List<String> lstErreur = (List<String>) request.getAttribute("lstErreur"); 
	   List<Categorie> lstCategorie = (List<Categorie>) request.getAttribute("lstCategorie");
	   String valide = (String) request.getAttribute("valide");
	   Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
	   Article article = (Article) request.getAttribute("article");
	   
	   // Verification si il y a des erreurs
	   if (lstErreur != null) {
	   		if (lstErreur.size()> 0){
	   			
	   			//Si oui on affiche les différentes erreurs rencontrées
		   %> <h5>Erreur :</h5> <%
		 		for (String message : lstErreur){
			 %> <p><%=message%></p> <%
			 }
	   	}
	   		//Si non on verifie s'il y a un succès
	   } else if (valide != null){
		   
		   //Si oui on affiche le(s) succès
		  %> <h5>Succès :</h5> <%
		  %> <p> <%=valide%> </p> <%
		  
	   }
	   %>

	<div class="containerCard">
	<div class="CardVente">
		<div class="titre">
		 <h3>Modifier Article</h3>
		 </div>
		 <div class="textinput">
 			<form action="./ServletUdpateArticle" method="post" id="vendreArticle">
 				<div class="article">
 				<p>Article : </p>
				<label for="input_article"></label><input id="input_article" value="<%=article.getNomArticle() %>" type="text" name="nomArticle" placeholder="Nom (45 caractères max.)" required/> 
				</div>
 				<div class="article">
 				<p>Description : </p>
				<label for="input_description"></label><textarea name="description" id="input_description" placeholder="Description (255 caractères max.)" required><%=article.getDescription() %></textarea>
				</div>
				<div class="article">
				<p>Catégorie : </p>
				 
				<select name="categorie" id="categorie_select" value="<%=article.getCategorie().getLibelle()%>" required>
					<option value="<%=article.getCategorie().getNoCategorie()%>"><%=article.getCategorie().getLibelle()%></option>
					<%for (Categorie cate : lstCategorie){
						if (cate.getNoCategorie() == article.getCategorie().getNoCategorie()){
							break;
						}
						//Ajout de toutes les catégories présentes en BDD dans le select
						//Avec une value qui est égal a son ID pour l'utiliser dans la servlet
						%><option value="<%=cate.getNoCategorie()%>"> <%=cate.getLibelle()%></option> <% 
								}
					%>
				</select>
				</div>
				<div class="article">
				<p>Mise à prix :</p>
				<label for="input_mise_a_prix"></label><input id="input_mise_a_prix" value="<%=article.getPrixDepart() %>" type="number" name="prixArticle" required/> 
				</div>
				<div class="article">
					<p>Date début enchère :</p>
					<label for="input_date_debut"></label><input id="input_date_debut" value="<%=article.getDateDebutEnchere() %>" type="date" name="dateDebutEnchere" required/>
					</div>
					<div class="article">
					<p>Date Fin enchère :</p>
					<label for="input_date_fin"></label><input id="input_date_fin" value="<%=article.getDateFinEnchere() %>" type="date" name="dateFinEnchere" required/>
				</div>
				
				<div class="article">
					<p>Rue :</p>
					<label for="input_rue"></label><input id="input_rue" type="text" value="<%=article.getRetrait().getRue() %>" name="rueRetrait"/>
					</div>
					<div class="article">
					<p>Code postal :</p>
					<label for="input_code_post"></label><input id="input_code_post" type="number" max="99999" value=<%=article.getRetrait().getCodePostal() %> name="codePostalRetrait"/>
					</div>
					<div class="article">
					<p>Ville :</p>
					<label for="input_ville"></label><input id="input_ville" type="text" value="<%=article.getRetrait().getVille() %>" name="villeRetrait"/>
					</div>
					
					<input type="hidden" name="idArticle" value="<%=article.getNoArticle()%>">
			</form>
			</div>
			<div class="boutons">
			<input class="boute" type="submit" value="Valider" form="vendreArticle"> 
			
			<a href="http://localhost:8080/trocEnchere/ServletListeEnchere">
				<button class="boute">Annuler</button>
			</a>
			</div>
	</div>
	</div>
</body>
</html>