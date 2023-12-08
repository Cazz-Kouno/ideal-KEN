<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Reserve" %>
<%@ page import="model.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>予約情報一覧画面</title>

    <style>
        body {
            background-image: url(/ideal/img/レストラン1.jpg);
            background-size: 100% auto;
        }

        html {
            min-height: 100%;
        }

        table {
            border: 2px gray solid;
        }

        th, td {
            border: 2px solid gray;
        }
    </style>
</head>
<body>
    <%-- セッションから会員情報と予約情報を取得 --%>
    <jsp:useBean id="userInfo" class="model.User" scope="session" />
    <jsp:useBean id="reserveList" class="java.util.ArrayList" scope="session" />

    <h1><%= userInfo.getUsrName() %>様、ご予約一覧</h1>

    <p>
        <%-- メッセージが null でない場合にのみ表示 --%>
        <c:if test="${not empty requestScope.msg}">
            <div>
                <p>${requestScope.msg}</p>
            </div>
        </c:if>
    </p>

    <table>
        <tr>
            <th>NO</th>
            <th>ご予約日時</th>
            <th>人数</th>
            <th>コース名</th>
            <th>テーブル名</th>
            <th>登録日時</th>
            <th></th>
            <th></th>
        </tr>

        <c:forEach var="reserve" items="${reserveList}">
            <tr>
                <td>${reserve.rsvId}</td>
                <td>${reserve.formattedDateTime}</td>
                <td>${reserve.person}</td>
                <td>${reserve.courseName}</td>
                <td>${reserve.tableName}</td>
                <td>${reserve.formattedCreatedAt}</td>
                <td>
                    <%-- 予約変更ボタン --%>
                    <form action="/ideal/controller/ReserveUpdateSvl" method="post">
                        <input type="hidden" name="rsvId" value="${reserve.rsvId}">
                        <input type="submit" value="変更">
                    </form>
                </td>
                <td>
                    <%-- 予約削除ボタン --%>
                    <form action="/ideal/controller/ReserveDeleteSvl" method="post">
                        <input type="hidden" name="rsvId" value="${reserve.rsvId}">
                        <input type="submit" value="取消">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <%-- 予約登録ボタン --%>
    <form action="/ideal/controller/ReserveInsertSvl" method="post">
        <input type="submit" value="新規ご予約">
    </form>

    <%-- 処理メニューに戻るリンク --%>
    <p><a href="../userIndex.jsp">処理メニューに戻る</a></p>
    <br>
</body>
</html>