<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*,controller.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>メニュー情報削除画面</title>

<style>
body {
	background-image: url(/ideal/img/レストラン27.jpg);
	background-size: 100% auto;
}

table {
	width: 500px;
	
	margin: auto;
}

th, td {
	padding: 8px;
	text-align: left;
	border: 1px gray solid;
}
</style>

</head>

<style>
h1 {
	background-color: Red;
	text-align: center;
}

th {
	background-color: Red;
}

div{
text-align: center;
}

</style>

<body>
	<%
	request.setCharacterEncoding("UTF-8");
	%>
	<jsp:useBean id="oneMenu" class="model.Menu" scope="request" />
	<%
	int typeId = oneMenu.getTypeId();
	%>
	<table>
	<td colspan="2">
		<h1>メニューの削除</h1>
		<form action="/ideal/controller/MenuOperationSvl" method="post">
			<tr>
				<th>メニュー名</th>
				<td><jsp:getProperty name="oneMenu" property="menuName" /></td>
			</tr>
			<tr>
				<th>価格</th>
				<td><jsp:getProperty name="oneMenu" property="price" /></td>
			</tr>
			<tr>
				<th>オーダー可</th>
				<td><input type="radio" name="orderFlg" value="1" checked />可
					<input type="radio" name="orderFlg" value="0" />不可</td>
			</tr>
			>
		<tr>
			<th>分類</th>
			<td><jsp:getProperty name="oneMenu" property="typeName" /></td>
		</tr>
		<tr>
			<th>コメント</th>
			<td><textarea name="detail" cols="45" rows="6"><%=oneMenu.getDetail()%>
			</textarea>
			</td>
		</tr>
		<input type="hidden" name="mode" value="<%=MenuOperationSvl.DELETE%>" />
		<input type="hidden" name="typeId"
			value='<jsp:getProperty name = "oneMenu" property = "typeId"/>' />
		<input type="hidden" name="menuId"
			value='<jsp:getProperty name = "oneMenu" property = "menuId"/>' />
		<tr>
			<th colspan="2" class="bottom"><input type="submit"
				value="メニューを削除" /></th>
		</tr>
		</form>
	</table>
	<p>
		<div><a href="MenuController?typeId=<%=typeId%>">一覧表示画面へ戻る</a></div>
	</p>
</body>
</html>






