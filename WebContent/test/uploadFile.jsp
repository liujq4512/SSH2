<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<script type="text/javascript">

function checkFileType(file){
	
	var s = file.value;
	
	var type = s.substring(s.lastIndexOf('.'));
	if(type !='jar'){
		alert("请选择 jar 文件 ！");
		document.getElementsByName('jarFiles')[0].value = '';
		return false;
	}
}

</script>
<body>

<input type='file' name='jarFiles' onchange='checkFileType(this);'>

</body>
</html>