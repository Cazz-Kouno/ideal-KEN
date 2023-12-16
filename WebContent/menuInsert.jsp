<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*,controller.*"%>
<%-- <%@ page import="java.util.logging.Logger" %> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規メニュー登録画面</title>

<style>body{
            background-image:url(/ideal/img/レストラン7.jpg);
    		 background-size:100% auto;
            }
        table {
            width: 500px;
            text-align:center;
            background:linear-gradient(red,white,white,green);
            align:center;
            margin: auto;
            }
        html{
            min-height:500px;
            }
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px gray solid;
            }
		th{background-color: Red;}
        h1 {
	       background-color: Red;
	       text-align: center;
           }
           
           div{
           text-align: center;
           }
</style>>

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
		if (i >= obj.orderFlg.length) {
			msg += "オーダーの可否をチェックしてください\n";
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
</head>
<body>
	<%
	String message = (String) request.getAttribute("msg");
	int count = 1;
	if (message != null && !message.isEmpty()) {
	%>

	<div>
		<p><%=message%></p>
	</div>
	<%
	}
	%>
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
			<h1>新しいメニューを追加</h1>
		</td>
		</tr>
		<form id="frm1" name="frm1"
			action="/ideal/controller/MenuOperationSvl" method="post"
			onsubmit="return dataCheck(this);">

			<tr>
				<th>メニュー名*</th>
				<td><input type="text" name="menuName" size="30"
					style="ime-mode: active; width:98%;"></td>

			</tr>
			<tr>
				<th>価格*</th>
				<td><input type="text" name="price" size="10"
					style="ime-mode: inactive"></td>
			</tr>

			<tr>
				<th>オーダー可*</th>
				<td><input type="radio" name="orderFlg" value="1" checked />可
					<input type="radio" name="orderFlg" value="0" />不可</td>
			</tr>

			<tr>
				<th>分類</th>
				<td><select name="typeId">
						<%
						// MenuInsertsvlt.java から
						// このキャスト必要か？必要です
						for (MenuType mt : (ArrayList<MenuType>) mType) {
						%>

						<%
						String selected = "";
						if (typeId == mt.getTypeId()) {
							selected = "selected";
						} else {
							selected = "";
						}
						%>
						<option value="<%=mt.getTypeId()%>" <%=selected%>>
							<%=mt.getTypeName()%>
						</option>
						<%
						}
						%>
				</select></td>
			</tr>

			<tr>
				<th>コメント</th>
					<td><textarea name="detail"  
					       rows="8" style="overflow-y:scroll; 
					                  ime-mode: inactive; width:98%;" >
					    </textarea></td>
						</tr>
			<input type="hidden" name="mode" value="<%=MenuOperationSvl.INSERT%>" />
			<tr align="right">
				<td colspan="2" style="text-align: right; background-color: Red;">※は必修入力です。<input type="submit" value="登録"></td>
			</tr>
	</table>
	</form>
	<div align="center">
	<p>
		<a href="MenuMaintenanceSvl?typeId=<%=typeId%>">
<font color="Red">一覧表示に戻る</font></a>
	</div>
</body>
</html>





