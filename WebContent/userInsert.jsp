<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*,controller.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>顧客情報登録画面</title>
    
    <style charset="utf-8">
        body {
            background-image: url(/ideal/img/レストラン1.jpg);
            background-size: 100% auto;
        }

        table {
            width: 500px;
            text-align: center;
            background: linear-gradient(red, white, white, green);
            margin: auto;
        }

        html {
            min-height: 100%;
        }
        
        h1 {
                text-shadow:1px 1px 0 #FFF, -1px -1px 0 #FFF,
              -1px 1px 0 #FFF, 1px -1px 0 #FFF,
              0px 1px 0 #FFF,  0-1px 0 #FFF,
              -1px 0 0 #FFF, 1px 0 0 #FFF;
            
             
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        td2 {
            color: red;
             
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            padding: 10px;
            background-color: #007B66;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #007B66;
        }  
        
        h1{
        text-align:center;
        }
        
        div{
        text-align:center;
        }
        #sub{text-align: right;}
    </style>  
            
    <link rel="stylesheet" type="text/css" href="style.css" />
    <script type="text/JavaScript" src="validationScript.js"></script>
    <script type="text/JavaScript">
    <!--
    function dataCheck(obj){
        if (obj.elements['usrName'].value.length === 0) {
            alert("氏名は必須項目です。");
            return false;
        }
        if (!validatePhone(obj.elements['phone'].value)) {
            alert("電話番号は必須項目です。");
            return false;
        }
        if (!validateMail(obj.elements['mail'].value)) {
            alert("メールアドレスは必須項目です。");
            return false;
        }
        console.log("passlength:" + obj.elements['password'].value.length);
        if (obj.elements['password'].value.length === 0) {
            alert("パスワードは必須項目です。");
            return false;
        }
        
        return true;
    }

    // 電話番号の正規化チェック（半角数字とハイフンのみ許可）
    function validatePhone(phone) {
        var regex = /^[\d-]+$/;
        return regex.test(phone);
    }

    // メールアドレスの正規化チェック
    function validateMail(mail) {
        var regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        return regex.test(mail);
    }
    //-->
    
    </script>
</head>

<body>
    <h1>お客様情報登録</h1>

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

    <form name="userInsertForm" action="/ideal/controller/UserOperationSvl" method="post" onsubmit="return dataCheck(this);">
        <table>
            <tr>
                <th>お名前 *</th>
                <td><input type="text" name="usrName" size="30" maxlength="30" style="ime-mode: active" /></td>
            </tr>
            <tr>
                <th>住所</th>
                <td><input type="text" name="address" rows="4" cols="50" maxlength="200" style="ime-mode: active;" /></td>
            </tr>
            <tr>
                <th>電話番号 *</th>
                <td><input type="text" name="phone" size="15" maxlength="20" style="ime-mode: inactive;" /></td>
            </tr>
            <tr>
                <th>E-mail *</th>
                <td><input type="text" name="mail" size="30" maxlength="100" style="ime-mode: inactive;" /></td>
            </tr>
            <tr>
                <th>パスワード *</th>
                <td><input type="password" name="password" size="8" maxlength="8" style="ime-mode: inactive" /></td>
            </tr>
            <tr>
                <td id="sub" colspan="2">
                    <span style="color:red;">*は必須入力です。</span>
                    <input type="submit" value="登録" />
                    <input type="hidden" name="mode" value="<%= UserOperationSvl.INSERT %>" />
                </td>
            </tr>
        </table>
    </form>

    <br />

    <!-- ⑧．処理メニューに戻る -->
    <div><a href="home.jsp"><font color="white">ホームページに戻る</font></a></div>
</body>
</html>