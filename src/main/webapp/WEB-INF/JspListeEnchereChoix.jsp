<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.eni.ecole.trocenchere.bo.Article"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	Cookie cookie= (Cookie) request.getAttribute("ChoixCookie");
	Cookie deuxiemeCookie = (Cookie)request.getAttribute("RechercheCookie");
	
	String choix= cookie.getValue();
	String deuxiemeChoix= deuxiemeCookie.getValue();
	
	Boolean imprimeChacal=false;

	%>
	<%
	List<Article> article = (List<Article>) request.getAttribute("article");
	%>
	<form method="post" action="./ServletListeEnchere">
		<label for="name">Filtres :</label> <input type="search" id="name"
			name="recherche" style="width: 200px"
			placeholder="le nom de l'article contient"><input
			type="submit" value="Envoyer"> <br> <br> <label
			for="choixCategorie">Catégorie :</label> <select name="categorie"
			id="choixCategorie">
			<option value="Toutes">Toutes</option>
			<option value="Informatique">Informatique</option>
			<option value="Ameublement">Ameublement</option>
			<option value="Vetement">Vêtement</option>
			<option value="Sport">Sport&Loisirs</option>
		</select>
	</form>
	
	<% if(choix.equals("Toutes")){%>
		<form method="get" action="./ServletDetailArticle">
		<%for (Article current : article) {if ((current.isEtatVente()==false) && current.getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase())){
			%>
			<button type="submit">
					<%=current.getNomArticle()%>
			</button><input type="hidden" id="input_article" name="idArticle" value=<%=current.getNoArticle()%>>
		
			<p>	<%="Prix: " + current.getPrixDepart() + " " + "points"%> </p>
			<p>	<%="Fin de l'enchere: " + current.getDateFinEnchere()%> </p>
			<p>	<%="Vendeur: " + current.getUtilisateur().getPseudo()%> </p>
			<br>
		<%
		imprimeChacal=true;
		}}
	%>
		</form>
<% 
	} else {
		for (Article current : article) {%>
		<form method="get" action="./ServletDetailArticle">
		<%if (current.getCategorie().getLibelle().equals(choix)&&(current.isEtatVente()==false)&& current.getNomArticle().contains(deuxiemeChoix)){
				%>
				<button type="submit">
						<%=current.getNomArticle()%>
				</button><input type="hidden" id="input_article" name="idArticle" value=<%=current.getNoArticle()%>>
			
				<p>	<%="Prix: " + current.getPrixDepart() + " " + "points"%> </p>
				<p>	<%="Fin de l'enchere: " + current.getDateFinEnchere()%> </p>
				<p>	<%="Vendeur: " + current.getUtilisateur().getPseudo()%> </p>
				<br>
			<%
			imprimeChacal=true;
			}
		}%>
		</form> <%
	}%>
	
	<%if(imprimeChacal==false) {%>
	<h1><%="Aucun article trouvé pour ta recherche... Essaye autre chose mon gourmand"%></h1>
	<%} %>


</body>
</html>