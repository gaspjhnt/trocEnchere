<%@page import="java.util.List"%>
<%@page import="fr.eni.ecole.trocenchere.bo.Categorie" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Article" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Retrait" %>
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
<% List<String> lstErreur = (List<String>) request.getAttribute("lstErreur"); 
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
   } %>
   
<h3>Détail vente</h3>

	<p><%=article.getNomArticle() %></p>
	
	<div class="description">
		<p>Description : <%=article.getDescription() %></p>
	</div> <br>
	
	<div class="categorie">
		<p>Catégorie : <%=article.getCategorie().getLibelle()%></p>
	</div><br>
	
	<div class="enchere">
		<p>Meilleure offre : <%=request.getAttribute("Enchere") %></p>
	</div><br>
	
	
	<div class="miseAPrix">
		<p>Mise à prix : <%=article.getPrixDepart() %> points</p>
	</div><br>
	
	<div class="dateFinEnchere">
		<p>Fin de l'enchère : <%=article.getDateFinEnchere() %></p>
	</div><br>
	
	<div class="retrait">
		<%Retrait retrait = (Retrait) request.getAttribute("Retrait");%>
		<p>Retrait : <%=retrait.getRue() + " " %></p>
		<p><%=retrait.getCodePostal()  %> <%=retrait.getVille()%></p>
	</div><br>
	
	
	<div class="vendeur">
		<p>Vendeur : <%=article.getUtilisateur().getPseudo() %></p>
	</div><br>
	
	
 	<form action="./ServletDetailArticle" method="post">
 	<%Integer minPropo = (Integer) request.getAttribute("Proposition"); %>
 		<label for="input_proposition">Ma proposition : </label><input id="input_proposition" type="number" name="proposition" min="<%=minPropo%>" value="<%=minPropo%>" required/>
 		<input type="submit" value="Encherir">
 	</form>
	
</body>
</html>