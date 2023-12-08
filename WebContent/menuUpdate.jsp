<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*,controller.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー情報変更画面</title>

<style>
body {
	background-image: url(./img/レストラン10.jpg);
	background-size: 100% auto;
}

table {
	width: 100%;
	text-align: center;
	background: linear-gradient(red, white,);
}

html {
	min-height: 100%;
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
	background-color: green;
}

th {
	background-color: green;
}
</style>

<body>
	<%
	request.setCharacterEncoding("UTF-8");
	int typeId;
	try {
		typeId = Integer.parseInt(request.getParameter("typeId"));
	} catch (NumberFormatException e) {
		typeId = 200;
	}
	%>
	<jsp:useBean id="upD_mType" class="java.util.ArrayList" scope="request" />
	<jsp:useBean id="upD_oneMenu" class="model.Menu" scope="request" />

	<table>
		<td colspan="2">
			<h1>メニューの更新</h1>

			<p>ここにメッセージを表示</p>

			<form id="frm1" name="frm1" action="./ex12.html" method="get">


				<tr>
					<th>メニュー名</th>
					<td><input type="text" name="menuName" size="30"
						style="ime-mode: active"></td>
				</tr>

				<tr>
					<th>価格</th>
					<td><input type="text" name="price" size="6"
						style="ime-mode: inactive"></td>
				</tr>

				<tr>
					<th>オーダー可</th>
					<td><input type="radio" name="orderFlg" value="1" checked />可
						<input type="radio" name="orderFlg" value="0" />不可</td>
		</td>
		</tr>

		<tr>
			<th>分類</th>
			<td><input type="select" name="typeId" size="15"
				style="ime-mode: inactive"></td>
		</tr>

		<tr>
			<th>コメント</th>
			<td><input type="textarea" name="detail" size="30"
				style="ime-mode: active"></td>
		</tr>

		<tr>
			<td colspan="2" style=""><input type="submit" value="更新"></td>
		</tr>


	</table>
	</form>
	<p>
		<a href="home.jsp">一覧表示に戻る</a>
	</p>


</body>
</html>







