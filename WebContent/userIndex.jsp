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
body{text-align:center;}
</style>
</head>
<body>
<h1>■ Restaurante IDEALLE ■</h1>
<%
    // セッションからユーザーIDを取得
    User user = (User)(session.getAttribute("usrName"));
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
    String message = (String) session.getAttribute("msg");
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

<p><a href="ShowMenuSvl">●メニュー紹介</a></p>
<p><a href="ReserveListSvl">●ご予約</a></p>
<p><a href="UserUpdateSvl">●お客様情報変更
<p><a href="UserDeleteSvl">●お客様脱会手続き</a></p>
<p><a href="UserLogoffSvl">●ログオフ</a></p>
</form>

</body>
</html>