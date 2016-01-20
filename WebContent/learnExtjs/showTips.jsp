<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../ext/resources/css/ext-all.css"/>
<script type="text/javascript" src="../ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="../ext/ext-all-debug.js"></script>

<title>showTips</title>
</head>
<script type="text/javascript" language="javascript">
	Ext.onReady(function(){
		Ext.QuickTips.init();
		var config = {
			title:'show prompt',
			msg:'this is a dialog,do what you want do ',
			width:200,
			multiline:true,
			closeble:false,
			buttons:Ext.Msg.YESNOCANCEL,
			icon:Ext.Msg.QUESTION,
			fn:function(btn,tx){
				Ext.Msg.alert(null,'you click the '+btn+' button,input context is '+tx);
				}
		}
		var subItems = new Array();
		subItems.push({
			'text':"input you name in prompt",
			'listeners':{'click':{'fn':function(){
											Ext.Msg.prompt('input box','input your name',function(btn,tx){
													Ext.Msg.alert('result','you click the '+btn+' button,you name is '+tx);
												},this,30);
										}}}	
		});
		subItems.push({
			'text':"show the confirm prompt",
			'listeners':{'click':{'fn':function(){
											Ext.Msg.confirm(null,'please click the button below to chose',function(btn,tx){
													if(btn == 'yes'){Ext.Msg.alert('you confirmed the result!');}
													else{Ext.Msg.alert('you cancel the choose!');}
												});
										}}}
		});
		subItems.push({'text':'show dialog','listeners':{'click':{'fn':function(){Ext.Msg.show(config);}}}});
		var buttonEdit = new Ext.SplitButton({
			'toolTip':"please click the button",
			'toolType':'title',
			'hideLabel':true,
			'listeners':{'click':{'fn':function(){alert("you click the button");}}},
			'menu': new Ext.menu.Menu({'items':subItems})
		});

		new Ext.FormPanel({
			items:[{xtype:'textfield',fieldLabel:'Name',name:'name'},
			       {xtype:'textfield',fieldLabel:'Id',name:'id'},
			       {xtype:'datefield',fieldLabel:'date',name:'date'},
			       {xtype:'},
			       buttonEdit
					]
		}).applyToMarkup('div1');
	});
</script>
<body>

<div id="div1"></div>
</body>
</html>