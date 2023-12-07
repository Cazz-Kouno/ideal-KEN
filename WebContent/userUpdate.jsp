<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*,controller.*" %>

<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>顧客情報変更画面</title>
    
    <style>
        body{
            background-image:url(/ideal/img/レストラン12.jfif);
    		 background-size:100% auto;}
        table {
            width: 100%;         
            text-align:center;
            background:linear-gradient(red,white,white,green);}
        html{
            min-height:100%;}  
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;}
       	p{color:red;}
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
    function dataCheck(obj){
       if (obj.elements['usrName'].value.length == 0) {
                alert("氏名は必須項目です。");
                return false;
                }
       if (!validatePhone(obj.elements['phone'].value)) {
           alert("電話番号は必須項目です。");
           return false;
           }
       if (!validateEmail(obj.elements['mail'].value)) {
           alert("メールアドレスは必須項目です。");
           return false;
           }
       return true;
       }
        
   		// 電話番号の正規化チェック
       		function validatePhone(phone) {
          		var regex = /^[0-9-]+$/;
            	return regex.test(phone);
        		}
       	// メールアドレスの正規化チェック
        	function validateEmail(email) {
            	var regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            	return regex.test(email);
        		}
        //-->
        
    </script>
</head>

<body>
    <h1>顧客情報変更</h1>

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
    <form name="userUpdateForm" action="/ideal/controller/UserOperationSvl" method="post" onsubmit="return dataCheck(this);">
        <table>
            <tr>
                <th>お客様ID</th>
                <td><%= userInfo.getUsrId() %></td>
            </tr>
            <tr>
                <th>お名前	*</th>
                <td><input type="text" name="usrName" size="20" maxlength="20" style="ime-mode: active" placeholder=<%= (userInfo.getUsrName() != null) ? userInfo.getUsrName() : "" %> ></td>
            </tr>
           <tr>
                <th>住所</th>
                <td><input type="text" name="address" size="4" cols="50" style="ime-mode: active" placeholder=<%= (userInfo.getAddress() != null) ? userInfo.getAddress() : "" %> ></td>
            </tr>
            <tr>
                <th>電話番号 *</th>
                <td><input type="text" name="phone" size="15" maxlength="20" style="ime-mode: inactive" placeholder=<%= (userInfo.getPhone() != null) ? userInfo.getPhone() : "" %> ></td>
            </tr>
            <tr>
                <th>メールアドレス	*</th>
                <td><input type="text" name="mail" size="30" maxlength="100" style="ime-mode: inactive" placeholder=<%= (userInfo.getMail() != null) ? userInfo.getMail() : "" %> ></td>
            </tr>
            <tr>
                <th>パスワード</th>
                <td><input type="password" name="password" size="8" maxlength="8" style="ime-mode: inactive" placeholder=<%= (userInfo.getPassword() != null) ? userInfo.getPassword() : "" %> >※変更時のみ入力してください。</td>
            </tr>
            <tr>            	
                <td id="sub" colspan="2">
                </br>
					<td2>*は必須入力です。</td2><input type="submit" value="変更" />
					 <input type="hidden" name="mode" value=<%= UserOperationSvl.UPDATE %> />
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