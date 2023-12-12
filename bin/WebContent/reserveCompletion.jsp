<%@page import="model.Reserve"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約完了画面</title>
<style>
	
body{text-align:center;

background:linear-gradient(red,white,white,green);
}

</style>
</head>
<body>
	<h1><%=userInfo.getUsrName()%>様、ご予約が完了いたしました。
	</h1>
	<h2>
		<%
		//9999年99月99日　99時99分より
		int year = reserve.getRsvYy();
		int month = reserve.getRsvMm();
		int day = reserve.getRsvDd();
		int hour = reserve.getRsvHh();
		int minute = reserve.getRsvMi();
		String formattedDateTime = String.format("%04d年%02d月%02d日 %02d時%02d分より", year, month, day, hour, minute);
		%>
		<%=formattedDateTime%>
	</h2>

	<h2>
		<%=reserve.getCourseName()%>
		&nbsp;
		<%=reserve.getCourseName()%>
		名様
	</h2>
	<h2>
		ご予約番号は<%=reserve.getRsvId()%>番です。
	</h2>

	<h2>ご来店の際、受付にお申し付けください</h2>

	<a href="/ideal/controller/ReserveListSvl">予約一覧に戻る</a>
		</th>
</body>
</html>