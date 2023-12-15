<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>お客様ログイン画面</title>
       <link rel="stylesheet" type="text/css" href="login.css" />
    
    <style>
        body {
            background-image:url(/ideal/img/レストラン2.jpg);
    		background-size:100% auto;            
            font-family: Arial, sans-serif;
            margin: 20px;}
        table{
            width:500px;
            text-align:center;
            background:linear-gradient(red,white,white,green);
        	margin:auto;
        	}
		html{
			min-height:100%;}
        h2 {
            text-align:center;
            color: white;}
        div {
            text-align:center;
            background-color: #ffcccc;
            padding: 10px;
            margin-bottom: 10px;}
        div.link{
        	background-color: transparent;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px gray solid;}
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;}
        input[type="submit"] {
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;}
        input[type="submit"]:hover {
            background-color: #45a049;}
        #sub{text-align: center;}
            
</style>
    <script type="text/JavaScript" src="incScript.js"></script>
    <script type="text/JavaScript">
    <!--
    function dataCheck(obj){
        // 1.管理者名、パスワードの未入力チェック
        if(obj.elements[0].value.length == 0 || obj.elements[1].value.length == 0){
            // 1.お客様ID、パスワードが未入力の時、アラートを表示し、"false"をリターンする。
            alert("ID、パスワードを必ず入力してください。");
            return false;
        }
    }
//-->
    </script>
</head>

<body>
    <h2>お客様ログイン</h2>
    <br />
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
    <form name="UserloginForm" action="/ideal/controller/UserLoginSvl" method="post" 
    											onsubmit="return dataCheck(this);">
        <table>
            <tr>
                <th>お客様ID</th>
                <td><input type="text" name="usrId" size="8" maxlength="8" style="ime-mode: active;" /></td>
            </tr>
            <tr>
                <th>パスワード</th>
                <td><input type="password" name="password" size="8" maxlength="8" style="ime-mode: inactive;" /></td>
            </tr>
            <tr>
                <td id="sub" colspan="2">
                    <input type="submit" value=" 送 信 " /></td>
            </tr>
        </table>
    </form>
    <br />
    <div class="link"><a href="/ideal/home.jsp">ホームページ</a></div>
</body>
</html>