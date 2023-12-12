<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">>
<%@ page import="model.*,controller.*" %>
<%@ page import="java.util.ArrayList"%>
<%@page import="model.Reserve"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>予約取消画面</title>
    
    <style>
        table {
            width: 100%;
            text-align: center;
            background: linear-gradient(red, white, white, green);
        }

        html {
            min-height: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border: 1px gray solid;
        }
    </style> 
            
    <link rel="stylesheet" type="text/css" href="style.css" />
    <script type="text/JavaScript">
    </script>
</head>
<body>
	<%
        // セッションから User オブジェクトを取得し、型キャストする
        model.User userInfo = (model.User)session.getAttribute("userInfo");
        model.Reserve reserve = (model.Reserve)request.getAttribute("reserve"); // 予約情報をセッションから取得

        if (userInfo == null || reserve == null) {
    %>
            <h2>エラー: ユーザ情報または予約情報が取得できませんでした。</h2>
    <%
            // エラーが発生した場合の適切な処理を行う（例: リダイレクト、エラーページへの遷移など）
            response.sendRedirect("reserveDelete.jsp");
        }
    
    <h1><%= userInfo.getUsrName()%>様ご予約取消</h1>

    <p>
       <%-- メッセージがnullでない場合のみ表示 --%>
    <% if (request.getAttribute("msg") != null) { %>
        <p><%= request.getAttribute("msg") %></p>
    <% } %>
    </p>
    
    <form id="frm1" name="frm1" action="/ideal/controller/ReserveOperationSvl" onsubmit="return checkData(this)" method="post">
        <table>
            <tr>
                <th>予約番号</th>
                <td><%=reserve.getRsvId()%></td>
            </tr>
            <tr>
                <th>日付</th>
                <%
                    int year = reserve.getRsvYy();
                    int month = reserve.getRsvMm();
                    int day = reserve.getRsvDd();

                    String formattedDateTime = String.format("%04d年%02d月%02d日", year, month, day);
                    %> <%=formattedDateTime%>
            </tr>
            <tr>
                <th>時刻</th>
                <td>
                    <%
                    int hour = reserve.getRsvHh();
                    int minute = reserve.getRsvMi();

                    formattedDateTime = String.format("%02d時%02d分", hour, minute);
                    %> <%=formattedDateTime%>
                </td>
            </tr>
            <tr>
                <th>人数</th>
                <td><%=reserve.getPerson()%></td>
            </tr>
            <tr>
                <th>コース</th>
                <td><%=reserve.getCourseName()%></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="取消">
                </td>
                <input type="hidden" name="rsvId" value=<%= reserve.getRsvId() %> />
                <input type="hidden" name="mode" value="<%= controller.ReserveOperationSvl.DELETE %>" />
            </tr>
        </table>
    </form>
    <p>
        <a href="/ideal/controller/ReserveListSvl">予約一覧に戻る</a>
    </p>
</body>
</html>