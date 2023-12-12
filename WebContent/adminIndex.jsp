<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者処理選択</title>
    
    <style>
       	body{
            width: 500px;
            text-align:center;
            
            background-image:url(/ideal/img/レストラン18.avif),url(/ideal/img/レストラン1.jpg);
    		 background-size:100% auto;
            /*background:linear-gradient(red,white,white,green);*/
            }

        html{
            min-height:100%;
            
            }
        h1{
            color: white;
        }
            
        h2{
            color: white;
        }
        
        h1{
            text-align: center;
        }
            
            
            
            
            </style>
</head>

<body>
	<h1>管理者処理選択</h1>
<%
    // セッションから管理者名を取得
    Admin admin = (Admin)(session.getAttribute("adminInfo"));
    String admName = null;
    		
    if (admin != null) {
    	admName = admin.getAdmName();
%>
    <h2><%= admName %>様、いらっしゃいませ。</h2>
<%
    } else {
        // セッションが切れている場合はログイン画面に遷移
        response.sendRedirect("adminLogin.jsp");
    }

    // メッセージがnullでない場合にのみ表示
    String message = (String) request.getAttribute("msg");
    if (message != null && !message.isEmpty()) {
%>
    <div>
        <p><%= message %></p>
    </div>
<%
    }
%>
 <br />
 <!-- ③．メニューメンテナンス -->
    <a href="/ideal/controller/MenuMaintenanceSvl">●メニューメンテナンス</a></p>

 <!-- ④．ログオフ -->
    <a href="/ideal/controller/AdminLogoffSvl">●ログオフ</a></p>
</form>

</body>
</html>