<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規予約画面</title>
<style>
table {
	width: 100%;
}

th, td {
	padding: 8px;
	text-align: left;
	border: 1px gray solid;
}
</style>
</head>
<body>
<body>
	<h1><%=userInfo.getUsrName()%>様ご予約
	</h1>

	<p>ここにメッセージを表示</p>

	<form id="frm1" name="frm1" action="./ex12.html" method="get">
		<table>

			<tr>
				<th>日付</th>
				<td>
					<form>
						<select name="rsvYy" id="rsvYySelect">
							<option value="">西暦</option>
							<%
							for (int year = currentYear; year <= currentYear + 1; year++) {
							%>
							<option value="<%=year%>"><%=year%></option>
							<%
							}
							%>
						</select> 年 <select name="rsvMm" id="rsvMmSelect">
							<%
							for (int month = 1; month <= 12; month++) {
							%>
							<option value="<%=month%>"><%=month%>月
							</option>
							<%
							}
							%>
						</select> 月 <select name="rsvDd" id="rsvDdSelect">
							<%
							// 日数の設定
							int selectedMonth = 1; // 月の初期値
							int daysInMonth = 31; // 月の日数

							// 選択された月を取得
							if (request.getParameter("rsvMm") != null) {
								selectedMonth = Integer.parseInt(request.getParameter("rsvMm"));
							}

							// 2, 4, 6, 9, 11以外の月の日数を設定
							if (selectedMonth == 4 || selectedMonth == 6 || selectedMonth == 9 || selectedMonth == 11) {
								daysInMonth = 30;
							} else if (selectedMonth == 2) { // 2月の日数を設定（うるう年を考慮）
								int selectedYear = currentYear; // 年の初期値
								if (request.getParameter("rsvYy") != null) {
									selectedYear = Integer.parseInt(request.getParameter("rsvYy"));
								}
								if ((selectedYear % 4 == 0 && selectedYear % 100 != 0) || (selectedYear % 400 == 0)) {
									daysInMonth = 29;
								} else {
									daysInMonth = 28;
								}
							}
							// 日付のプルダウンメニューを生成
							for (int day = 1; day <= daysInMonth; day++) {
							%>
							<option value="<%=day%>"><%=day%></option>
							<%
							}
							%>
						</select> 日
				</td>
			</tr>

			<tr>
				<th>時刻</th>
				<td><select name="rsvHh" id="rsvHhSelect">
						<option value=""></option>
						<%
						for (int i = 17; i <= 21; i++) {
						%>
						<option value="<%=i%>"><%=i%></option>
						<%
						}
						%>
				</select> 時 <select name="rsvMm" id="rsvMmSelect">
						<option value="00">00</option>
						<option value="15">15</option>
						<option value="30">30</option>
						<option value="45">45</option>
				</select> 分</td>
			</tr>

			<tr>
				<th>人数</th>
				<td>???名</td>
			</tr>

			<tr>
				<th>コース</th>
			</tr>

			<
			<tr>
				<td colspan="2" style=""><input type="submit" value="予約"></td>
			</tr>


		</table>
	</form>
	<p>
		<a href="Servlet">予約一覧に戻る</a>
	</p>


</body>
</html>
