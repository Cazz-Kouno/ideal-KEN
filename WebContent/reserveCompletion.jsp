<%@page import="model.Reserve"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約完了画面</title>
<style>
body{text-align:center;}
</style>
</head>
<body>
<h1><%=userInfo.getUsrName() %>様、ご予約が完了いたしました。</h1>

<p><a href=""><%
//9999年99月99日　99時99分より
int year = reserve.getRsvYy();
int month = reserve.getRsvMm();
int day = reserve.getRsvDd();
int hour = reserve.getRsvHh();
int minute = reserve.getRsvMi();
String formattedDateTime = String.format("%04d年%02d月%02d日 %02d時%02d分より", year, month, day, hour, minute);
%>
<%= formattedDateTime %>
</a></p>
<p><a href="">
<%= reserve.getCourseName() 
//ＸＸＸＸＸＸＸコース　9名様
%>
&nbsp;
<%= reserve.getCourseName()%>
名様
</a></p>
<p><a href="">ご予約番号は９９番です。
<p><a href="">ご来店の際、受付にお申し付けください

</a></p>
</a></p>
<th colspan="2">予約一覧に戻る</th>





</body>
</html>