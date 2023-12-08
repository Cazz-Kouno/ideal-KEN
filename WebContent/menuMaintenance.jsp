<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*,controller.*" %>
<%-- include file="incFile.jsp" --%>

<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Menu Maintenance</title>


<style>

body{
            width: 100%;
            text-align:center;
            
            /*background-image:url(/ideal/img/レストラン18.avif);*/
    		 background-size:100% auto;
            }
            background:linear-gradient(red,white,white,green);
            }
<!--
table{text-align:center;margin:auto;}
caption{font-size:18pt;color:#009967;}
tr#data0{background:#E3FFE3;}
tr#data1{background:#A4FFDB}
th{background:#007B66;color:#ECFFF3;}
td{text-align:left;vertical-align:top;white-space:nowrap;}
td#outer{padding:10px;background:#FAFFE3;}
#code{width:40px;text-align:center;}
#menu{width:280px}
#price{width:80px;text-align:right;padding-right:5px;}
#comm{width:400px;}
#ord{width:100px;text-align:center;}
#btn{width:100px;}
h1{text-align:center;background:#007B66;color:#ECFFF3;}
div#type1{width:140px;border:solid gray 1px;
background:#E3FFE3;color:#007B53;padding:2px;cursor:pointer;margin-top:5px;}
div#type2{width:140px;border:solid gray 1px;
background:#007B53;color:#E3FFE3;padding:2px;cursor:pointer;margin-top:5px;}
-->
</style>
</head>

<body>
<h1>===メニューマスターメンテナンス===</h1>
<%
String[] order = {"不可","可"};

NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("ja","JP"));
int typeId;
String style = "id = 'type1'";

typeId = (Integer)request.getAttribute("typeId");

%>

<%
String message = (String) request.getAttribute("msg");
if (message != null && !message.isEmpty()) {
%>
			
			<div>
				<p><%=message%></p>
			</div> 
<%
 }
 %>

<!-- Beanを作成 -->
<jsp:useBean id = "mType" class = "java.util.ArrayList" scope = "request"/>
<jsp:useBean id = "menu" class = "java.util.ArrayList" scope = "request"/> 
<table>
<tr>
<td id = "outer">
<%
for(int i = 0 ; i < mType.size() ; i++){
	MenuType mt = (MenuType)mType.get(i);
	if(typeId == mt.getTypeId()){
		style = " id = 'type2'";
	}else{
		style = " id = 'type1'";
	}
%>
	<form name = "frm<%= i %>" action = "/ideal/controller/MenuMaintenanceSvl" method = "post">
	<input type = "hidden" name = "mode" value = ""/>
	<input type = "hidden" name = "typeId" value = "<%= mt.getTypeId() %>"/>
	<div <%= style %> onclick = "document.frm<%= i %>.submit();">
	<%= mt.getTypeName() %>
	</div>
	</form>
<%      }                               %>
</td>
<td id = "outer">
<table><!-- インナーテーブル -->
<caption> &lt;&lt;&lt;
<%   try{                                %>
	<%= ((Menu)menu.get(0)).getTypeName() %>
<%   }catch(Exception e){ %>
	メニューがありません。
<%   }                                   %>
     &gt;&gt;&gt; </caption>
     
     <tr>
     <th id = "code">ID</th>
     <th id = "menu">メニュー</th>
     <th id = "price">価格</th>
     <th id = "comm">コメント</th>
     <th id = "ord">オーダー可</th>
     <th id = "btn" colspan = "2"></th>
     </tr>

<%
for(int i = 0 ; i < menu.size() ; i++){
	Menu m = (Menu)menu.get(i);
%>
<tr id = "data<%= i % 2 %>">
  <td id = "code"><%= m.getMenuId() %></td>
  <td id = "menu"><%= m.getMenuName() %></td>
  <td id = "price"><%= cf.format(m.getPrice()) %></td>
  <td id = "comm"><%= m.getDetail() == null ? "" : m.getDetail()%></td>
  <td id = "ord"><%= order[m.getOrderFlg()]%></td>
  <td>
  <form action = "/ideal/controller/MenuUpdateSvl" method = "post">
  <input type = "submit" value = "更新"/>
   <input type = "hidden" name = "mode" value= "<%= MenuOperationSvl.UPDATE %>"/>
  <input type = "hidden" name = "menuId" value = "<%= m.getMenuId() %>"/>
  <input type = "hidden" name = "typeId" value = "<%= typeId %>"/>
  </form>
  </td>
  <td>
  <form action = "/ideal/controller/MenuDeleteSvl" method = "post">
  <input type = "submit" value = "削除"/>
  <!--隠しフィールド-->
  <input type = "hidden" name = "mode" value= "<%= MenuOperationSvl.DELETE %>"/>
  <input type = "hidden" name = "menuId" value = "<%= m.getMenuId() %>"/>
  <input type = "hidden" name = "typeId" value = "<%= typeId %>"/>
  </form>
  </td>
  </tr>
<%    }                %>
<tr>
<form action = "/ideal/controller/MenuInsertSvl" method = "post">
<th colspan = "7">
<input type = "hidden" name = "typeId" value = "<%= typeId %>" />
<input type = "hidden" name = "mode" value = "<%= MenuOperationSvl.INSERT %>" />
<input type = "submit" value = "新しいメニューの追加"/>
</th>
</form>
</tr>
</table><!--  インナーテーブル終了 -->
</td>
</tr>
</table>
</body>
</html>