<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー紹介</title>
<style>
body{text-align:center;}
table{width:100%;}
</style>

<%-- ArrayList<Course> courseList = (ArrayList<Course>)session.getAttribute("courseList"); --%>
<% ArrayList<Course> courseList = Course.getCourseList(); %>
<% ArrayList<Menu> menuList = (ArrayList<Menu>)(session.getAttribute("menuList")); %>
<% int count = 0;boolean change = false; %>
<% String admininfo = String.valueOf(session.getAttribute("adminInfo")); %>
</head>
<body>

<h1>メニュー紹介</h1>
<h1>■コース料理</h1>

.border{
   max-width:300px
   }
   

<%
for(Course list_id:courseList){ 
	if(list_id.getCourseId() != count){
		change = true;
		count = list_id.getCourseId();
	}
	if(change == true){
		if(count != 0){
%>
</table>
<%
	}
%>
  <table> 
	<tr>
		<td><%= list_id.getCourseName() %></td>
		<td rawspan="2"><%= list_id.getPrice() %></td>
	</tr>
	<tr>
		<td padding="20px"><%= list_id.getDetail() %></td>
	</tr> 
<%
	}
	change = false;
%>	
	<tr>
		<td colspan="2"><%= list_id.getMenuName() %></td>
	</tr>
<% 	
}
%>
</table>
<%
count = 0;
%>

<h1>■一品料理</h1>
<%
for(Menu id:menuList){
	if(id.getTypeId() != count){
		change = true;
		count = id.getTypeId();
	}
	if(change == true){
		if(count != 0){
%>
</table>
<%
		}
%>
<table>
	<tr>
		<td colspan="2"><%= id.getTypeName() %></td>
	</tr>
<%
	}
	change = false;
%>
	<tr>
		<td><%= id.getMenuName() %></td>
		<td rowspan="2"><%= id.getPrice() %></td>
	</tr>
	<tr>
		<td padding="20px"><%= id.getDetail() %></td>
	</tr>
<% 	
}
%>
</table>
<br>
<p><a href="/ideal/home.jsp">[戻る]</a></p>
</body>
</html>