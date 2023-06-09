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
	//Initialisation des cookies qui prennent la valeur des clés "ChoixCookies" et "RechercheCookie"
	Cookie cookie= (Cookie) request.getAttribute("ChoixCookie");
	Cookie deuxiemeCookie = (Cookie)request.getAttribute("RechercheCookie");
	
	//Initialisation des String choix et deuxiemeChoix qui prennent la valeur des cookies
	String choix= cookie.getValue();
	String deuxiemeChoix= deuxiemeCookie.getValue();
	
	//Je met en place un petit Boolean à insérer dans ma Foreach pour voir si elle imprime quelquechose ou non
	//Elle va me servir à imprimer "pas d'article trouvé" s'il n'y a pas de résultat à la recherche de l'utilisateur
	Boolean imprimeChacal=false;

	%>
	<%
	//Initialisation d'une Liste d'article qui prend en valeur la clé "article" qui contient notre méthode getAllByDate
	List<Article> article = (List<Article>) request.getAttribute("article");
	%>
<!-- 	Création du champ de recherche et de la liste déroulante de catégorie -->
	<form method="get" action="./ServletListeEnchere">
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
	
<!-- Création de deux if selon le choix utilisateur. On passe dans le premier lorsque l'utilisateur a choisi toutes les catégories  -->
<!-- La foreach va imprimer un article si l'état de l'article est en vente et si l'article contient la valeur dans le champ de recherche de l'utilisateur -->

	<% if(choix.equals("Toutes")){%>
		<form method="get" action="./ServletDetailArticle">
		<%for (Article current : article) {
			if ((current.isEtatVente()==false) && current.getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase())){
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
		<!-- Création du deuxième if. On passe dans le deuxième lorsque l'utilisateur a choisi une catégorie  -->
<!-- La foreach va imprimer un article si sa catégorie correspond à celle de l'utilisateur et si l'état de l'article est en vente et si l'article contient la valeur dans le champ de recherche de l'utilisateur -->
<% 
	} else {
		for (Article current : article) {%>
		<form method="post" action="./ServletDetailArticle">
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
<!-- 	S'il n'y a aucune réponse à la recherche utilisateur, on imprime un message  -->
	<%if(imprimeChacal==false) {%>
	<h1><%="Aucun article trouvé pour ta recherche... Essaye autre chose mon gourmand"%></h1>
	<%} %>


</body>
</html>