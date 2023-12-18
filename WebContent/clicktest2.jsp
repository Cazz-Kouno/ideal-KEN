<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
var viewState = [1,1];
function changeView(num){
	viewState[num] = (viewState[num] + 1) % 2;
	var cont = document.getElementById("content["+num+"]")
	if(viewState[num] == "0"){
		cont.style.display = "none";
	}else{
		cont.style.display = "block";
	}
}
</script>
<style>
table,td{
	border: 2px gray solid;
}
</style>
</head>
<body>
<table>
<%
String str[] = {"222","abc"};

for(int i = 0;i < str.length;i++){
%>
	<tr><td>
<%
	if(str[i].isEmpty()){
%>
<%= "num=" + i %>	
<%
	}else{
%>
<a href="#" class="toggleLink" onclick="changeView(<%=i%>)"><%= "num=" + i %></a>
<%		
	}
%>
	</td></tr>
	<tr><td>
	<div id="content[<%=i %>]" class="toggleContent" style="display: block;"><%=str[i] %></div>
	</td></tr>
<%

%>
<%
}
%>
</table>
</body>
</html>