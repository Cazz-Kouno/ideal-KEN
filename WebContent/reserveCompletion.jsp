
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
        html {
            min-height: 100%;
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
            response.sendRedirect("reserveUpdate.jsp");
        }
    %>

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
		<%=reserve.getPerson()%>
		名様
	</h2>
	<h2>
		ご予約番号は<span font-size="100px"><%=reserve.getRsvId()%></span>番です。
	</h2>

	<h2>ご来店の際、受付にお申し付けください</h2>

	<a href="/ideal/controller/ReserveListSvl">予約一覧に戻る</a>
		</th>
</body>
</html>