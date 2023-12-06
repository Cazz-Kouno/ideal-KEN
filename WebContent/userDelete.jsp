<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*,controller.*" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>顧客脱会画面</title>
    
    <style>
        body{
            background-image:url(./img/レストラン8.jpg);
    		 background-size:100% auto;
        
        }
    
       	table {
            width: 100%;
            text-align:center;
            background:linear-gradient(red,white,white,green);}
        html{
            min-height:100%;
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;}
        td2{color:red;}
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
    </style>  
            
    <link rel="stylesheet" type="text/css" href="style.css" />
    <script type="text/JavaScript" src="validationScript.js"></script>
    <script type="text/JavaScript">
        <!--
        function dataCheck(obj) {
            // 必須項目の未入力チェック
            if (obj.elements['usrName'].value.length == 0 ||
            	obj.elements['usrId'].value.length == 0 ||
                obj.elements['address'].value.length == 0 ||
                obj.elements['phone'].value.length == 0 ||
                obj.elements['mail'].value.length == 0 ||{
                alert("必須項目を入力してください。");
                return false;
            }

            // 電話番号の正規化チェック（半角数字およびハイフンのみ）
            if (!validatePhone(obj.elements['phone'].value)) {
                alert("電話番号が正しくありません。");
                return false;
            }

            // メールアドレスの正規化チェック
            if (!validateEmail(obj.elements['mail'].value)) {
                alert("メールアドレスが正しくありません。");
                return false;
            }

            return true;
        }
        //-->
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
                <td><td2>確認し、脱会ボタンをクリックしてください。</td2>
                <id="sub" colspan="2">
                    <input type="submit" value="脱会" />
					 <input type="hidden" name="mode" value=<%= UserOperationSvl.DELETE %> />
       				 <input type="hidden" name="usrId" value=<%= request.getAttribute("usrId") %>" />
                </td>
            </tr>
        </table>
    </form>
    <br />

    <!-- 9．処理メニューに戻る -->
    <a href="../userIndex.jsp">処理メニューに戻る</a>
</body>
</html>