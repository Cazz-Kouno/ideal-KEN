<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*,controller.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>メニュー情報削除画面</title>

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
					<td>￥<jsp:getProperty name="oneMenu" property="price" /></td>
				</tr>				
				<tr>
					<th>オーダー可</th>
					<td><%= oneMenu.getOrderFlg() == 1 ? "オーダー可":"オーダー不可" %></td>
				</tr>				
				<tr>
					<th>分類</th>
					<td><jsp:getProperty name="oneMenu" property="typeName" /></td>
				</tr>
				<tr>
					<th>コメント</th>
					<td><%=oneMenu.getDetail()%></td>
				</tr>
				<input type="hidden" name="mode"
					value="<%=MenuOperationSvl.DELETE%>" /> <input type="hidden"
					name="typeId"
					value='<jsp:getProperty name = "oneMenu" property = "typeId"/>' />
				<input type="hidden" name="menuId"
					value='<jsp:getProperty name = "oneMenu" property = "menuId"/>' />
				<th colspan="2" class="bottom" style="text-align: right;">
        		<input type="submit" value="メニューを削除" />
    			</th>
			</form>
	</table>
	<p>
	<div>
		<a href="MenuMaintenanceSvl?typeId=<%=typeId %>" style="font-weight: bold;"><font color="white">一覧表示画面へ戻る</font></a>
	</div>
	</p>
</body>
</html>






