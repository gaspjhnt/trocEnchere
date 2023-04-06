<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.ecole.trocenchere.bo.Categorie" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Article" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Retrait" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Utilisateur" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Enchere" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="DetailsArticle.css">
</head>
<body>	

<%
//Récuperartion de la liste d'erreru et de l'article
List<String> lstErreur = (List<String>) request.getAttribute("lstErreur"); 
Article article = (Article) request.getAttribute("article");
List<Enchere> lstEnchere = (List<Enchere>) request.getAttribute("lstEnchere");
   
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
   
 
<%if(session.getAttribute("Utilisateur")==null){
	 %>
	<jsp:include page="../NavBarDeconnecte.html"></jsp:include>
<% } else {%>
	<jsp:include page="../NavBarConnecte.html"></jsp:include>
	<%} %>

<div class="container">
<div class="cardArticle">
<div class="titre">
<h1>Détail vente</h1>
</div>
	<div class="infos">
		<p id="nomArticle"><%=article.getNomArticle() %></p>
		
		<div class="monInfo">
			<h3>Description :</h3> <p><%=article.getDescription() %></p>
		</div>
		
		<div class="monInfo">
		 <h3>Catégorie :</h3> <p><%=article.getCategorie().getLibelle()%></p>
		</div>
		
		<div class="monInfo">
			<h3>Meilleure offre :</h3> <p><%=request.getAttribute("Enchere") %></p>
		</div>
		
		 
		<div class="monInfo">
			<h3>Mise à prix :</h3> <p><%=article.getPrixDepart() %> points</p>
		</div>
		
		<div class="monInfo">
			<h3>Fin de l'enchère :</h3> <p><%=article.getDateFinEnchere() %></p>
		</div>
		
		<div class="monInfo">
			<%Retrait retrait = (Retrait) request.getAttribute("Retrait");%>
			<h3>Retrait :</h3> <p><%=retrait.getRue()%>
			<%=retrait.getCodePostal()  %> <%=retrait.getVille()%></p>
		</div>
		
		
		<div class="vendeur">
			<form action="./ServletProfilOther" method="get">
				<label class="babybel">Vendeur : 
				<button class="boute" type="submit" name="seller" value="<%=article.getUtilisateur().getNoUtilisateur()%>">
					<%=article.getUtilisateur().getPseudo() %>
				</button>
				</label>
			</form>
		</div>
		
		<%if (session.getAttribute("Utilisateur") != null) {
			Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
			if (user.getNoUtilisateur() != article.getUtilisateur().getNoUtilisateur()) {%>

	 	<form action="./ServletDetailArticle" method="post">
	 	<%Integer minPropo = (Integer) request.getAttribute("Proposition") + 1; %>
			<%if (article.getDateDebutEnchere().isAfter(LocalDate.now())) {%>
						
						<p>L'enchère ne débute que le : <%=article.getDateDebutEnchere() %></p>
<%-- 			 		<input type="hidden" name="PropositionMin" value=<%=minPropo %> disabled> --%>
<%-- 			 		<label for="input_proposition">Ma proposition : </label><input id="input_proposition" type="number" name="none" min="<%=minPropo%>" value="<%=minPropo%>" required disabled/> --%>
<!-- 			 		<input type="button" value="Encherir" disabled> -->
		 		<%} else if (article.getDateFinEnchere().isBefore(LocalDate.now())) {
		 			if (lstEnchere.size() >= 1){
		 			%>
		 			<p>Acquéreur : <%=lstEnchere.get(lstEnchere.size() -  1).getUtilisateur().getPseudo() %> pour <%= lstEnchere.get(lstEnchere.size() -  1).getMontant_enchere()%> pts.</p> <%} %>
		 			<p>L'enchère a pris fin le : <%=article.getDateFinEnchere() %></p><%
		 		}
			
			
			else { %>
					<div class="propo">
		 			<input type="hidden" name="PropositionMin" value=<%=minPropo %>>
			 		<label class="babybel" id="mapropo" for="input_proposition">Ma proposition : </label><input id="input_proposition" type="number" name="proposition" min="<%=minPropo%>" value="<%=minPropo%>" required/>
			 		<input class="boute" type="submit" value="Encherir">
		 			</div>
		 		<%} %>
	 	</form>
	 	<%}else {%>
	 		<form action="./ServletUdpateArticle" method="get">
					<button class="boute" type="submit" name="modifSupp" value=<%=article.getNoArticle() %>>
		 			Modifier - Supprimer
		 			</button>
			<%} %>
	 		</form>
	 	<%} %>
	 	</div>
	 	</div>
		
	</div>
	
	
</body>
</html>