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
body{text-align:center;

background:linear-gradient(red,white,white,green);
}

html{
min-height:100%;
}
table{width:700px;
text-align:center;
margin:auto;
/*border: 2px black solid;*/
}
/*tr, td{
border: 2px black solid;

}*/
td{width:80%;

}
th{width:80%;}
td.price{width:20%;}
.border{
   max-width:300px;
   }
.price{
	text-align:center;
	font-style:oblique;
	font-weight:bold;
}
.menuType{
	background-color:orange;
}
.course{
width:500px;
text-align:left;
border: 2px black solid;
}
.menu{
	border-bottom:dotted;
}
</style>
<script>
document.addEventListener("DOMContentLoaded", function () {
var toggleLinks = document.querySelectorAll(".toggleLink");
var toggleContents = document.querySelectorAll(".toggleContent");

// すべてのtoggleLinkに対してクリックイベントを設定
toggleLinks.forEach(function (toggleLink) {
  toggleLink.addEventListener("click", function (event) {
    // デフォルトの動作を防ぐ
    event.preventDefault();

    // クリックされたリンクに関連するtargetを取得
    var targetId = toggleLink.getAttribute("data-target");
    var targetContent = document.getElementById(targetId);

    // 対象の要素の表示状態を切り替える
    if (targetContent.style.display === "none") {
      targetContent.style.display = "block";
    } else {
      targetContent.style.display = "none";
    }
  });
});
});
</script>

<%-- ArrayList<Course> courseList = (ArrayList<Course>)session.getAttribute("courseList"); --%>
<% ArrayList<Course> courseList = Course.getCourseList(); %>
<% ArrayList<Menu> menuList = (ArrayList<Menu>)(session.getAttribute("menuList")); %>
<% int count = 0;boolean change = false;int idCount = 0; %>
<% String admininfo = String.valueOf(session.getAttribute("adminInfo")); %>
</head>
<body>

<h1>メニュー紹介</h1>
<h1>■コース料理</h1>

   

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
  <table class="course"> 
	<tr>
		<th><%= list_id.getCourseName() %></th>
		<td rowspan="2" class="price">￥<%= list_id.getPrice() %></td>
	</tr>
	<tr>
		<td style="padding-left:40px;"><%= list_id.getDetail() %></td>
	</tr> 
<%
	}
	change = false;
%>	
	<tr>
		<td colspan="2" style="padding-left:80px;">・<%= list_id.getMenuName() %></td>
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
		<th colspan="2" class="menuType"><%= id.getTypeName() %></th>
	</tr>
<%
	}
	change = false;
%>
	<tr>
		<td>
		<%
		if(id.getDetail() == null || id.getDetail().isEmpty()){
		%>
		<%= id.getMenuName() %>
		<%
		}else{
		%>
		<a href="#" class="toggleLink" data-target="content<%=idCount%>"><%= id.getMenuName() %></a>
		<%
		}
		%>
		</td>
		<td class="price">￥<%= id.getPrice() %></td>
	</tr>
	<tr>
		<td colspan="2" class="menu">
			<div id="content<%=idCount %>" class="toggleContent" style="display: none;">
			<%= id.getDetail() == null ? "":id.getDetail() %></div>
		</td>
	</tr>
<% 
idCount++;
}
%>
</table>
<br>
<p><% if(session.getAttribute("userInfo") == null){ %>
<a href="../home.jsp">[戻る]</a>
<% }else{ %>
<a href="../userIndex.jsp">[戻る]</a>
<% } %>

</p>
</body>
</html>