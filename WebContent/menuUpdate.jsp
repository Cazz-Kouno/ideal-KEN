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
	background-image: url(/ideal/img/レストラン10.jpg);
	background-size: 100% auto;
}

table {
	width: 500px;
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
<script type="text/javascript">
<!--
	function dataCheck(obj) {
		var msg = "";
		if (obj.menuName.value.length < 1) {
			msg += "メニュー名を入力してください。\n";
		}
		if (!obj.price.value.match(/^[0-9]+$/g)) {
			msg += "価格を数値で入力してください。\n";
		}
		var i;
		for (i = 0; i < obj.orderFlg.length; i++) {
			if (obj.orderFlg[i].checked)
				break;
		}
		if (msg.length > 0) {
			alert(msg);
			return false;
		} else {
			return true;
		}
	}
//-->
</script>
</head>

<style>
h1 {
	background-color: green;
}

th {
	background-color: green;
}
</style>
<script type="text/javascript">
<!--
	function dataCheck(obj) {
		var msg = "";
		if (obj.menuName.value.length < 1) {
			msg += "メニュー名を入力してください。\n";
		}
		if (!obj.price.value.match(/^[0-9]+$/g)) {
			msg += "価格を数値で入力してください。\n";
		}
		var i;
		for (i = 0; i < obj.orderFlg.length; i++) {
			if (obj.orderFlg[i].checked)
				break;
		}
		if (msg.length > 0) {
			alert(msg);
			return false;
		} else {
			return true;
		}
	}
	-->
</script>
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
	<jsp:useBean id="mType" class="java.util.ArrayList" scope="request" />
	<jsp:useBean id="oneMenu" class="model.Menu" scope="request" />

	<table>
		<td colspan="2">
			<h1>メニューの更新</h1>

			<p>ここにメッセージを表示</p>

			<form id="frm1" name="frm1"
				action="/ideal/controller/MenuOperationSvl" method="post"
				onsubmit="return dataCheck(this);">


				<tr>
					<th>メニュー名</th>
					<td><input type="text" name="menuName" size="30"
						value='<jsp:getProperty name = "oneMenu" property = "menuName"/>'
						style="ime-mode: active"></td>
				</tr>

				<tr>
					<th>価格</th>
					<td><input type="text" name="price" size="6"
						value='<jsp:getProperty name = "oneMenu" property = "price"/>'
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
			<td><select name="typeId">
					<%
					for (Object o : mType) {
						MenuType mt = (MenuType) o;
						String selected = "";
						if (typeId == mt.getTypeId()) {
							selected = "selected";
						} else {
							selected = "";
						}
					%>
					<option value="<%=mt.getTypeId()%>" <%=selected%>>
						<%=((MenuType) o).getTypeName()%></option>
					<%
					}
					%>
			</select></td>
		</tr>

		<tr>
			<th>コメント</th>
			<td><input type="textarea" name="detail" size="30" value='<%=oneMenu.getDetail()%>'
				style="ime-mode: active"></td>
		</tr>
		<input type="hidden" name="mode" value="<%=MenuOperationSvl.UPDATE%>" />
		<input type="hidden" name="menuId"
			value='<jsp:getProperty name = "oneMenu" property = "menuId"/>' />
	    <input type="hidden" name="typeId"
			value='<jsp:getProperty name = "oneMenu" property = "typeId"/>' />
		<tr>
			<td colspan="2" style=""><input type="submit" value="更新"></td>
		</tr>

		</form>
	</table>
	<p>
		<a href="/ideal/controller/menuMaintenance">一覧表示に戻る</a>
	</p>


</body>
</html>







