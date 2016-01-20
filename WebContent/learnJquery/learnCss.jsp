<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
	#div2{background-image:url(../image/11.png); background-repeat:repeat-y;}
	#div1{border-style:solid;border-width:2px; border-color:green; width:400px; height:200px; background-color:#fff; padding:10px;margin:10px;}
	div{ width:400px; height:200px; }
	.s{
		border-style:solid dotted dashed double;
		border-width:thick medium thin 1px; 
		border-color:black red blue #aff; 
		margin:10px;
		width:200px; height:200px;}
	h{color:red;}
	td{border-style:solid; border-width:1px}
</style>
</head>
<script type="text/javascript" src="../jquery/jquery-1.10.2.js"></script>
<script type="text/javascript">

	function show(array){
		$.each(array,
				function(i,n){
					console.log('index'+i+' value'+n.innerHTML);
				}
		);
	}
	$(function(){
		console.log("hello");
		$("div:eq(2) div input:visible:eq(1)").css({background:'blue'});
		console.log($("table[name=table1] tr:eq(1) td:eq(1)").html());
		console.log($(".s").html());
		console.log($("div >div >table").html());
		console.log($("div > input").size()); //父子元素
		console.log($("table[name=table1] tr:eq(1) td:eq(1) + h").size());//匹配所有紧接在 prev 元素后的 next 元素
		console.log($("table + input").size());
		show($("table ~ input")); //匹配 prev 元素之后的所有 siblings 元素, 不包括子元素
		console.log($("table[name=table1] tr:even"));
		show($("table[name=table1] tr:even td:even:has(h):contains('row one column two')"));
		show($("table[name=table1] tr:contains('third row first column')"));
		show($("div[id=div2]"));
		show($("td:not(:parent)"));
	});
</script>

<body>
<div id="div1">
	<div name="1_div1">
		<table name="table1">
			<tr><td><h>row one column one</h></td><td><h>row one column two</h></td></tr>
			<tr><td><h>second row first column</h></td><td><h>second row first column</h></td></tr>
			<tr><td>third row first column</td><td></td></tr>
			<tr><td style="unicode-bidi:embed;"><input type="text" name="input" value="the first content" style="direction:rtl;"></td><td><h>the first content</h></td></tr>
		</table>
	</div>
</div>
<div id="div2" class="s">
	<div name="1_div1">
		<table name="table1">
			<tr><td><h>the second content</h></td></tr>
		</table>
	</div>
	<div>
		<input type="text" name="text1" value="test1">
		<input type="text" name="text2" value="test2">
	</div>
</div>
<br>
<br>
<div id="div3">
	<div name="1_div1">
		<table id="table1">
			<tr><td><h>the third content</h></td></tr>
		</table>
		<input type="text" name="text3" value="test3">
	</div>
</div>
</body>
</html>