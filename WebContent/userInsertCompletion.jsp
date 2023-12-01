うごかないよーーーーーーーー

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>顧客登録完了画面</title>
	<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>

<div class="container">
	<h1>お客様情報の登録が完了いたしました</h1>

<%
    // セッションから管理者名を取得
    User user = (User)(session.getAttribute("userInfo"));
    String usrName = "";
    String usrId = "";
    		
    if (user != null) {
    	usrName = user.getUsrName();
    	usrId = user.getUsrId();
%>
<%-- 氏名と顧客IDを表示 --%>
	<h2><%= usrName %>様のお客様IDは<%= usrId %>でございます。</h2>
    <h3>ログインの際に必要となりますので大切に保管してください。</h3>
<%
｝
%>
<br />
<!-- 戻るボタン -->
    <form action="userIndex.jsp" method="post">
        <input type="submit" value="戻る" />
    </form>
    
</div>
</body>
</html>