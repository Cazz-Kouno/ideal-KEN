<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
User user = User.getUser(11);
System.out.print(user.getUsrId());
session.setAttribute("userInfo",user);

%>
</head>
<body onload="location.href='userDelete.jsp'">

</body>
</html>