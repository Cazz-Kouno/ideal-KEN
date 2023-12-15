<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>予約変更画面</title>

    <style>
        body {
	       background-image: url(/ideal/img/レストラン38.jpg);
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

        th, td {
            padding: 8px;
            text-align: left;
            border: 1px gray solid;
        }
        h1{
        text-align: center;
        }
        div{
        text-align: center;
        }
    </style>

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
        response.sendRedirect("../reserveUpdate.jsp");
        return; // 以降の処理を中断
    }

    String formattedDateTime; // formattedDateTimeを宣言
%>
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
                <td>
                    <%
                    int year = reserve.getRsvYy();
                    int month = reserve.getRsvMm();
                    int day = reserve.getRsvDd();

                    formattedDateTime = String.format("%04d年%02d月%02d日", year, month, day);
                    %> <%=formattedDateTime%>
                </td>
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
            	<td colspan="2" style="text-align:right;">
           		 <input type="submit" value="取消"></td>
           		<input type="hidden" name="rsvId" value=<%= request.getParameter("rsvId") %> />
            	<input type="hidden" name="mode" value="<%= controller.ReserveOperationSvl.DELETE %>" />
        	</tr>
        </table>
    </form>
    <p>
        <div><a href="/ideal/controller/ReserveListSvl">予約一覧に戻る</a></div>
    </p>
</body>
</html>