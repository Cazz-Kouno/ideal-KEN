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
    	
    	
	    background-image:url(/ideal/img/レストラン19.jpg);
	    background-size:100% auto;
    	
    	
    	/* background:linear-gradient(red,white,white,green); */
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
        <td name="usrName" size="10" maxlength="20" /><font style="font-weight: bold; font-size:30px; color:green;"><%= userInfo.getUsrName() %></font>
         <font style="font-weight: bold; font-size:30px">&nbsp;様の&nbsp;ユーザーID は&nbsp; </font></td>
    </tr>
    <tr>
        <td name="usrId" size="10" maxlength="20" style="font-weight: bold;">
          <font style="font-weight: bold; font-size:30px; color:green;" ><%= userInfo.getUsrId() %></font>
            <font style="font-weight: bold; font-size:30px">&nbsp;でございます。</font></td>
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