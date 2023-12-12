<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約情報一覧画面</title>

<style>

body{
	background-image:url(/ideal/img/レストラン1.jpg);
	background-size:100% auto;
	}
	html{
min-height:100%;
	}
table{
	border: 2px glay solid;
}

th {
	border: 2px solid glay;
	
}

td {
	border: 2px solid glay;
}

h1{
    color: white;
}
</style>
</head>
<body>
<jsp:useBean id="reserveList" class="java.util.ArrayList"
	scope="session" />
<jsp:useBean id="userInfo" class="model.User" scope="session" />
	<h1><%=userInfo.getUsrName()%>様、ご予約一覧
	</h1>

	<p><%-- メッセージがnullでない場合にのみ表示 --%>
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
    </p>


	<table>
			<tr>
				<td>NO</td>
				<td>ご予約日時</td>
				<td>人数</td>
				<td>コース名</td>
				<td>テーブル名</td>
				<td>登録日時</td>
			</tr>
			<%
			for (Object obj : reserveList) {
				Reserve reserve = (Reserve) obj;
			%>
			<tr>
				<td><%=reserve.getRsvId()%></td>
				<td>
					<%
					int year = reserve.getRsvYy();
					int month = reserve.getRsvMm();
					int day = reserve.getRsvDd();
					int hour = reserve.getRsvHh();
					int minute = reserve.getRsvMi();

					String formattedDateTime = String.format("%04d年%02d月%02d日 %02d時%02d分", year, month, day, hour, minute);
					%> <%=formattedDateTime%>
				</td>
				<td><%=reserve.getPerson()%></td>
				<td><%=reserve.getCourseName()%></td>
				<td><%=reserve.getTableName()%></td>
				<%
				formattedDateTime = String.format("%04d年%02d月%02d日 %02d時%02d分", reserve.getAppYy(), reserve.getAppMm(),
						reserve.getAppDd(), reserve.getAppHh(), reserve.getAppMm());
				%>
				<td><%=formattedDateTime%></td>
				<td>                    <%-- 予約変更ボタン --%>
                    <form action="/ideal/controller/ReserveUpdateSvl" method="post">
                        <input type="hidden" name="rsvId" value="<%=reserve.getRsvId()%>">
                        <input type="submit" value="変更">
                    </form></td>
				<td>  <%-- 予約削除ボタン --%>
                    <form action="/ideal/controller/ReserveDeleteSvl" method="post">
                        <input type="hidden" name="rsvId" value="<%=reserve.getRsvId()%>">
                        <input type="submit" value="取消">
                    </form></td>
			</tr>
			<%
			}
			%>
				</table>

    <form action="/ideal/controller/ReserveInsertSvl" method="post">
        <!-- type="submit" で "新規ご予約" という名前のボタンを作成 -->
        <input type="submit" value="新規ご予約">
    </form>

	<p>
		<a href="../userIndex.jsp">処理メニューに戻る</a>
	</p>
	<br>
</body>

</html>

