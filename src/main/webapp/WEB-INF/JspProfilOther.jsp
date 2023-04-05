<%@page import="java.util.List"%>
<%@page import="fr.eni.ecole.trocenchere.bo.Categorie" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Utilisateur" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon profil</title>
 <link rel="stylesheet" type="text/css" href="Profil.css">
</head>
<body>

<%List<String> lstErreurs = (List<String>)request.getAttribute("lstErreurs");
	if(lstErreurs != null) {
		for(String message : lstErreurs){
			%> <p><%=message%></p>
			<%
		}
	}%>
	
<jsp:include page="NavBarConnecte.html"></jsp:include>	

	<%
	Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
	
	String pseudo=(String) request.getAttribute("pseudo");
	String nom=(String) request.getAttribute("nom");
	String prenom=(String) request.getAttribute("prenom");
	String email=(String) request.getAttribute("email");
	String telephone=(String) request.getAttribute("telephone");
	String rue=(String) request.getAttribute("rue");
	String codepostal=(String) request.getAttribute("codepostal");
	String ville=(String) request.getAttribute("ville");
	%>
	<div class="container">
	<div class="card">
	<div class="titre">
	<h1>Mon Profil</h1>
	</div>
	<div class="infos">
	<div class="moninfo">
	<h3>Pseudo:</h3> <p><%=user.getPseudo()%></p>
	</div>
	<div class="moninfo">
	<h3>Nom:</h3> <p><%= user.getNom()%></p>
	</div>
	<div class="moninfo">
	<h3>Prenom:</h3> <p><%= user.getPrenom()%></p>
	</div>
	<div class="moninfo">
	<h3>Email:</h3> <p><%=user.getEmail()%></p>
	</div>
	<div class="moninfo">
	<h3>Telephone:</h3> <p><%=user.getTelephone()%></p>
	</div>
	<div class="moninfo">
	<h3>Rue:</h3> <p><%=user.getRue()%></p>
	</div>
	<div class="moninfo">
	<h3>Code postal:</h3> <p><%=user.getCodePostal()%></p>
	</div>
	<div class="moninfo">
	<h3>Ville:</h3> <p><%=user.getVille()%></p>
	</div>
	</div>
	
	<div class="bouteboute">
	<form id="bouton" method="get" action="./ServletModificationProfil">
	<input class="boute" type="submit" value="Modifier">
	</form>
	</div>
	
	</div>
	</div>
</body>
</html>