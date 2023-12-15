<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*,controller.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コース情報削除画面</title>

<style>body{
            background-image:url(/ideal/img/レストラン7.jpg);
    		 background-size:100% auto;
            }
        table {
            width: 500px;
            text-align:center;
            background:linear-gradient(red,white,white,green);
            align:center;
            margin: auto;
            }
        html{
            min-height:500px;
            }
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px gray solid;
            }
		th{background-color: Red;}
        h1 {
	       background-color: Red;
	       text-align: center;
           }
           
           div{
           text-align: center;
           }
</style>

</head>
<body>
<jsp:useBean id="course" class="model.Course" scope="request"/>
<jsp:useBean id="oneCourse" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="typeMenuList" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="menuTypes" class="java.util.ArrayList" scope="request"/>

	
				<%-- メッセージがnullでない場合にのみ表示 --%>

<%
String message = (String) request.getAttribute("msg");
int count = 1;
if (message != null && !message.isEmpty()) {
%>
			
			<div>
				<p><%=message%></p>
			</div> 
<%
 }
 %>
	
<form id="frm1" name="frm1" action="/ideal/controller/CourseOperationSvl" method="post">
<table>
		<td colspan="2">
<h1>コースの削除</h1>


<tr>
<th>コース名</th>
<td>
<%=course.getCourseName() %>
</td>
</tr>

<tr>
<th>価格</th>
<td>
<%=course.getPrice() %>
</td>
</tr>

<tr>
<th>オーダー可</th>
<td>
<%= course.getOrderFlg() == 1 ? "オーダー可" : "オーダー不可" %>
</td>
</tr>

<tr>
<th>コメント</th>
<td>
<%=course.getDetail() == null ? "" :course.getDetail()%>
</td>

</tr>

<%
for(Object obj:typeMenuList){
	if(obj != null){
		ArrayList<Menu> menuList = (ArrayList<Menu>)obj;
%>
				<tr>
					<th><%=((MenuType)menuTypes.get(count)).getTypeName() %></th>
	<%					
		for(Menu menu:menuList){
			for(Object menuObj:oneCourse){
				Course courseMenu = (Course)menuObj;
				if(courseMenu.getMenuId() == menu.getMenuId()){
	%>
					<td>
					<%= menu.getMenuName() %>
					</td>
	<%
				}
			}
		}
	}
	%>
				</tr>

<%
count++;
}
%>

<tr>
<td colspan="2" style="text-align:right;"><input type="submit" value="削除"></td>
</tr>


</table>
	<input type="hidden" name="mode" value="<%= CourseOperationSvl.DELETE %>">
	<input type="hidden" name="typeId" value="100">
	<input type="hidden" name="courseId" value="<%= course.getCourseId()%>">
</form>
<div><p><a href="/ideal/controller/MenuMaintenanceSvl">一覧表示画面に戻る</a></p></div>


</body>
</html>



