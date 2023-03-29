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

	<h1>ça marche bien</h1>

	<label for="name">Filtres :</label>

	<input type="search" id="name" name="name" style="width:200px" placeholder="le nom de l'article contient" required minlength="4"
		maxlength="8" size="10"> <button>Search</button>
	<br>
	<br>
	<label for="choixCategorie">Catégorie :</label>

	<select name="categorie" id="choixCategorie">
		<option value="">Toutes</option>
		<option value="dog">Informatique</option>
		<option value="cat">Ameublement</option>
		<option value="hamster">Vêtement</option>
		<option value="parrot">Sport&Loisirs</option>
	</select>

	<%
	List<Article> article = (List<Article>) request.getAttribute("article");
	%>

	<%
	for (Article current : article) {
	%><p><%=current.getNomArticle() + " pour " + current.getDescription() + " "%></p>
	<p><%="Prix: " + current.getPrixDepart() + " " + "points"%></p>
	<p><%="Fin de l'enchere: " + current.getDateFinEnchere()%></p>
	<p><%="Vendeur: " + current.getUtilisateur().getPseudo()%>
	</p>
	<br>
	<%
	}
	%>


</body>
</html>