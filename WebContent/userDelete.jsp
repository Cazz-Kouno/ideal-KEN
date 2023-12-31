<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*,controller.*" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>顧客脱会画面</title>
    
    <style>
        body{
            background-image:url(/ideal/img/レストラン8.jpg);
    		 background-size:100% auto;
        
        }
    
       	table {
            width: 500px;
            background:linear-gradient(red,white,white,green);
            margin: auto;
            }
        html{
            min-height:100%;
            }
            
            h1{
            color: white; 
            }
       
        
        
        
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;}
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;}
        input[type="submit"] {
            padding: 10px;
            background-color: #007B66;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;}
        input[type="submit"]:hover {
            background-color: #007B66;}  
            
        h1{
        text-align: center;
        }
        
        div{
        text-align: center;
        }
    </style>  
            
    <link rel="stylesheet" type="text/css" href="style.css" />
    <script type="text/JavaScript" src="validationScript.js"></script>
    <script type="text/JavaScript">
    </script>
</head>

<body>
    <h1>お客様脱会手続き</h1>

    <%-- メッセージがnullでない場合にのみ表示 --%>
    <%
        String message = (String) request.getAttribute("msg");
        if (message != null && !message.isEmpty()) {
    %>
        <div>
            <p><%= message %></p>
        </div>
    <%
        }
    %>
<jsp:useBean id="userInfo" class="model.User" scope="session"/>
    <form name="userDeleteForm" action="/ideal/controller/UserOperationSvl" method="post" onsubmit="return dataCheck(this);">
        <table>
        	<tr>
                <th>お客様ID</th>
                <td><%= userInfo.getUsrId() %></td>
            </tr>
            <tr>
                <th>お名前</th>
                <td><%= (userInfo.getUsrName() != null) ? userInfo.getUsrName() : "" %></td>
            </tr>
            <tr>
                <th>住所</th>
                <td><%= (userInfo.getAddress() != null) ? userInfo.getAddress() : "" %></td>
            </tr>
            <tr>
                <th>電話番号</th>
                <td><%= (userInfo.getPhone() != null) ? userInfo.getPhone() : "" %></td>
            </tr>
            <tr>
                <th>E-mail</th>
                <td><%= (userInfo.getMail() != null) ? userInfo.getMail() : "" %></td>
            </tr>
            <tr>
                <td colspan="2" id="sub"><font color="red">確認し、脱会ボタンをクリックしてください。</font>
                    <input type="submit" value="脱会" />
					 <input type="hidden" name="mode" value=<%= UserOperationSvl.DELETE %> />
       				 <input type="hidden" name="usrId" value=<%= request.getAttribute("usrId") %>" />
                </td>
            </tr>
        </table>
    </form>
    <br />

    <!-- 9．処理メニューに戻る -->
    <div><a href="../userIndex.jsp"><font color="white">処理メニューに戻る</font></a></div>
</body>
</html>