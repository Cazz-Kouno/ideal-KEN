<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,WebContent.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規メニュー登録画面</title>

<style>
h1 {
	background-color: Lightgreen;
}

th {
	background-color: Lightgreen;
}

table {
	width: 500px;
	text-align: center;
	background: linear-gradient(red, white, white, green);
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
	function dataCheck(obj) {
		// 		var msg = "";
		// 		if (obj.menuName.value.length < 1) {
		// 			msg += "メニュー名を入力してください。\n";
		// 		}
		// 		if (!obj.price.value.match(/^[0-9]+$/g)) {
		// 			msg += "価格を数値で入力してください。\n";
		// 		}
		// 		var i;
		// 		for (i = 0; i < obj.orderFlg.length; i++) {
		// 			if (obj.orderFlg[i].checked)
		// 				break;
		// 		}
		// 		if (i >= obj.orderFlg.length) {
		// 			msg += "オーダーの可否をチェックしてください\n";
		// 		}
		// 		if (msg.length > 0) {
		// 			alert(msg);
		// 			return false;
		// 		} else {
		// 			return true;
		// 		}
	}
</script>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	int typeId;
	try{
		typeId = Integer.parseInt(request.getParameter("typeId"));
	}catch(NumberFormatException e){
		typeId = 200;
	}
	%>
	<jsp:useBean id="oneMenu" class="model.Menu" scope="request" />
	<jsp:useBean id="mType" class="java.util.ArrayList" scope="request" />
	<tr>
		<table>
			<td colspan="2">
				<h1>新しいメニューを追加</h1>
			</td>
			</tr>



			<form id="frm1" name="frm1" action="MenuInsertSvl" method="post"
				onsubmit="retrun dataCheck(this);">

				<p>test</p>

				<tr>
					<th>メニュー名</th>
					<td><input type="text" name="menuName" size="30"
						value='<jsp:getProperty name="oneMenu" property = "menuName"/>'
						style="ime-mode: active"></td>
				</tr>

				<tr>
					<th>価格</th>
					<td><input type="text" name="price" size="6"
						value='<jsp:getProperty name="oneMenu" property="price" />'
						style="ime-mode: inactive"></td>
				</tr>

				<tr>
					<th>オーダー可</th>
					<%
					String[] order = { "不可", "可" };
					%>
					<td>
						<%
					for (int i = 0; i < order.length; i++) {
						String checked = "";
						if (oneMenu.getOrderFlg() == i) {
							checked = "checked";
						}
					%> <input type="radio" name="orderFlg" value="<%=i%>" <%=checked%> />
						<%=order[i]%>&nbsp;&nbsp;&nbsp; 
						<%}%>
					</td>
				</tr>

				<tr>
					<th>分類</th>
					<td><select name="typeId">
							<%
					      for(Object o: mType){ // MenuInsertsvlt.java から 
					    	  MenuType mt = (MenuType) o;
					      String selected = "";
					      if(typeId == mt.getTypeId()){
					    	  selected = "selected";
					      }else{
					    	  selected = "";
					      }
					    %>
						<option value="<%=mt.getThpeId() %>" <%=selected% %>>
							<%=((MenuType)o).getTypeName() %>
						</option>
						<%}%>
							<select /></td>
				</tr>

				<tr>
					<th>コメント</th>
					<td><input type="textarea" name="detail" size="30"
						style="ime-mode: active"></td>
				</tr>

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







