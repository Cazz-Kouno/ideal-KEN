<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規予約画面</title>
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
<h1>XXX様ご予約</h1>

<p>ここにメッセージを表示</p>
	
<form id="frm1" name="frm1" action="./ex12.html" method="get">
<table>

<tr>
<th>日付</th>
<td>
???年???月???日
</td>
</tr>

<tr>
<th>時刻</th>
<td>
???年???分
</td>
</tr>
			
<tr>
<th>人数</th>
<td>
???名
</td>
</tr>	

<tr>
<th>コース</th>
</tr>

<<tr>
<td colspan="2" style="text-align;right;"><input type="submit" value="予約"></td>
</tr>


</table>
</form>
<p><a href="Servlet">予約一覧に戻る</a></p>


</body>
</html>
