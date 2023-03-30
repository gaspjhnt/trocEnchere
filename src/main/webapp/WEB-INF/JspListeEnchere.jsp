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
	
<!-- 	Création du champ de recherche et de la liste déroulante de catégorie -->
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
	//Initialisation d'une Liste d'article qui prend en valeur la clé "article" qui contient notre méthode getAllByDate
	List<Article> article = (List<Article>) request.getAttribute("article");
	%>


<!-- Boucle foreach de la Liste article qui va imprimer tous les articles dont la date de fin d'enchere est après la date du jour présents dans la base de donnée -->
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