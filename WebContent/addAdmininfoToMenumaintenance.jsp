<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
Admin admin = new Admin();
admin.setAdmName("ito");
admin.setPassword("1234");
session.setAttribute("adminInfo", admin);
%>
</head>
<body onload="location.href='/ideal/controller/MenuMaintenanceSvl'">

</body>
</html>