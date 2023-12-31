<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.*"%>
<%@ page import="course.*"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>予約変更画面</title>

<style>
body {
	background-image: url(/ideal/img/レストラン35.jpg);
	background-size: 100% auto;
}
table {
	width: 500px;	
	margin: auto;
	}
th, td {
	padding: 8px;
	text-align: left;
	border: 1px gray solid;}
th {
	background-color: Red;
}
td{
	background-color: white;
}
h1{
    text-align: center;
}
div{
    text-align: center;
    
}
</style>
<link rel="stylesheet" type="text/css" href="meisai.css" />
    <script type="text/javascript">
        function checkData(obj) {
            var msg = "";
            var rsvYy = parseInt(obj.rsvYy.value);
            var rsvMm = parseInt(obj.rsvMm.value) - 1;
            var rsvDd = parseInt(obj.rsvDd.value);
            var rsvHh = parseInt(obj.rsvHh.value);
            var rsvMi = parseInt(obj.rsvMi.value);
            var rsvDay = new Date(rsvYy, rsvMm, rsvDd, rsvHh, rsvMi, 0);
            var toDay = new Date();

            if (rsvDay.getTime() < toDay.getTime()) {
                msg += "予約日時が過去日又は無効な日付です。\n";
            }
            if ((rsvDay.getTime() - toDay.getTime()) / 24 / 60 / 60 / 1000 >= 365) {
                msg += "一年以上先の予約は承れません。\n";
            }
            if (msg.length > 0) {
                alert(msg);
                return false;
            } else {
                return true;
            }
        }
    </script>
</head>
<body>
	<%
        // セッションから User オブジェクトを取得し、型キャストする
        model.User userInfo = (model.User)session.getAttribute("userInfo");
        model.Reserve reserve = (model.Reserve)request.getAttribute("reserve"); // 予約情報をセッションから取得
        ArrayList<Course> courseList = (ArrayList<Course>)request.getAttribute("courseList");

        if (userInfo == null || reserve == null || courseList == null) {
    %>
            <h2>エラー: ユーザ情報または予約情報が取得できませんでした。</h2>
    <%
            // エラーが発生した場合の適切な処理を行う（例: リダイレクト、エラーページへの遷移など）
            response.sendRedirect("reserveUpdate.jsp");
        }
    %>
	<h1><%= userInfo.getUsrName() %> 様ご予約変更</h1>

	<%-- メッセージがnullでない場合のみ表示 --%>
    <% if (request.getAttribute("msg") != null) { %>
        <p><%= request.getAttribute("msg") %></p>
    <% } %>

	<form id="frm1" name="frm1" action="/ideal/controller/ReserveOperationSvl" onsubmit="return checkData(this)" method="post">
		<table border>
			<tr>
				<th>予約番号</th>
				<td><%= reserve.getRsvId() %></td>
			</tr>

			<tr>
                <th>日付</th>
                <td>
                    <select name="rsvYy">
                        <%-- 予約年の選択対象：当年から一年後 --%>
                        <option value="<%= java.time.Year.now().getValue() %>" selected><%= java.time.Year.now().getValue() %>年</option>
                        <option value="<%= java.time.Year.now().plusYears(1).getValue() %>"><%= java.time.Year.now().plusYears(1).getValue() %>年</option>
                    </select>
                    <select name="rsvMm">
                        <%-- 予約月の選択対象：1月から12月 --%>
                        <% for (int i = 1; i <= 12; i++) { %>
                            <option value="<%= i %>"<%= i == reserve.getRsvMm() ? " selected" : "" %>><%= i %>月</option>
                        <% } %>
                    </select>
     		<%
    		// JavaScriptの変数としてrsvDdを取得
    		String rsvDdValue = "document.getElementById('rsvDd').value";
			%>
					<select name="rsvDd" id="rsvDd">
    					<%-- 予約日の選択対象：1日から31日 --%>
    					<% for (int i = 1; i <= 31; i++) { %>
        	<%
            String condition = 
            "(i == 8 && " + rsvDdValue + " == null) || (" + rsvDdValue + " != null && parseInt(" + rsvDdValue + ") == " + i + ")";
        	%>
        			<option value="<%= i %>"<%= condition.equals("true") ? " selected" : "" %>><%= i %>日</option>
    					<% } %>
					</select>

                </td>
            </tr>
            <tr>
                <th>時刻</th>
                <td>
                    <select name="rsvHh">
                        <%-- 予約時の選択対象：17時から21時 --%>
                        <% for (int i = 17; i <= 21; i++) { %>
                            <option value="<%= i %>"<%= i == 17 ? " selected" : "" %>><%= i %>時</option>
                        <% } %>
                    </select>
                    <select name="rsvMi">
                        <%-- 予約分の選択対象：0分、15分、30分、45分 --%>
                        <% for (int i = 0; i <= 45; i += 15) { %>
                            <option value="<%= i %>"<%= i == 0 ? " selected" : "" %>><%= i %>分</option>
                        <% } %>
                    </select>
                </td>
            </tr>
            <tr>
                <th>人数</th>
                <td>
                    <select name="person">
                        <%-- 予約人数の選択対象：1名から6名 --%>
                        <% for (int i = 1; i <= 6; i++) { %>
                            <option value="<%= i %>"<%= i == 1 ? " selected" : "" %>><%= i %> 名</option>
                        <% } %>
                    </select>
                </td>
            </tr>
            <tr>
                <th>コース</th>
                <td>
                    <select name="courseId">
<%
for(Course c:courseList){
%>	
	<option value="<%= c.getCourseId()%>"><%= c.getCourseName() %></option>
<%	
}
%>
                    </select>
                </td>
            </tr>
            <tr>
                <td id="sub" colspan="2">
                    席を確認します。確認を押してください。 <input type="submit" value="変更" />
                </td>
            </tr>
            <input type="hidden" name="rsvId" value=<%= reserve.getRsvId() %> />
            <input type="hidden" name="mode" value="<%= controller.ReserveOperationSvl.UPDATE %>" />
        </table>
        <br />
    </form>
    <br />
    <p id="boder">
        <div><a href="/ideal/controller/ReserveListSvl">予約一覧に戻る</a></div>
    </p>
</body>
</html>