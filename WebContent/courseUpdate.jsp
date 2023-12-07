<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コース情報変更画面</title>

<style>body{
            background-image:url(/ideal/img/レストラン15.jpg);
    		 background-size:100% auto;
        
        }


        table {
            width: 100%;}
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px gray solid;
            }
h1{background-color:Red;}
th{background-color:Red;}
</style>

</head>


<body>
<table>
<td colspan="2">
<h1>コースの更新</h1>
	
<p>ここにメッセージを表示</p>
	
<form id="frm1" name="frm1" action="./ex12.html" method="get">


<tr>
<th>コース名</th>
<td>
<input type="text" name="courseName" size="30" style="ime-mode: active">
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
<th>コメント</th>

<td>
<input type="textarea" name="detail" size="50" style="ime-mode: active">
</td>
</tr>

<tr>
<th>前菜</th>
<td>
<input type="select" name="appetizerId" size="30" style="ime-mode:active">
</td>
</tr>

<tr>
<th>スープ</th>
<td>
<input type="select" name="soupId" size="30" style="ime-mode:active">
</td>
</tr>

<tr>
<th>パスタ</th>
<td>
<input type="select" name="pastaId" size="30" style="ime-mode:active">
</td>
</tr>

<tr>
<th>肉料理</th>
<td>
<input type="select" name="meetId" size="30" style="ime-mode:active">
</td>
</tr>

<tr>
<th>魚料理</th>
<td>
<input type="select" name="fishId" size="30" style="ime-mode:active">
</td>
</tr>

<tr>
<th>デザート</th>
<td>
<input type="select" name="dessertId" size="30" style="ime-mode:active">
</td>
</tr>

<tr>
<td colspan="2" style="text-align;right;"><input type="submit" value="更新"></td>
</tr>


</table>
</form>
<p><a href="home.jsp">一覧表示に戻る</a></p>


</body>
</html>



