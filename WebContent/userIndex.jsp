<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員処理選択画面</title>
<link rel="stylesheet" type="text/css" href="login.css" />
<style>
body{text-align:center;
	background-image:url(/ideal/img/レストラン4.jpg);
	background-size:100% auto;
	background:linear-gradient(red,white,green);}
html{min-height:100%;}
</style>
</head>
<body>
<h1>■ Restaurante IDEALLE ■</h1>
<%
    // セッションからユーザーIDを取得
    User user = (User)(session.getAttribute("userInfo"));
	String usrName = null;
    		
    if (user != null) {
    	usrName = user.getUsrName();
%>
    <h2><%= usrName %>様、いらっしゃいませ。</h2>
<%
	} else {
    // セッションが切れている場合はログイン画面に遷移
        response.sendRedirect("../userLogin.jsp");
    }

    // メッセージがnullでない場合にのみ表示
    String message = (String) request.getAttribute("msg");
    if (message != null && !message.isEmpty()) {
%>
 <div>
     <p><%= message %></p>
 </div>
<%
 }
%>
<form name="UserloginForm" action="/ideal/controller/UserLoginSvl" method="post" 
       											 onsubmit="return dataCheck(this);">

<p><a href="/ideal/controller/ShowMenuSvl">●メニュー紹介</a></p>
<p><a href="/ideal/controller/ReserveListSvl">●ご予約</a></p>
<p><a href="/ideal/controller/UserUpdateSvl">●お客様情報変更</a>
<p><a href="/ideal/controller/UserDeleteSvl">●お客様脱会手続き</a></p>
<p><a href="/ideal/controller/UserLogoffSvl">●ログオフ</a></p>
</form>

</body>
</html>