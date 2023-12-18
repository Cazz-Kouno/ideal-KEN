
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お客様予約情報一覧画面</title>

<style>

body{
	background-image:url(/ideal/img/レストラン1.jpg);
	background-size:100% auto;
	}
	html{
min-height:100%;
	}
table{
	border: 2px gray solid;
	margin:auto;
}

th {
	border: 2px solid gray;
	background-color: Red;
		padding: 10px;
}

td {
	border: 2px solid gray;
	background-color: white;
		padding: 10px;
}

h1{
    text-align:center;
    text-shadow:1px 1px 0 #FFF, -1px -1px 0 #FFF,
              -1px 1px 0 #FFF, 1px -1px 0 #FFF,
              0px 1px 0 #FFF,  0-1px 0 #FFF,
              -1px 0 0 #FFF, 1px 0 0 #FFF;
}

div{
    text-align:center;
}
</style>
</head>
<body>
<jsp:useBean id="allReserveList" class="java.util.ArrayList"
	scope="request" />
<jsp:useBean id="adminInfo" class="model.Admin" scope="session" />
	
	<h1>お客様予約一覧</h1>

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
				<th>NO</th>
				<th>お客様氏名</th>
				<th>ご予約日時</th>
				<th>人数</th>
				<th>コース名</th>
				<th>テーブル名</th>
				<th>登録日時</th>
				<th colspan="2"></th>
			</tr>
			<%
			for (Object obj : allReserveList) {
				Reserve reserve = (Reserve) obj;
			%>
			<tr>
				<td><%=reserve.getRsvId()%></td>	
				<td style="max-width:120px;"><%=reserve.getUsrName()%>様</td>	
				
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
				<td style="text-align:center;"><%=reserve.getPerson()%></td>
				<td><%=reserve.getCourseName()%></td>
				<td><%=reserve.getTableName()%></td>
				<%
				formattedDateTime = String.format("%04d年%02d月%02d日 %02d時%02d分", reserve.getAppYy(), reserve.getAppMm(),
						reserve.getAppDd(), reserve.getAppHh(), reserve.getAppMm());
				%>
				<td><%=formattedDateTime%></td>
<%--形だけ残す--%>	<td>                    <%-- 予約変更ボタン --%>
                    <form action="/ideal/controller/ReserveUpdateSvl" method="post">
                        <input type="hidden" name="rsvId" value="<%=reserve.getRsvId()%>">
                        <input type="submit" value="変更">
                    </form></td>
<%--形だけ残す--%>	<td>  <%-- 予約削除ボタン --%>
                    <form action="/ideal/controller/ReserveDeleteSvl" method="post">
                        <input type="hidden" name="rsvId" value="<%=reserve.getRsvId()%>">
                        <input type="submit" value="取消">
                    </form></td>
			</tr>
			<%
			}
			%>

				
<%--形だけ残す--%>
	<tr><td colspan="9" style="text-align:center;">
    <form action="/ideal/controller/ReserveInsertSvl" method="post">
        <!-- type="submit" で "新規ご予約" という名前のボタンを作成 -->
        <input type="submit" value="新規ご予約">
    </form>
    </td></tr>

				</table>
				<br>
	<div>
		<a href="../adminIndex.jsp"><font color="white">処理メニューに戻る</font></a>
	</div>
	<br><br><br><br><br>
</body>

</html>

