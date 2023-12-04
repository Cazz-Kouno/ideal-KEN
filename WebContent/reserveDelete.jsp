<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約変更画面</title>

<style>
        table {
            width: 100%;}
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px gray solid;
            }
</style>

</head>
<body>
<body>
<h1><%=userInfo.getUsrName() %>様ご予約取消</h1>
	
<p>ここにメッセージを表示</p>
	
<form id="frm1" name="frm1" action="./ex12.html" method="get">
<table>

<tr>
<th>予約番号</th>
<td>
<%= reserve.getRsvId() %>
</td>
</tr>

<tr>
<th>日付</th>
<td>
<%
int year = reserve.getRsvYy();
int month = reserve.getRsvMm();
int day = reserve.getRsvDd();

String formattedDateTime = String.format("%04d年%02d月%02d日", year, month, day);
%>
<%= formattedDateTime %>
</td>
</tr>
			
<tr>
<th>時刻</th>
<td>
<%
int hour = reserve.getRsvHh();
int minute = reserve.getRsvMi();

String formattedDateTime = String.format("%02d時%02d分", hour, minute);
%>
<%= formattedDateTime %>
</td>
</tr>	

<tr>
<th>人数</th>
<td>
<%= reserve.getPerson() %>
</td>
</tr>

<tr>
<th>コース</th>
<td>
<%= reserve.getCourseName() %>
</td>
</tr>

<tr>
<td colspan="2" style="text-align;right;"><input type="submit" value="取消"></td>
</tr>


</table>
</form>
<p><a href="home.jsp">予約一覧に戻る</a></p>


</body>
</html>




