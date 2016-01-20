<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../jquery/jquery-1.10.2.js"></script>
</head>

<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			type:'get',
			url:'test_json.do',
			dataType:'json',
			success:function(data){
					$('#table1').empty();
					var html ='';
					$.each(data, function(index, comment){
						html =html + '<tr><td><h>'+comment['name']+':'+comment['value']+' '+index+'</h></td></tr>';
						});
					$('#table1').html(html);
				}
				
		});
	});
</script>
<div id="div1"><table id="table1"></table></div>
</body>
</html>