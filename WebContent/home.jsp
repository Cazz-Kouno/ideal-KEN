<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホームページ</title>
<style>

body{text-align:center;
background:linear-gradient(red,white,white,green);
}

html{
min-height:100%;
}
div{
background-image:url(../img/レストラン1.jpg);
background-size:100% auto;

}
</style>
</head>
<body>
<div style="font-size:70px;">■ Restaurante IDEALLE ■</div>
<p>ここにメッセージを表示</p>
<p><a href="/ideal/controller/ShowMenuSvl">●メニュー紹介</a></p>
<p><a href="/ideal/userLogin.jsp">●ログイン</a></p>
<p><a href="/ideal/userInsert.jsp">●新規お客様登録</a></p>
<br>
<p><a href="/ideal/adminLogin.jsp">●管理者ログイン</a></p>


</body>
</html>