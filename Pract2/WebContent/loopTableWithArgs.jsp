<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
// Here we get the http-request parameter, called "num", and transform it into an integer
int max = Integer.parseInt(request.getParameter("num"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Table WITH a loop</title>
</head>
<body>
<table><caption>With LOOP</caption>
<% for (int i = 1; i <= max; i++) {%>
<tr><td>Value</td><td><%= i %></td></tr>
<% } %>
</table>
</body>
</html>