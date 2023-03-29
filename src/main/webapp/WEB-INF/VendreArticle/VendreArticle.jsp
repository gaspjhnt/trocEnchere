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
	<header>
		<p>ENI-Enchères</p>
	</header>
	
	<% List<String> lstErreur = (List<String>) request.getAttribute("lstErreur"); 
	   List<Categorie> lstCategorie = (List<Categorie>) request.getAttribute("lstCategorie");
	   
	   if (lstErreur != null) {
	   if (lstErreur.size()> 0){
		   %> <h5>Erreur :</h5> <%
		 for (String message : lstErreur){
			 %> <p><%=message%></p> <%
		 }
	   }
	   }
	   %>
	   
	
	
	<div class="CardVente">
		 <h3>Nouvelle vente</h3>
		 
 			<form action="./ServletVendreArticle" method="post">
 				<p>Article : </p>
				<label for="input_article"></label><input id="input_article" type="text" name="nomArticle" required/> 
 			
 				<p>Description : </p>
				<label for="input_description"></label><textarea name="description" id="input_description"></textarea>
				
				<p>Catégorie : </p>
				 
				<select name="couleurs" id="couleurs-select">
					<%for (Categorie cate : lstCategorie){
						%><option value="<%=cate.getNoCategorie()%>"> <%=cate.getLibelle()%></option> <% 
								}
					%>
				</select>
			<input type="submit" value="Valider"> 
		</form>
	</div>
</body>
</html>