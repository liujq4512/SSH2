<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:set name="year" value="#{'1':'一年','2':'两年','3':'三年','4':'四年'}"></s:set>
	<form action="">
		<table>
		<tr><td>使用语言：<s:checkboxlist name="language" list="{'java','c','c++','c#','php'}" value="{'java','php'}"></s:checkboxlist></td></tr>
		<tr><td>使用时长:<s:radio name="year" list="year" listKey='key' listValue='value' value='1'/></td></tr>
		<tr><td>擅长语言:<s:select name="trained" list="{'java','php'}" value="java"></s:select></td></tr>
		<s:iterator value="year" status="y">
			<tr><td><input type="radio" name="year2" checked="%{#y.key==2?'checked':''}"/></td></tr>
			<s:debug ></s:debug>
		</s:iterator>
			
		</table>
	</form>
</body>
</html>