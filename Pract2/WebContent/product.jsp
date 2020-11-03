<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% String pId = request.getParameter("productId"); %>
<html>
	<head>
		<title>Display product</title>
	</head>
	<body>
		Your product is (Id. <%= pId %>)
		<% if (Integer.parseInt(pId) == 1) { %>
		a <b>banana</b>!
		<% } else if (Integer.parseInt(pId) == 2) { %>
		a <b>piece of gold</b>!!
		<% } else { %>
		a <b>super car</b>!!!
		<% } %>
	</body>
</html>

