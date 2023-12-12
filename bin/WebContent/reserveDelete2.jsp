<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*,controller.*" %>

<!DOCTYPE html>

<html>
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
    <script type="text/JavaScript" src="validationScript.js"></script>
    <script type="text/JavaScript">
    </script>
</head>

<body>
    <jsp:useBean id="userInfo" class="model.User" scope="session"/>
    <jsp:useBean id="reserve" class="model.Reserve" scope="session"/>
    
    <h1><%= userInfo.getUsrName()%>様ご予約取消</h1>

    <p>
       <%-- メッセージがnullでない場合のみ表示 --%>
    <% if (request.getAttribute("msg") != null) { %>
        <p><%= request.getAttribute("msg") %></p>
    <% } %>
    </p>
    
    <form name="reserveDeleteForm" action="/ideal/controller/ReserveOperationSvl" method="post" onsubmit="return dataCheck(this);">
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
                <%
                    // reserve.getPerson()がnullでない場合はそのまま表示、nullの場合は空文字列を表示
                    String person = (reserve.getPerson() != null) ? String.valueOf(reserve.getPerson()) : "";
                %>
                <td><%= person %></td>
            </tr>
            <tr>
                <th>コース</th>
                <td><%= (reserve.getCourseName() != null) ? reserve.getCourseName() : "" %></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="取消">
                </td>
                <input type="hidden" name="rsvId" value=<%= request.getAttribute("rsvId") %> />
                <input type="hidden" name="mode" value="<%= controller.ReserveOperationSvl.DELETE %>" />
            </tr>
        </table>
    </form>
    <p>
        <a href="/ideal/controller/ReserveListSvl">予約一覧に戻る</a>
    </p>
</body>
</html>