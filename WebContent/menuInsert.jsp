<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規メニュー登録画面</title>

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

<style>
h1{background-color:Lightgreen;}
th{background-color:Lightgreen;}
</style>
<body>
<tr>
<table>
<td colspan="2">
<h1>新しいメニューを追加</h1>
</td>
</tr>
	

	
<form id="frm1" name="frm1" action="./ex12.html" method="get">

<p>ここにメッセージを表示</p>

<tr>
<th>メニュー名</th>
<td>
<input type="text" name="menuName" size="30" style="ime-mode: active">
</td>
</tr>

<tr>
<th>価格</th>
<td>
<input type="text" name="price" size="6" style="ime-mode: inactive">
</td>
</tr>
			
<tr>
<th>オーダー可</th>
<td>
<input type="radio" name="orderFlg" size="50" style="ime-mode: active">

</td>
</tr>	

<tr>
<th>分類</th>
<td>
<input type="select" name="typeId" size="15" style="ime-mode: inactive">
</td>
</tr>

<tr>
<th>コメント</th>
<td>
<input type="textarea" name="detail" size="30" style="ime-mode:active">
</td>
</tr>

<tr>
<td colspan="2" style="text-align;right;"><input type="submit" value="登録"></td>
</tr>


</table>
</form>
<p><a href="home.jsp">一覧表示に戻る</a></p>


</body>
</html>







