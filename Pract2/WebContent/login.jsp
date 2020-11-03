<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%@ page import="es.uco.pw.display.common.Language,es.uco.pw.display.javabean.CustomerBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accediendo</title>
</head>
<body>
<% 
//Se declaran las variables fuera del condicional por motivos de ámbito
String name = "";
int random = -1;
//Se comprueba primero que el usuario no está logado
if (customerBean == null || customerBean.getLogin()=="") { %>
	Generando usuario...<br/>
<%
	// Este proceso de generación aleatoria sustituye a un formulario HTML para el acceso
	random = 1+(int)(Math.random() * 5);
	if(random == 1) name = "JohnDoe";
	if(random == 2) name = "SusanPoe";
	if(random == 3) name = "TudelaCow";
	if(random == 4) name = "PhilMarrow";
	if(random == 5) name = "MiaTuia";
%>
<jsp:setProperty property="login" value="<%=name%>" name="customerBean"/>
<jsp:setProperty property="idUser" value="<%=random%>" name="customerBean"/>
<jsp:setProperty property="userLang" value="<%=Language.English%>" name="customerBean"/>
<% } else { %>
El usuario ya est&aacute; logado. 
<% } %>
<a href="index.jsp">Volver</a>
</body>
</html>