<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
reserveList
</style>
<JSP:useBean id="reserveList_esc" class="java.util.ArrayList" scope="session"/>
<JSP:useBean id="userInfo_esc" class="ideal.model.User" scope="session"/>
<% 
User userInfo = new User();
userInfo.setUsrName("ito");
ArrayList<Reserve> reserveList = Reserve.getReserveList(1);
%>
</head>
<body>
<h1><%=userInfo.getUsrName() %>様、ご予約一覧</h1>
	
<p>ここにメッセージを表示</p>
	
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
<td><%= reserve.getRsvId() %></td>
<td>
<%
int year = reserve.getRsvYy();
int month = reserve.getRsvMm();
int day = reserve.getRsvDd();
int hour = reserve.getRsvHh();
int minute = reserve.getRsvMi();

String formattedDateTime = String.format("%04d年%02d月%02d日 %02d時%02d分", year, month, day, hour, minute);
%>
<%= formattedDateTime %>
</td>
<td><%= reserve.getPerson() %></td>
<td><%= reserve.getCourseName() %></td>
<td><%= reserve.getTableName() %></td>
<td><%= reserve.getAppDate() %></td>
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
	
