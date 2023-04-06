<%@page import="java.time.LocalDate"%>
<%@page import="org.apache.taglibs.standard.lang.jstl.DivideOperator"%>
<%@page import="fr.eni.ecole.trocenchere.bo.Categorie" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Utilisateur" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Enchere" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.eni.ecole.trocenchere.bo.Article"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDate"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="ListeEnchere.css">
<meta charset="UTF-8">
<title>Page d'Accueil</title>
</head>
<body>

<%
	Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
	//Initialisation des cookies qui prennent la valeur des clés "ChoixCookies" et "RechercheCookie"
	Cookie cookie= (Cookie) request.getAttribute("ChoixCookie");
	Cookie deuxiemeCookie = (Cookie)request.getAttribute("RechercheCookie");
	Cookie troisiemeCookie= (Cookie) request.getAttribute("boutonRadioCookie");
	Cookie quatriemeCookie= (Cookie) request.getAttribute("checkboxesCookie1") ;
	Cookie cinquiemeCookie= (Cookie) request.getAttribute("checkboxesCookie2") ;
	
	//Initialisation des String choix et deuxiemeChoix qui prennent la valeur des cookies
	String choix="";
	if (cookie != null) {
     choix = cookie.getValue();
}
	String deuxiemeChoix= "";
			if(deuxiemeCookie!=null){
				deuxiemeChoix=deuxiemeCookie.getValue();
			}
	String troisiemeChoix= "";
			if(troisiemeCookie!=null){
				troisiemeChoix=troisiemeCookie.getValue();
			}
	String quatriemeChoix= "";
			if(quatriemeCookie!=null){
				quatriemeChoix=quatriemeCookie.getValue();
			}
	String cinquiemeChoix= "";
			if(cinquiemeCookie!=null){
				cinquiemeChoix=cinquiemeCookie.getValue();
			}
			
			
	//Je met en place un petit Boolean à insérer dans ma Foreach pour voir si elle imprime quelquechose ou non
	//Elle va me servir à imprimer "pas d'article trouvé" s'il n'y a pas de résultat à la recherche de l'utilisateur
	Boolean imprimeChacal=false;
	%>
	<%
	//Initialisation d'une Liste d'article qui prend en valeur la clé "article" qui contient notre méthode getAllByDate
	List<Article> article = (List<Article>)request.getAttribute("article");
	List<Categorie> lstCategorie = (List<Categorie>) request.getAttribute("lstCategorie");
	
	//Initialisation d'une Liste d'enchères qui prend en valeur la clé "encherebyuser" qui contient notre méthode getEnchereByUser
	List<Enchere>enchere = (List<Enchere>)request.getAttribute("encherebyuser");
	
	//Initialisation d'une Liste d'articles qui prend en valeur la clé "selectallbyuser" qui contient notre méthode getAllArticlesByUser
	List<Article> allArticleByUser = (List<Article>)request.getAttribute("selectallbyuser");

	
	//Affichage erreur
	List<String> lstErreur = (List<String>) request.getSession().getAttribute("lstErreurEnchere");
	String succes = (String) request.getSession().getAttribute("SuccesDetailsArticle");

	%>

<%if(session.getAttribute("Utilisateur")==null){
	 %>
	<jsp:include page="NavBarDeconnecte.html"></jsp:include>
<% } else {%>
	<jsp:include page="NavBarConnecte.html"></jsp:include>
	<%} %>

<%if (lstErreur != null) {
	if (lstErreur.size() > 0) {
	%> <p>Erreur :</p> <%
	for (String element : lstErreur) {%>
	<p><%=element %></p>
<%}
	}
else if (succes != null){%>
<p>Succès : </p> <%= succes%>
<%  }
	} %>

<div class="container">
<!-- 	Création du champ de recherche et de la liste déroulante de catégorie -->
<form class="filtre" method="post" action="./ServletListeEnchere" id="filtreform">
	<label for="choixCategorie">Catégorie:</label>
	<select name="categorie" id="choixCategorie">
		<option value="Toutes">Toutes</option>
		<option value="Informatique">Informatique</option>
		<option value="Ameublement">Ameublement</option>
		<option value="Vetement">Vêtement</option>
		<option value="Sport">Sport&Loisirs</option>
	</select>
	<label class="textFiltre" for="name">Filtres:</label>
	<input type="search" id="name" name="recherche" placeholder="le nom de l'article contient"> 
	<input class="bouteFiltre" type="submit" value="Envoyer">
</form>

<%if(session.getAttribute("Utilisateur")!=null){%>
<div class="rechercheSpe">
<div class="mesachats">
  <label for="radio-1">Achat
  <input type="radio" name="bouton-radio" id="radio-1" value="achat" form="filtreform"
         onclick="disableCheckboxes('bouton-radio-1')" checked>
</label>
  <label for="checkbox-1-1">enchères ouvertes
  <input type="checkbox" name="checkbox-1" id="checkbox-1-1" value="encheresOuvertes" form="filtreform"
         onclick="checkIfAllChecked('bouton-radio-1'); uncheckOtherCheckboxes(this);" checked>
</label>
  <label for="checkbox-1-2">mes enchères
  <input type="checkbox" name="checkbox-1" id="checkbox-1-2" value="mesEncheres" form="filtreform"
         onclick="checkIfAllChecked('bouton-radio-1'); uncheckOtherCheckboxes(this);">
</label>
  <label for="checkbox-1-3">mes enchères remportées
  <input type="checkbox" name="checkbox-1" id="checkbox-1-3" value="mesEnchereRemportees" form="filtreform"
         onclick="checkIfAllChecked('bouton-radio-1'); uncheckOtherCheckboxes(this);">
         </label>
</div>
<div class="mesventes">
  <label for="radio-2">Mes ventes
  <input type="radio" name="bouton-radio" id="radio-2" value="mesVentes" form="filtreform"
         onclick="disableCheckboxes('bouton-radio-2')">
</label>
  <label for="checkbox-2-1">mes ventes en cours
  <input type="checkbox" name="checkbox-2" id="checkbox-2-1" value="mesVentesEnCours" form="filtreform"
         onclick="checkIfAllChecked('bouton-radio-2'); uncheckOtherCheckboxes(this);">
</label>
  <label for="checkbox-2-2">ventes non débutées
  <input type="checkbox" name="checkbox-2" id="checkbox-2-2" value="mesVentesNonDebutees" form="filtreform"
         onclick="checkIfAllChecked('bouton-radio-2'); uncheckOtherCheckboxes(this);">
</label>
  <label for="checkbox-2-3">ventes terminées
  <input type="checkbox" name="checkbox-2" id="checkbox-2-3" value="mesVentesTerminees" form="filtreform"
         onclick="checkIfAllChecked('bouton-radio-2'); uncheckOtherCheckboxes(this);">
         </label>
         </div>
</div>


<script>
function disableCheckboxes(boutonRadioValue) {
    let checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(function(checkbox) {
      if (checkbox.name === 'checkbox-1' && boutonRadioValue !== 'bouton-radio-1') {
        checkbox.disabled = true;
      } else if (checkbox.name === 'checkbox-2' && boutonRadioValue !== 'bouton-radio-2') {
        checkbox.disabled = true;
      } else {
        checkbox.disabled = false;
      }
    });
    uncheckCheckboxes();
  }
  function uncheckCheckboxes() {
    let checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(function(checkbox) {
      checkbox.checked = false;
    });
  }

  function checkIfAllChecked(boutonRadioValue) {
    let checkboxes = document.querySelectorAll('input[type="checkbox"]');
    let checkedCheckboxes = 0;
    checkboxes.forEach(function(checkbox) {
        if (checkbox.checked && checkbox.name === 'checkbox-1' && boutonRadioValue === 'bouton-radio-1') {
          checkedCheckboxes++;
        } else if (checkbox.checked && checkbox.name === 'checkbox-2' && boutonRadioValue === 'bouton-radio-2') {
          checkedCheckboxes++;
        }
      });
      if (checkedCheckboxes > 1) {
        alert('Vous ne pouvez choisir qu\'une seule option à la fois.');
        uncheckCheckboxes();
      }
  }

  let checkboxes = document.querySelectorAll('input[type="checkbox"]');
  checkboxes.forEach(function(checkbox) {
    checkbox.addEventListener('click', function() {
      let boutonRadioValue = document.querySelector('input[type="radio"]:checked').value;
      checkIfAllChecked(boutonRadioValue);
    });
  });
</script>
<%}%>

<!-- 	<div class="articles"> -->
	<!-- Boucle foreach de la Liste article qui va imprimer tous les articles dont la date de fin d'enchere est après la date du jour présents dans la base de donnée --> 
	<% if(choix.equals("")){%>
	<form class="articles" method="get" action="./ServletDetailArticle"> 
	<div class="flex-container">
	<% for (Article current : article) {if (current.isEtatVente()==false){ %> 
	<div class="unArticle">
	<button class="bouteboute" type="submit" name="idArticle" value="<%= current.getNoArticle() %>">
        <%= current.getNomArticle() %>
   		 </button>
	<p> <%="Prix: " + current.getPrixDepart() + " " + "points"%> </p> 
	<p> <%="Fin de l'enchere: " + current.getDateFinEnchere()%> </p> 
	<p> <%="Vendeur: " + current.getUtilisateur().getPseudo()%> </p> 
	</div>
	<% }} %>
	</div>
	</form>
	<%
		imprimeChacal=true;
		}
	%>
	
	<% if(choix.equals("Toutes")){%>
		<% if (troisiemeChoix.equals("") && quatriemeChoix.equals("") && cinquiemeChoix.equals("")) {%>
		<form class="articles" method="get" action="./ServletDetailArticle">
		<div class="flex-container">
		<%for (Article current : article){if ((current.isEtatVente()==false) && current.getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase())){
			%>
			<div class="unArticle">
			<button class="bouteboute" type="submit" name="idArticle" value="<%= current.getNoArticle()%>">
       		 <%= current.getNomArticle() %>
   		 	</button>
			<p>	<%="Prix: " + current.getPrixDepart() + " " + "points"%> </p>
			<p>	<%="Fin de l'enchere: " + current.getDateFinEnchere()%> </p>
			<p>	<%="Vendeur: " + current.getUtilisateur().getPseudo()%> </p>
			</div>
		<%
		imprimeChacal=true; 
		}}}}
	%>
	</div>
		</form>
		<!-- Création de deux if selon le choix utilisateur. On passe dans le premier lorsque l'utilisateur a choisi toutes les catégories  -->
<!-- La foreach va imprimer un article si l'état de l'article est en vente et si l'article contient la valeur dans le champ de recherche de l'utilisateur -->
	<% if(choix.equals("Toutes")){%>
		<% if (troisiemeChoix.equals("achat") && quatriemeChoix.equals("encheresOuvertes")) {%>
		<form class="articles" method="get" action="./ServletDetailArticle">
		<div class="flex-container">
		<%for (Article current : article){if ((current.isEtatVente()==false) && current.getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase()) && user.getNoUtilisateur()!=current.getUtilisateur().getNoUtilisateur()){
			%>
			<div class="unArticle">
			<button class="bouteboute" type="submit" name="idArticle" value="<%= current.getNoArticle()%>">
       		 <%= current.getNomArticle() %>
   		 	</button>
			<p>	<%="Prix: " + current.getPrixDepart() + " " + "points"%> </p>
			<p>	<%="Fin de l'enchere: " + current.getDateFinEnchere()%> </p>
			<p>	<%="Vendeur: " + current.getUtilisateur().getPseudo()%> </p>
			</div>
		<%
		imprimeChacal=true; 
		}}}}
	%>
	</div>
		</form>
			
		<!-- Création du deuxième if. On passe dans le deuxième lorsque l'utilisateur a choisi une catégorie  -->
<!-- La foreach va imprimer un article si sa catégorie correspond à celle de l'utilisateur et si l'état de l'article est en vente et si l'article contient la valeur dans le champ de recherche de l'utilisateur -->
	
<% 
	 if(choix.equals("Informatique")||choix.equals("Ameublement")||choix.equals("Vetement")||choix.equals("Sport")){%>
			<% if (troisiemeChoix.equals("achat") && quatriemeChoix.equals("encheresOuvertes")) {%>
			
		<form class="articles" method="get" action="./ServletDetailArticle">
		<div class="flex-container">
		<%for (Article current : article) { if (current.getCategorie().getLibelle().equals(choix)&&(current.isEtatVente()==false)&& current.getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase())&& user.getNoUtilisateur()!=current.getUtilisateur().getNoUtilisateur()){
			%>
			<div class="UnArticle">
    	<button class="bouteboute"  type="submit" name="idArticle" value="<%= current.getNoArticle() %>">
        <%= current.getNomArticle() %>
   		 </button>
				<p>	<%="Prix: " + current.getPrixDepart() + " " + "points"%> </p>
				<p>	<%="Fin de l'enchere: " + current.getDateFinEnchere()%> </p>
				<p>	<%="Vendeur: " + current.getUtilisateur().getPseudo()%> </p>
			</div>
			<%
			imprimeChacal=true;
			}
		}%>
		</div>
		</form>
	
	
<!-- 	S'il n'y a aucune réponse à la recherche utilisateur, on imprime un message  -->
	<%if(imprimeChacal==false) {%>
	<h1><%="Aucun article trouvé pour ta recherche... Essaye autre chose"%></h1>
	<%}}} %>
	
		<!-- 	Double if qui gèrent la condition "mes encheres" selon la catégorie -->
	<% 
	if(choix.equals("Toutes")){%>
		<% if (troisiemeChoix.equals("achat") && quatriemeChoix.equals("mesEncheres")) {%>
		
		
		<%List<Integer>lstTmp=new ArrayList<>();%>
		<form class="articles" method="get" action="./ServletDetailArticle">
		<div class="flex-container">
		<% for(Enchere current: enchere) {%>
		<%if(!lstTmp.contains(current.getArticle().getNoArticle())&&(current.getArticle().isEtatVente()==false) && (current.getArticle().getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase()))){ %>
		<div class="unArticle">
		<button class="bouteboute"  type="submit" name="idArticle" value="<%= current.getArticle().getNoArticle()%>">
        <%= current.getArticle().getNomArticle() %>
   		 </button>
			<p> <%="Mon enchere: "+ current.getMontant_enchere() %></p>
			<p>	<%="Prix: " + current.getArticle().getPrixDepart() + " " + "points"%> </p>
			<p>	<%="Fin de l'enchere: " + current.getArticle().getDateFinEnchere()%> </p>
			<p>	<%="Vendeur: " + current.getArticle().getUtilisateur().getPseudo()%> </p>
			<%lstTmp.add(current.getArticle().getNoArticle()); %>
			</div>
	<% }}}}%>
	
	</div>
	</form>
	<% 
	if(choix.equals("Informatique")||choix.equals("Ameublement")||choix.equals("Vetement")||choix.equals("Sport")){%>
		<% if (troisiemeChoix.equals("achat") && quatriemeChoix.equals("mesEncheres")) {%>
		
		<%List<Integer>lstTmp=new ArrayList<>();%>
		<form class="articles" method="get" action="./ServletDetailArticle">
				<div class="flex-container">
		
		<% for(Enchere current: enchere) {%>
		<%if((!lstTmp.contains(current.getArticle().getNoArticle())&&(current.getArticle().getCategorie().getLibelle().equals(choix)&&(current.getArticle().isEtatVente()==false)&& (current.getArticle().getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase()))))){ %>
		
			<div class="unArticle">
		<button class="bouteboute"  type="submit" name="idArticle" value="<%= current.getArticle().getNoArticle()%>">
        <%= current.getArticle().getNomArticle() %>
   		 </button>
			<p> <%="Mon enchere: "+ current.getMontant_enchere() %></p>
			<p>	<%="Prix: " + current.getArticle().getPrixDepart() + " " + "points"%> </p>
			<p>	<%="Fin de l'enchere: " + current.getArticle().getDateFinEnchere()%> </p>
			<p>	<%="Vendeur: " + current.getArticle().getUtilisateur().getPseudo()%> </p>
			<%lstTmp.add(current.getArticle().getNoArticle()); %>
			</div>
	<% }}}}%>
	
	</div>
	</form>
	
<!-- 	Double if qui gèrent la condition "mes encheres remportees" selon la catégorie -->
	<% 
	if(choix.equals("Toutes")){%>
		<% if (troisiemeChoix.equals("achat") && quatriemeChoix.equals("mesEnchereRemportees")) {%>
		
		<%List<Integer>lstTmp=new ArrayList<>();%>
		<form class="articles" method="get" action="./ServletDetailArticle">
		<div class="flex-container">
		<% for(Enchere current: enchere) {%>
		<%if(!lstTmp.contains(current.getArticle().getNoArticle())&&(current.getArticle().isEtatVente()==true) && 
				(current.getArticle().getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase()))){ %>
		<div class="unArticle">
		<button class="bouteboute"  type="submit" name="idArticle" value="<%= current.getArticle().getNoArticle()%>">
        <%= current.getArticle().getNomArticle() %>
   		 </button>
			<p> <%="Mon enchere: "+ current.getMontant_enchere() %></p>
			<p>	<%="Prix: " + current.getArticle().getPrixDepart() + " " + "points"%> </p>
			<p>	<%="Fin de l'enchere: " + current.getArticle().getDateFinEnchere()%> </p>
			<p>	<%="Vendeur: " + current.getArticle().getUtilisateur().getPseudo()%> </p>
			<%lstTmp.add(current.getArticle().getNoArticle()); %>
			</div>
	<% }}}}%>
	
	</div>
	</form>
	
	<% 
	if(choix.equals("Informatique")||choix.equals("Ameublement")||choix.equals("Vetement")||choix.equals("Sport")){%>
		<% if (troisiemeChoix.equals("achat") && quatriemeChoix.equals("mesEncheresRemportees")) {%>
		
		<%List<Integer>lstTmp=new ArrayList<>();%>
		<form class="articles" method="get" action="./ServletDetailArticle">
				<div class="flex-container">
		
		<% for(Enchere current: enchere) {%>
		<%if((!lstTmp.contains(current.getArticle().getNoArticle())&&(current.getArticle().getCategorie().getLibelle().equals(choix)&&(current.getArticle().isEtatVente()==true)&& (current.getArticle().getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase()))))){ %>
		<div class="unArticle">
		<button class="bouteboute"  type="submit" name="idArticle" value="<%= current.getArticle().getNoArticle()%>">
        <%= current.getArticle().getNomArticle() %>
   		 </button>
			<p> <%="Mon enchere: "+ current.getMontant_enchere() %></p>
			<p>	<%="Prix: " + current.getArticle().getPrixDepart() + " " + "points"%> </p>
			<p>	<%="Fin de l'enchere: " + current.getArticle().getDateFinEnchere()%> </p>
			<p>	<%="Vendeur: " + current.getArticle().getUtilisateur().getPseudo()%> </p>
			<%lstTmp.add(current.getArticle().getNoArticle()); %>
			</div>
	<% }}}}%>
	
	</div>
	</form>
	
<!-- 	double if pour gérer "mes ventes en cours" selon la catégorie -->
	
	<% if(choix.equals("Toutes")){%>
		<% if (troisiemeChoix.equals("mesVentes") && cinquiemeChoix.equals("mesVentesEnCours")) {%>
		<form class="articles" method="get" action="./ServletDetailArticle">
		<div class="flex-container">
		<%for (Article current : article){if ((current.getDateDebutEnchere().isBefore(LocalDate.now())||current.getDateDebutEnchere().isEqual(LocalDate.now()))&&(current.isEtatVente()==false) && current.getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase()) && user.getNoUtilisateur()==current.getUtilisateur().getNoUtilisateur()){
			%>
			<div class="unArticle">
			<button class="bouteboute" type="submit" name="idArticle" value="<%= current.getNoArticle()%>">
       		 <%= current.getNomArticle() %>
   		 	</button>
			<p>	<%="Prix: " + current.getPrixDepart() + " " + "points"%> </p>
			<p>	<%="Fin de l'enchere: " + current.getDateFinEnchere()%> </p>
			<p>	<%="Vendeur: " + current.getUtilisateur().getPseudo()%> </p>
			</div>
		<%
		imprimeChacal=true; 
		}}}}
	%>
	</div>
		</form>
<% 
	 if(choix.equals("Informatique")||choix.equals("Ameublement")||choix.equals("Vetement")||choix.equals("Sport")){%>
			<% if (troisiemeChoix.equals("mesVentes") && cinquiemeChoix.equals("mesVentesEnCours")) {%>
			
		<form class="articles" method="get" action="./ServletDetailArticle">
		<div class="flex-container">
		<%for (Article current : article) { if ((current.getDateDebutEnchere().isBefore(LocalDate.now())||current.getDateDebutEnchere().isEqual(LocalDate.now())) && current.getCategorie().getLibelle().equals(choix)&&(current.isEtatVente()==false)&& current.getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase())&& user.getNoUtilisateur()==current.getUtilisateur().getNoUtilisateur()){
			%>
			<div class="UnArticle">
    	<button class="bouteboute"  type="submit" name="idArticle" value="<%= current.getNoArticle() %>">
        <%= current.getNomArticle() %>
   		 </button>
				<p>	<%="Prix: " + current.getPrixDepart() + " " + "points"%> </p>
				<p>	<%="Fin de l'enchere: " + current.getDateFinEnchere()%> </p>
				<p>	<%="Vendeur: " + current.getUtilisateur().getPseudo()%> </p>
			</div>
			<%
			imprimeChacal=true;
			}
		}%>
		</div>
		</form>
<!-- 	S'il n'y a aucune réponse à la recherche utilisateur, on imprime un message  -->
	<%if(imprimeChacal==false) {%>
	<h1><%="Aucun article trouvé pour ta recherche... Essaye autre chose"%></h1>
	<%}}} %>
	
	<!-- 	double if pour gérer "mes ventes non débutées" selon la catégorie -->
	
	<% if(choix.equals("Toutes")){%>
		<% if (troisiemeChoix.equals("mesVentes") && cinquiemeChoix.equals("mesVentesNonDebutees")) {%>
		<form class="articles" method="get" action="./ServletDetailArticle">
		<div class="flex-container">
		<%for (Article current : article){if (current.getDateDebutEnchere().isAfter(LocalDate.now()) && (current.isEtatVente()==false) && current.getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase()) && user.getNoUtilisateur()==current.getUtilisateur().getNoUtilisateur()){
			%>
			<div class="unArticle">
			<button class="bouteboute" type="submit" name="idArticle" value="<%= current.getNoArticle()%>">
       		 <%= current.getNomArticle() %>
   		 	</button>
			<p>	<%="Prix: " + current.getPrixDepart() + " " + "points"%> </p>
			<p>	<%="Fin de l'enchere: " + current.getDateFinEnchere()%> </p>
			<p>	<%="Vendeur: " + current.getUtilisateur().getPseudo()%> </p>
			</div>
		<%
		imprimeChacal=true; 
		}}}}
	%>
	</div>
		</form>
<% 
	 if(choix.equals("Informatique")||choix.equals("Ameublement")||choix.equals("Vetement")||choix.equals("Sport")){%>
			<% if (troisiemeChoix.equals("mesVentes") && cinquiemeChoix.equals("mesVentesNonDebutees")) {%>
			
		<form class="articles" method="get" action="./ServletDetailArticle">
		<div class="flex-container">
		<%for (Article current : article) { if (current.getDateDebutEnchere().isAfter(LocalDate.now())&& current.getCategorie().getLibelle().equals(choix)&&(current.isEtatVente()==false)&& current.getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase())&& user.getNoUtilisateur()==current.getUtilisateur().getNoUtilisateur()){
			%>
			<div class="UnArticle">
    	<button class="bouteboute"  type="submit" name="idArticle" value="<%= current.getNoArticle() %>">
        <%= current.getNomArticle() %>
   		 </button>
				<p>	<%="Prix: " + current.getPrixDepart() + " " + "points"%> </p>
				<p>	<%="Fin de l'enchere: " + current.getDateFinEnchere()%> </p>
				<p>	<%="Vendeur: " + current.getUtilisateur().getPseudo()%> </p>
			</div>
			<%
			imprimeChacal=true;
			}
		}%>
		</div>
		</form>
<!-- 	S'il n'y a aucune réponse à la recherche utilisateur, on imprime un message  -->
	<%if(imprimeChacal==false) {%>
	<h1><%="Aucun article trouvé pour ta recherche... Essaye autre chose"%></h1>
	<%}}} %>
	
	
	<!-- 	double if pour gérer "mes ventes terminées" selon la catégorie -->
	
	<% if(choix.equals("Toutes")){%>
		<% if (troisiemeChoix.equals("mesVentes") && cinquiemeChoix.equals("mesVentesTerminees")) {%>
		<form class="articles" method="get" action="./ServletDetailArticle">
		<div class="flex-container">
		<%for (Article current : allArticleByUser){if ((current.isEtatVente()==true) && current.getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase()) && user.getNoUtilisateur()==current.getUtilisateur().getNoUtilisateur()){
			%>
			<div class="unArticle">
			<button class="bouteboute" type="submit" name="idArticle" value="<%= current.getNoArticle()%>">
       		 <%= current.getNomArticle() %>
   		 	</button>
			<p>	<%="Prix: " + current.getPrixDepart() + " " + "points"%> </p>
			<p>	<%="Fin de l'enchere: " + current.getDateFinEnchere()%> </p>
			<p>	<%="Vendeur: " + current.getUtilisateur().getPseudo()%> </p>
			</div>
		<%
		imprimeChacal=true; 
		}}}}
	%>
	</div>
		</form>
<% 
	 if(choix.equals("Informatique")||choix.equals("Ameublement")||choix.equals("Vetement")||choix.equals("Sport")){%>
			<% if (troisiemeChoix.equals("mesVentes") && cinquiemeChoix.equals("mesVentesTerminees")) {%>
			
		<form class="articles" method="get" action="./ServletDetailArticle">
		<div class="flex-container">
		<%for (Article current : allArticleByUser) { if (current.getCategorie().getLibelle().equals(choix)&&(current.isEtatVente()==true)&& current.getNomArticle().toLowerCase().contains(deuxiemeChoix.toLowerCase())&& user.getNoUtilisateur()==current.getUtilisateur().getNoUtilisateur()){
			%>
			<div class="UnArticle">
    	<button class="bouteboute"  type="submit" name="idArticle" value="<%= current.getNoArticle() %>">
        <%= current.getNomArticle() %>
   		 </button>
				<p>	<%="Prix: " + current.getPrixDepart() + " " + "points"%> </p>
				<p>	<%="Fin de l'enchere: " + current.getDateFinEnchere()%> </p>
				<p>	<%="Vendeur: " + current.getUtilisateur().getPseudo()%> </p>
			</div>
			<%
			imprimeChacal=true;
			}
		}%>
		</div>
		</form>
<!-- 	S'il n'y a aucune réponse à la recherche utilisateur, on imprime un message  -->
	<%if(imprimeChacal==false) {%>
	<h1><%="Aucun article trouvé pour ta recherche... Essaye autre chose"%></h1>
	<%}}} %>
	
	
<!-- 	</div> -->
	</div>
</body>
</html>




