<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<a href="#" class="toggleLink" data-target="content<%=i%>"><%= "num=" + i %></a>
<%		
	}
%>
	</td></tr>
	<tr><td>
	<div id="content<%=i %>" class="toggleContent" style="display: block;"><%=str[i] %></div>
	</td></tr>
<%

%>
<%
}
%>
</table>
</body>
</html>