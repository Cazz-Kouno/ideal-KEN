<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*,controller.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コース情報変更画面</title>

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
</style>>
<%! String[] COURSE_MENU_TYPE_NAME = 
{"courseId","appetizerId","soupId","pastaId","meatId","fishId","dessertId","","","",""}; %>

</head>


<body>
<jsp:useBean id="course" class="model.Course" scope="request"/>
<jsp:useBean id="oneCourse" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="typeMenuList" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="menuTypes" class="java.util.ArrayList" scope="request"/>

<table>
<td colspan="2">
<h1>コースの更新</h1>
<%	
    // メッセージがnullでない場合にのみ表示
String message = (String) request.getAttribute("msg");
int count = 1;
if (message != null && !message.isEmpty()) {
%>
    <div>
        <p><%= message %></p>
    </div>
<%
    }
%>
	
<form id="frm1" name="frm1" action="/ideal/controller/CourseOperationSvl" method="post">


<tr>
<th>コース名*</th>
<td>
<input type="text" name="courseName" size="30" style="ime-mode: active;"
 value="<%=course.getCourseName() %>">
</td>
</tr>

<tr>
<th>価格*</th>

<td>
<input type="text" name="price" size="6" style="ime-mode: inactive;"
 value="<%=course.getPrice() %>">
</td>
</tr>
			
<tr>
<th>オーダー可</th>

<td>
<input type="radio" name="orderFlg" <% if(course.getOrderFlg() == 1){out.print("checked");} %> value="1">可
<input type="radio" name="orderFlg" <% if(course.getOrderFlg() == 0){out.print("checked");} %> value="0">不可
</td>
</tr>	

<tr>
<th>コメント</th>
<td><textarea name="detail" cols="45" rows="6">
<%=course.getDetail() %></textarea>
</td>
</tr>

<%
for(Object obj:typeMenuList){
	if(obj != null){
		ArrayList<Menu> menuList = (ArrayList<Menu>)obj;
%>
				<tr>
					<th><%=((MenuType)menuTypes.get(count)).getTypeName() %></th>
					<td><select name="<%= COURSE_MENU_TYPE_NAME[count] %>">
						<option value="">--- 選択してください。 ---</option>
	<%					
		for(Menu menu:menuList){
			String selected = "";
			for(Object menuObj:oneCourse){
				Course courseMenu = (Course)menuObj;
				if(courseMenu.getMenuId() == menu.getMenuId()){
					selected = "selected";
				}
			}
	%>
						<option value="<%= menu.getMenuId()%>" <%=selected %>><%= menu.getMenuName() %></option>
	<%
		}
	}
	%>
						
						</select>
					</td>
				</tr>

<%
count++;
}
%>

			<tr>
					<td colspan="2" style="text-align: right; 
					                       right; background-color: Red;">
				     ※は必修入力です。<input type="submit" value="更新"></td>
				</tr>


</table>
	<input type="hidden" name="mode" value="<%= CourseOperationSvl.UPDATE %>">
	<input type="hidden" name="typeId" value="100">
	<input type="hidden" name="courseId" value="<%= course.getCourseId()%>">
	
</form>
<div align="center">
	<p>
		<a href="/ideal/controller/MenuMaintenanceSvl" ><font color="white">一覧表示に戻る</font></a>
	</p>
    </div>


</body>
</html>



