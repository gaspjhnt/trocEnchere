<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.ecole.trocenchere.bo.Categorie" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Article" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Retrait" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Utilisateur" %>
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

<%
//Récuperartion de la liste d'erreru et de l'article
List<String> lstErreur = (List<String>) request.getAttribute("lstErreur"); 
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

	<div class="cardArticle">
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
		
		<%if (session.getAttribute("Utilisateur") != null) {
			Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
			if (user.getNoUtilisateur() != article.getUtilisateur().getNoUtilisateur()) {%>

	 	<form action="./ServletDetailArticle" method="post">
	 	<%Integer minPropo = (Integer) request.getAttribute("Proposition") + 1; %>
			<%if (article.getDateDebutEnchere().isAfter(LocalDate.now()) 
						|| article.getDateFinEnchere().isBefore(LocalDate.now())){%>
						
						<p>L'enchère ne début que le : <%=article.getDateDebutEnchere() %></p>
<%-- 			 		<input type="hidden" name="PropositionMin" value=<%=minPropo %> disabled> --%>
<%-- 			 		<label for="input_proposition">Ma proposition : </label><input id="input_proposition" type="number" name="none" min="<%=minPropo%>" value="<%=minPropo%>" required disabled/> --%>
<!-- 			 		<input type="button" value="Encherir" disabled> -->
		 		<%} else { %>
		 			<input type="hidden" name="PropositionMin" value=<%=minPropo %>>
			 		<label for="input_proposition">Ma proposition : </label><input id="input_proposition" type="number" name="proposition" min="<%=minPropo%>" value="<%=minPropo%>" required/>
			 		<input type="submit" value="Encherir">
		 		<%} %>
	 	</form>
	 	<%}else {%>
	 		<form action="./ServletUdpateArticle" method="get">
					<button type="submit" name="modifSupp" value=<%=article.getNoArticle() %>>
		 			Modifier - Supprimer
		 			</button>
			<%} %>
	 		</form>
	 	<%} %>
		
	</div>
	
	
</body>
</html>