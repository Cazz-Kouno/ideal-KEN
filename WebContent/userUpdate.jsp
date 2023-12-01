<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客情報変更画面</title>

<style>
        table {
            width: 100%;}
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px gray solid;
</style>
</head>
<body>
<body>
<h1>お客様情報変更</h1>
	
<p>ここにメッセージを表示</p>
	
<form id="frm1" name="frm1" action="./ex12.html" method="get">
<table>

<tr>
<th>お客様ＩⅮ</th>
</tr>

<tr>
<th>お名前</th>
<td>
<input type="text" name="name" size="40" style="ime-mode: active">
</td>
</tr>
			
<tr>
<th>住所</th>
<td>
<input type="text" name="address" size="50" style="ime-mode: active">

</td>
</tr>	

<tr>
<th>電話番号</th>
<td>
<input type="text" name="phone" size="15" style="ime-mode: inactive">
</td>
</tr>

<tr>
<th>e-mail</th>
<td>
<input type="text" name="mail" size="50" style="ime-mode:inactive">
</td>
</tr>

<tr>
<th>パスワード</th>
<td>
<input type="text" name="password" size="15" style="ime-mode: inactive">
</td>
</tr>

<tr>
<td colspan="2" style="text-align;right;"><input type="submit" value="変更"></td>
</tr>
</table>
</form>
<p><a href="jsp">処理メニューに戻る</a></p>