<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*,controller.*" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規コース登録画面</title>

<style>
body{
            background-image:url(./img/レストラン８.jsp);
    		 background-size:100% auto;
        
        }
table {
	width: 100%;
}

th, td {
	padding: 8px;
	text-align: left;
	border: 1px gray solid;
}
h1 {
	background-color: Lightgreen;
}

th {
	background-color: Lightgreen;
}
</style>
<%! String[] COURSE_MENU_TYPE_NAME = 
{"appetizerId","soupId","pastaId","meatId","fishId","dessertId"}; %>
</head>

<body>
<jsp:useBean id="typeMenuList" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="menuTypes" class="java.util.ArrayList" scope="request"/>

	<table>
		<td colspan="2">
			<h1>新しいコースを追加</h1>

			<p>
				<%-- メッセージがnullでない場合にのみ表示 --%>

<%
String message = (String) request.getAttribute("msg");
if (message != null && !message.isEmpty()) {
%>
			
			<div>
				<p><%=message%></p>
			</div> 
<%
 }
 %>

			</p>
			<form id="frm1" name="frm1" action="./ex12.html" method="get">

				<tr>
					<th>コース名</th>
					<td><input type="text" name="courseName" size="30"
						style="ime-mode: active"></td>
				</tr>

				<tr>
					<th>価格</th>
					<td><input type="text" name="price" size="6"
						style="ime-mode: inactive"></td>
				</tr>

				<tr>
					<th>オーダー可</th>
					<td><input type="radio" name="orderFlg" checked value="1">可
						<input type="radio" name="orderFlg" value="0">不可</td>
				</tr>

				<tr>
					<th>コメント</th>
					<td><textarea name="detail" cols="30" rows="4"
							style="ime-mode: active" placeholder="ご自由にご記入ください。"></textarea></td>
				</tr>
<%
for(int i=0;i < typeMenuList.size();i++){
	ArrayList<Menu> menuList = (ArrayList<Menu>)(((ArrayList)typeMenuList).get(i));
%>
				<tr>
					<th><%=((MenuType)((ArrayList)menuTypes).get(i)).getTypeName() %></th>
					<td><select name="<%= COURSE_MENU_TYPE_NAME[i] %>" size="30">
						<option value="">選択してください。</option>
	<%
	if(menuList != null){
		for(Menu menu:menuList){
	%>
						<option value="<%= menu.getMenuId()%>"><%= menu.getMenuName() %></option>
	<%
		}
	}
	%>
						
					</td>
				</tr>

<%	
}
%>

				<tr>
					<td colspan="2" style=""><input type="submit" value="登録"></td>
				</tr>
	</table>
	</form>
	<p>
		<a href="home.jsp">一覧表示に戻る</a>
	</p>

</body>
</html>