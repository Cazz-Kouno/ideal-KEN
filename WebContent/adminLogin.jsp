<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員ログイン画面</title>
    
    <style>
        table {
            width: 100%;}
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px gray solid;
            }
</style>
    
    <link rel="stylesheet" type="text/css" href="login.css"/>
 <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;}
        h2 {
            color: #333;}
        div {
            background-color: #007B66;
            padding: 10px;
            margin-bottom: 10px;}
       	table {
            width: 100%;}
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
</style>
    <script type="text/JavaScript" src="incScript.js"></script>
    <script type="text/JavaScript">
        <!--
            function dataCheck(obj){
                // 1.管理者名、パスワードの未入力チェック
                if(obj.elements[0].value.length == 0 || obj.elements[1].value.length == 0){
                    // 1.名前、パスワードが未入力の時、アラートを表示し、"false"をリターンする。
                    alert("管理者氏名、パスワードを必ず入力してください。");
                    return false;
                }
            }
        //-->
    </script>
</head>

<body>
    <h2>管理者ログイン</h2>
    <br />
	<p>ここにメッセージを表示</p>
    <%-- メッセージがnullでない場合にのみ表示 --%>
    <%
        String message = (String) session.getAttribute("msg");
        if (message != null && !message.isEmpty()) {
    %>
        <div>
            <p><%= message %></p>
        </div>
    <%
        }
    %>

    <form name="AdminloginForm" action="/ideal/controller/AdminLoginSvl" method="post" 
       											 onsubmit="return dataCheck(this);">
        <table>
            <tr>
                <th>管理者氏名</th>
                <td><input type="text" name="admName" size="4" maxlength="8" 
                        style="ime-mode: active;"/></td>
            </tr>
            <tr>
                <th>パスワード</th>
                <td><input type="password" name="password" size="3" maxlength="8" 
                        style="ime-mode: inactive;"/></td>
            </tr>
            <tr>
                <td id="sub" colspan="2">
                    <input type="submit" value=" 送 信 " /></td>
            </tr>
        </table>
    </form>
</body>
</html>