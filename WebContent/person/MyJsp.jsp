<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" language="javaScript">
	function toExt(){

		document.form.action="type.do";
		alert(document.form.action);
		document.form.submit();
	}
</script>
  </head>
  
  <body>
    <form name="form" action="action_findPerson.do">
		<select name="type">
			<option value="chain">type chain</option>
			<option value="redirect">type redirect</option>
			<option value="dispatcher">type dispatcher</option>
		</select>    
    	<input type="submit" value="submit"/>
    	<input type="button"  value="toExt" onClick="toExt();"/>
    	国际化信息：<s:text name="successLogin">
    				<s:param>"liujq"</s:param>
    			</s:text>
    			<s:text name="welcome"></s:text>
    </form>
  </body>
</html>
