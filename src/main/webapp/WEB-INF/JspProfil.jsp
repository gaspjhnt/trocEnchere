<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%String pseudo=(String) request.getAttribute("pseudo");
	String nom=(String) request.getAttribute("nom");
	String prenom=(String) request.getAttribute("prenom");%>
	

	<p><%= pseudo%></p>
	<p><%= nom%></p>
	<p><%= prenom%></p>
	
</body>
</html>