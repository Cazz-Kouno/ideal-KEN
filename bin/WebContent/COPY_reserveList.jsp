<?xml version="1.0" encoding="UTF-8" ?>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>予約情報一覧画面</title>

<style>
table{
border:2px glay solid;
}
th{
border:2px solid glay;
}
td{
border:2px solid glay;
}
</style>
<%-- 
User userInfo = new User();
userInfo.setUsrName("ito");
ArrayList<Reserve> reserveList = Reserve.getReserveList(1);
--%>
</head>
<body>
<jsp:useBean id="reserveList" class="java.util.ArrayList" scope="session"/>
<jsp:useBean id="userInfo" class="model.User" scope="session"/>
<h1><%=userInfo.getUsrName() %>様、ご予約一覧</h1>
	
<p>ここにメッセージを表示</p>
<%= session.getId() %>	
<table>
<form id="frm1" name="frm1" action="./ex12.html" method="get">
<tr>
<td>NO</td>
<td>ご予約日時</td>
<td>人数</td>
<td>コース名</td>
<td>テーブル名</td>
<td>登録日時</td>
</tr>
<%
for(Reserve reserve:reserveList){
	// Reserve reserve = (Reserve)obj;
%>			
<tr>
<td>kari<%= reserve.getRsvId() %></td>
<td>2012年08月10日 17時00分</td>
<td>6</td>
<td>Aコース (牛肉料理)</td>
<td>ロードス</td>
<td>2012年08年9日 09時54分</td>
<td>変更</td>
<td>取消</td>
</tr>	
<%
}
%>

<tr>
<th colspan="2">新規ご予約</th>

</tr>
</form>
</table>


<p><a href="home.jsp">処理メニューに戻る</a></p>
<br>
		
		
</body>

</html>
	
