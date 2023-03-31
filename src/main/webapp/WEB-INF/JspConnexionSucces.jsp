
<%@page import="fr.eni.ecole.trocenchere.bo.Article" %>
<%@page import="fr.eni.ecole.trocenchere.bo.Utilisateur" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
    String message = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
//     if(cookies != null){
//         for(Cookie cookie : cookies){
// //             if(cookie.getName().equals("message")) message = cookie.getValue();
//             if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
//         }
//     }
%>
    <h3>Login Success</h3>
    <h4><%=message%></h4>
    <h4>Session ID = <%=sessionID %></h4>  
    <br><br>
    <h1>Bienvenue aux enchères</h1>
    <p><%=user.toString() %></p>
    <form action="./ServletDeconnexion" method="post">
        <input type="submit" value="Logout" >
    </form>
</body>
</html>