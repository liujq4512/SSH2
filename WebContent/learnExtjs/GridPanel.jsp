<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="../ext/resources/css/ext-all.css"/>
<script type="text/javascript" src="../ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="../ext/ext-all-debug.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	Ext.onReady(function(){
		
		var proxy = new Ext.data.HttpProxy({url:"/SSH2/person/grid.do"});	

		var Human = new Ext.data.Record.create([
		                                        {name:'id',type:'int',mapping:'id'},
		                                        {name:'name',mapping:'name'},
		                                        {name:'sex',mapping:'sex'},
		                                        {name:'age',type:'int',mapping:'age'},
		                                        {name:'description'}
												]);
		var reader = new Ext.data.JsonReader(
				{totalProperty:'totalProperty',root:'root'},
				Human
				);
		var groupStore = new Ext.data.GroupingStore({
				proxy : proxy,
				reader : reader,
				groupField:'age',
				sortInfo:{field:'age',direction:'desc'}
		});
		groupStore.load({param:{start:0,limit:6}});

		var cm = new Ext.grid.ColumnModel([
		                                   {header:'id',dataIndex:'id'},
		                                   {header:'name',dataIndex:'name'},
		                                   {header:'sex',dataIndex:'sex'},
		                                   {header:'age',dataIndex:'age'}
		                           		]);
		var grid = new Ext.grid.GridPanel({
			title:'Personel Info',
			width:'100%',
			autoHeight:true,
			cm:cm,
			store:groupStore,
			view: new Ext.grid.GroupingView({
					forceFit:true,
					enableRowBody:true,
					showPreview:true,
					getRowClass:function(record,rowIndex,rowParams,store){
						if(this.showPreview){
								rowParams.body = '<p style="padding:5px;border:1px #DFE8F6 solid;margin:2px;"><span style="color:#15428B; font-weight:bold;"> 备注：</span>'+record.data.description+'</p>';
								return 'x-grid3-row-expanded';
							}
						return 'x-grid3-row-collapsed';
					}
				}),
		renderTo:'a',
		bbar:new Ext.PagingToolbar({
				store:groupStore,
				pagesize:6,
				displayInfo:true,
				displayMessage:'{0}-{1}of{2}',
				emptyMessage:'emptyMessage',
				items:['-',{
					icon: "../extjs/resources/images/default/dd/drop-yes.gif",
					cls: "x-btn-text-icon",
					pressed: true,
					enableToggle:true,
					text: '隐藏备注',
					cls: 'x-btn-text-icon details',
					toggleHandler: function(btn, pressed){
					var view = grid.getView();
					if(pressed){
					btn.setText("隐藏备注");
					}else{
					btn.setText("显示备注");
					}
					view.showPreview = pressed;
					view.refresh();
					}
					}]
			})
		});
	});
</script>
<body>
<div id='a'></div>
</body>
</html>