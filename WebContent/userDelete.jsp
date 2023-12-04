<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>顧客脱会画面</title>
    
    <style>
       	table {
            width: 100%;}
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
                obj.elements['address'].value.length == 0 ||
                obj.elements['phone'].value.length == 0 ||
                obj.elements['mail'].value.length == 0 ||
                obj.elements['password'].value.length == 0) {
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

    <form name="userDeleteForm" action="/ideal/UserOperationSvl." method="post" onsubmit="return dataCheck(this);">
        <table>
        	<tr>
                <th>お客様ID</th>
                <td><input type="text" name="usrId" size="30" maxlength="30" style="ime-mode: active;" /></td>
            </tr>
            <tr>
                <th>お名前</th>
                <td><input type="text" name="usrName" size="30" maxlength="30" style="ime-mode: active;" /></td>
            </tr>
            <tr>
                <th>住所</th>
                <td><textarea name="address" rows="4" cols="50" style="ime-mode: active;"></textarea></td>
            </tr>
            <tr>
                <th>電話番号</th>
                <td><input type="text" name="phone" size="15" maxlength="20" style="ime-mode: inactive;" /></td>
            </tr>
            <tr>
                <th>E-mail</th>
                <td><input type="text" name="mail" size="30" maxlength="100" style="ime-mode: inactive;" /></td>
            </tr>
            <tr>
                <td><td2>確認し、脱会ボタンをクリックしてください。</td2>
                <id="sub" colspan="2">
                    <input type="submit" value="脱会" />
					 <input type="hidden" name="mode" value="削除処理" />
       				 <input type="hidden" name="usrId" size="8" maxlength="8" style="ime-mode: inactive;" /></td>
                </td>
            </tr>
        </table>
    </form>

    <br />

    <!-- 9．処理メニューに戻る -->
    <a href="userIndex.jsp">処理メニューに戻る</a>
</body>
</html>