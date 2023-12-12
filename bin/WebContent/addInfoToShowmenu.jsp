<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
User user = new User();
user.setUsrId(1);
user.setUsrName("ito");
session.setAttribute("userInfo", user);
%>
</head>
<body onload="location.href='/ideal/controller/ShowMenuSvl'">

</body>
</html>