<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<%
User user = new User();
user.setUsrId(1);
user.setUsrName("ito");
session.setAttribute("userInfo", user);
%>
</head>
<body onload="location.href='/ideal/controller/ReserveListSvl'">

</body>
</html>