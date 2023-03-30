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

<form method="post" action="./ServletListeEnchere">
	<label for="name">Filtres :</label>

	<input type="search" id="name" name="recherche" style="width:200px" placeholder="le nom de l'article contient"> <input type="submit" value="Envoyer">
	<br>
	<br>
	<label for="choixCategorie">Catégorie :</label>
	<select name="categorie" id="choixCategorie">
		<option value="Toutes">Toutes</option>
		<option value="Informatique">Informatique</option>
		<option value="Ameublement">Ameublement</option>
		<option value="Vetement">Vêtement</option>
		<option value="Sport">Sport&Loisirs</option>
	</select>
</form>

	<%
	List<Article> article = (List<Article>) request.getAttribute("article");
	%>



	<form method="post" action="./ServletDetailArticle">
	<%
	for (Article current : article) {if (current.isEtatVente()==false){
	%>
	<button type="submit">
			<%=current.getNomArticle()%>
	</button><input type="hidden" id="input_article" name="idArticle" value=<%=current.getNoArticle()%>>

	<p>	<%="Prix: " + current.getPrixDepart() + " " + "points"%> </p>
	<p>	<%="Fin de l'enchere: " + current.getDateFinEnchere()%> </p>
	<p>	<%="Vendeur: " + current.getUtilisateur().getPseudo()%> </p>
	<br>
	<%
	}}
	%>
	</form>

</body>
</html>