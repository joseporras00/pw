<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
// Retrieving the http-request parameters
// Because it is a @include, the parameter should come from index.jsp
String user = request.getParameter("login");
// The content of the header would depend on whether the user is logged in or not.
if (user == null || user.isEmpty()) { %>
<b>Login here</b>
<% } else { %>
Hello <%= user %>!!
<% } %>
<br/>
</body>
</html>



<!--  Esto va como directiva include y un mostrar item de catálogo va como jsp:include -->