<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー情報削除画面</title>

<style>body{
            background-image:url(/ideal/img/レストラン27.jpg);
    		 background-size:100% auto;
        
        }


        table {
            width: 500px;}
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px gray solid;
            }
</style>

</head>

<style>
h1{background-color:Red;}
th{background-color:Red;}
</style>

<body>
<body>
<table>
<td colspan="2">
<h1>メニューの削除</h1>
	
<p>ここにメッセージを表示</p>
	
<form id="frm1" name="frm1" action="./ex12.html" method="get">


<tr>
<th>メニュー名</th>
<td>
フォアグラのロースト
</td>
</tr>

<tr>
<th>価格</th>
<td>
\3,000
</td>
</tr>
			
<tr>
<th>オーダー可</th>
<td>
オーダー可
</td>
</tr>	

<tr>
<th>分類</th>
<td>
前菜
</td>
</tr>

<tr>
<th>コメント</th>
</tr>

<tr>
<td colspan="2" style="text-align;right;"><input type="submit" value="削除"></td>
</tr>


</table>
</form>
<p><a href="home.jsp">一覧表示に戻る</a></p>


</body>
</html>






