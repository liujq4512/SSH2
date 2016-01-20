<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="person/action_findPerson.do" name="form1">
		<input type="submit" value="test"></input> 
		<input type="button" value="post" onclick="javascript:document.form1.action='test_post.do';document.form1.submit();">
		<input type="text" name="placeholder"/>
		<div>
			<ul>
				<li>test 1
				<li>test 2
				<li>test 3
				<li>test 4
			</ul>
		</div>
	</form>
</body>
</html>