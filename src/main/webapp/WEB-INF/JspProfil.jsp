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
<title>Insert title here</title>
</head>
<body>

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
	<h1>Mon Profil</h1>
	<p>Pseudo: <%=user.getPseudo()%></p>
	<p>Nom: <%= user.getNom()%></p>
	<p>Prenom: <%= user.getPrenom()%></p>
	<p>Email: <%=user.getEmail()%></p>
	<p>Telephone: <%=user.getTelephone()%></p>
	<p>Rue: <%=user.getRue()%></p>
	<p>Code postal: <%=user.getCodePostal()%></p>
	<p>Ville: <%=user.getVille()%></p>
	
	<form method="get" action="./ServletModificationProfil">
	<input type="submit" value="Modifier">
	</form>
	
</body>
</html>