<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*,controller.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>顧客登録完了画面</title>
    <style>    
    	body{text-align:center;
    	background:linear-gradient(red,white,white,green);
    	}

    	html{
    	min-height:100%;
    	}
    </style> 
		
	<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>

<div class="container">
	<h1>お客様情報の登録が完了いたしました。</h1>

<jsp:useBean id="userInfo" class="model.User" scope="session"/>
    <form name="userInsertCompletionForm" action="/ideal/controller/UserOperationSvl" method="post">
    <tr>
        <td><input type="text" name="usrName" size="10" maxlength="20"  placeholder=<%= userInfo.getUsrName() %> ;/>
         様のユーザーIDは <td>
    </tr>
    <tr>
        <td><input type="text" name="usrId" size="10" maxlength="20" style="ime-mode: active" placeholder=<%= userInfo.getUsrId() %> ;/>
         でございます。<td>
    </tr>
</form><br/>
<h2>ログインの際に必要となりますので</h2><br/>
<h2>大切に保管してください。</h2><br/>
<br/>
<!-- 戻るボタン -->
    <form action="../userIndex.jsp" method="post">
        <input type="submit" value="戻る" />
</div>
</body>
</html>