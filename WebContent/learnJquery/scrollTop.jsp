<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="../jquery/jquery-1.10.2.js"></script>
<script type="text/javascript">
	$(function(){
			var context = '';
			for(var i=0; i<100; i++){
				context +='<table id="'+ i+'"><tr><td><p>the first td of table'+ i+ '</p> </td></tr></table>';
			}
			console.log(context);
			$('#bigDiv').append(context);
		});
	$(document).scroll(function(){
		
		if(($("body").height()-$("body,html").scrollTop())<=document.documentElement.clientHeight){
			alert('get the bottom! <br> document.height()='+$(document).height()+' window.scrollTop()='+$(window).scrollTop()+'<br>');
			$('#log').append('document.height()='+$(document).height()+' window.scrollTop()='+$(window).scrollTop()+'body.height='+$("body").height()+'body,html scrollTop()'+$("body,html").scrollTop()+'clientHeight='+document.documentElement.clientHeight +'<br>');
			var context='';
			for(var i=0;i<10;i++){
				context +='<table id="'+ i+'"><tr><td><p>the first td of table'+ i+ '</p> </td></tr></table>';
			}
			$('#bigDiv').append(context);
		}else{
			
		}
		});
</script>
<body>
	<div><table>
		<tr>
			<td><div id="bigDiv"></div></td>
			<td><span id="log"></span></td>
		</tr>
	</table></div>
	
	
</body>
</html>