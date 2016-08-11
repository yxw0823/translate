<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	 <title></title>
     <%@ include file="/common/header.jsp"%>
     <style>
     .x-panel-header-default{
    	border-color: #dfe8f6;
     	background-color:#dfe8f6;
     	background-image:none;
     }
     </style>
	 <script type="text/javascript" >
	 	Ext.Loader.setConfig({enabled: true});
	 	//定义QrcodeloginModel
		Ext.define('QrcodeloginModel', {
		    extend: 'Ext.data.Model',
		    idProperty:'id',
		    fields: [
			      			{name:'id',type:'String'}    ,  
			      			{name:'user_name',type:'String'}    ,  
			      			{name:'password',type:'String'}    ,  
			      			{name:'createtime',type:'String'}    ,  
			      			{name:'qrcode',type:'String'}  
		    ] 
		});
	 	
		//定义QrcodeloginStore
		Ext.create("Ext.data.Store",{
		    model: 'QrcodeloginModel',
		    storeId: 'qrcodelogin_store', 
			pageSize: 20,
		    proxy:{   
		        type: 'ajax',
		        method: 'POST',
		        url:'<%=path%>/apps/qrlogin/QrcodeloginAction.do?method=selectQrcodeloginVOPage',
		        reader: { 
		        	type: 'json',
		            root: 'rows',
		            totalProperty: 'total'
		        }
		    },
		    //默认无排序
		    sorters: [{
		         property: 'Qrcodelogin.id',
		         direction: 'ASC'
		     }],
		    autoLoad:false
		});
		
		//定义grid
		var qrcodelogin_store = Ext.data.StoreManager.lookup('qrcodelogin_store');
		var qrcodelogin_grid = Ext.create("Ext.grid.Panel",{  
	        loadMask: true,
	        border: false,
	        region:'center',
	        columns:[//列模式的集合 
			      	 {text:'',dataIndex:'id',hidden:true
			      	 
			      	 } ,
			      	 {text:'',dataIndex:'user_name',flex:1
			      	 
			      	 } ,
			      	 {text:'',dataIndex:'password',flex:1
			      	 
			      	 } ,
			      	 {text:'',dataIndex:'createtime',flex:1
			      	 
			      	 } ,
			      	 {text:'',dataIndex:'qrcode',flex:1
			      	 
			      	 } ,
			      		{hidden:true,flex:0}
	        ],  
	        tbar:[
		            {xtype:'button',text:'新增',iconCls:'add'
		            	,handler:saveQrcodeloginVOHandler
		            },  
		            {xtype:'button',text:'删除',iconCls:'cross'
		            	,handler:deleteQrcodeloginVOHandler
		            },
		            {xtype:'button',text:'修改',iconCls:'note_edit'
		            	,handler:updateQrcodeloginVOHandler
		            },
		            {xtype:'button',text:'查看',iconCls:'icon-search'
		            	,handler:searchQrcodeloginVOHandler
		            },
		            {xtype:'button',text:'展开查询条件',iconCls:'application_put'
		            	,handler:function(btn){
		            		var w = Ext.getCmp('search-form');
		                    btn.getText()=="展开查询条件"?btn.setText("收起查询条件"):btn.setText("展开查询条件");
		                    btn.setIconCls(w.isHidden()?"application_get":"application_put"); 
		                    w.isHidden()?w.show():w.hide();
		            	}
		            }
		        ],
	        dockedItems:[{  
	            xtype:'pagingtoolbar',//分页工具栏组件  
	            beforePageText: '第',
	            store:qrcodelogin_store,  
	            dock: 'bottom',//放在下面  
	            displayInfo: true//是否展示信息  
	        }],  
	        selType:'checkboxmodel',//选择框组件  
	        multiSelect:true,//允许选择框多选
	        store:qrcodelogin_store,
	        listeners:{
	        	'itemdblclick':function(  obj,  record,  item,  index,  e,  eOpts ){
	        		searchQrcodeloginVOHandlerSub(record.data.id);
	        	}
	        }
	    }); 
		
		 
		//刷新grid
		function reloadQrcodeloginVOGrid(){
			qrcodelogin_store.reload();
		}
		
			      				  
		//定义form
		var  qrcodelogin_form=Ext.create("Ext.form.Panel",{
				frame:true,
				overflowY:'auto',
				border:false,
				bodyStyle:"padding: 5 5 5 5",
				defaultType:'textfield',
				defaults:{
					labelWidth:90,
					width:400,
					allowBlank: false,
					msgTarget :'under'
				},
				items:[
			      				    {
							      			xtype:'hiddenfield',
							      			allowBlank: true,
							      			fieldLabel:'',
											name:'id'
							      	},
			      				   {
							      			xtype:'textfield',
							      			maxLength:100,
							      			fieldLabel:'',
											name:'user_name' 
							      	},
			      				   {
							      			xtype:'textfield',
							      			maxLength:100,
							      			fieldLabel:'',
											name:'password' 
							      	},
			      				    {
			      						 	xtype   : 'datefield',
											editable: false,
										    fieldLabel:'',
										    format:'Y-m-d',
											name:'createtime'
			      					},
			      				   {
							      			xtype:'textfield',
							      			maxLength:36,
							      			fieldLabel:'',
											name:'qrcode' 
							      	},
			      		 {xtype:'hiddenfield'  }
				]
			});
			
		//定义DetailForm
		var  qrcodelogin_detailform=Ext.create("Ext.form.Panel",{
				frame:true,
				overflowY:'auto',
				border:false,
				bodyStyle:"padding: 5 5 5 5",
				defaultType:'textfield',
				defaults:{
					labelWidth:90,
					width:400
				},
				items:[
			      				  {
			      				  	
			      				  	name:'user_name',
			      				  	xtype:'displayfield',
			      				  	fieldLabel:''
			      				  },
			      				  {
			      				  	
			      				  	name:'password',
			      				  	xtype:'displayfield',
			      				  	fieldLabel:''
			      				  },
			      				  {
			      				  	
			      				  	name:'createtime',
			      				  	xtype:'displayfield',
			      				  	fieldLabel:''
			      				  },
			      				  {
			      				  	
			      				  	name:'qrcode',
			      				  	xtype:'displayfield',
			      				  	fieldLabel:''
			      				  },
			      		 {xtype:'hiddenfield'  }
				]
			});
		
		
		//定义操作按钮
		var saveQrcodeloginBtn=generateWindowBtn('保存新增','<%=path%>/apps/qrlogin/QrcodeloginAction.do?method=saveQrcodeloginVO',{},'reloadQrcodeloginVOGrid');
		var updateQrcodeloginBtn=generateWindowBtn('保存修改','<%=path%>/apps/qrlogin/QrcodeloginAction.do?method=updateQrcodeloginVO',{},'reloadQrcodeloginVOGrid');
		
		//定义window
		var qrcodelogin_win =generateWindow(450,400,qrcodelogin_form,[]);
		
		//定义window
		var qrcodelogin_detailwin =generateWindow(450,400,qrcodelogin_detailform,[]);
		
		//新增操作
		function saveQrcodeloginVOHandler(){
			qrcodelogin_form.getForm().reset();
    		addWindowBtn("新增",qrcodelogin_win,[saveQrcodeloginBtn]);
    		unlockedForm(qrcodelogin_form);
    		qrcodelogin_win.show();
		}
		
		//解析下拉框多选数据 通用单选
		function parseRemoteData(form,action){
		}
		
		//修改操作
		function updateQrcodeloginVOHandler(){
			var qrcodelogin_id=getSingleSelection('id', this.up('gridpanel'));
    		if(qrcodelogin_id)
    		{
    			var url='<%=path%>/apps/qrlogin/QrcodeloginAction.do?method=selectQrcodeloginVOById';
    			var params={id:qrcodelogin_id};
    			loadRecord(qrcodelogin_form,url,params,parseRemoteData);
    			unlockedForm(qrcodelogin_form);
    			addWindowBtn("修改",qrcodelogin_win,[updateQrcodeloginBtn]);
    			qrcodelogin_win.show();
    		}
		}
		
	        
		//查看操作
		function searchQrcodeloginVOHandler(){
			var qrcodelogin_id=getSingleSelection('id', this.up('gridpanel'));
    		searchQrcodeloginVOHandlerSub(qrcodelogin_id);
		}
		function searchQrcodeloginVOHandlerSub(qrcodelogin_id){
			if(qrcodelogin_id){
        			var url='<%=path%>/apps/qrlogin/QrcodeloginAction.do?method=selectQrcodeloginVOById';
        			var params={id:qrcodelogin_id};
        			loadRecord(qrcodelogin_detailform,url,params,parseRemoteData);
        			lockedForm(qrcodelogin_detailform);
        			addWindowBtn("查看",qrcodelogin_detailwin,[]);
        			qrcodelogin_detailwin.show();
    		}
		}
		
		//删除操作
		function deleteQrcodeloginVOHandler(){
			var qrcodelogin_id=getMultipleSelections('id', this.up('gridpanel'));
    		if(qrcodelogin_id)
    		{
        		var params={id:qrcodelogin_id};
        		var url='<%=path%>/apps/qrlogin/QrcodeloginAction.do?method=delQrcodeloginVO';
        		removeConfirm(params, url, 'reloadQrcodeloginVOGrid');
    		}
		}
		
		var  qrcodelogin_searchform=Ext.create("Ext.form.Panel",{
				frame:false,
				collapse:false,
				hidden:true,
				id:'search-form',
				region : 'north',
				border:false,
				bodyStyle:"padding: 5 5 5 5;background-color: #dfe8f6;",
				defaultType:'textfield',
				defaults:{
					labelWidth:100,
					msgTarget :'side'
				},
				items:[
					{
					    xtype: 'fieldcontainer',
					    combineErrors: true,
					    msgTarget : 'side',
					    layout: {
                        	type: 'hbox',
                        	padding:'5'
                   		 },
					    defaults: {
					        flex: 1,
					        labelWidth : 100
					    },
					    items: [
			      			{
			      				  			margin:'0 20 0 0',
			      				  			labelWidth:60,
		      				  				maxWidth:160,	
							      			xtype:'textfield',
							      			maxLength:36,
							      			fieldLabel:'',
											name:'id' ,
											listeners: { specialkey :  enterQuery }
							 }
			      		,
			      			{
			      				  			margin:'0 20 0 0',
			      				  			labelWidth:60,
		      				  				maxWidth:160,	
							      			xtype:'textfield',
							      			maxLength:100,
							      			fieldLabel:'',
											name:'user_name' ,
											listeners: { specialkey :  enterQuery }
							 }
			      		,
			      			{
			      				  			margin:'0 20 0 0',
			      				  			labelWidth:60,
		      				  				maxWidth:160,	
							      			xtype:'textfield',
							      			maxLength:100,
							      			fieldLabel:'',
											name:'password' ,
											listeners: { specialkey :  enterQuery }
							 }
			      		,
							{
						 					margin:'0 20 0 0',
			      				  			labelWidth:60,
		      				  				maxWidth:160,	
			      						 	xtype   : 'datefield',
											editable: false,
										    fieldLabel:'',
										    format:'Y-m-d',
											name:'createtime',
											listeners: { specialkey :  enterQuery } 
			      			}
			      		,
			      			{
			      				  			margin:'0 20 0 0',
			      				  			labelWidth:60,
		      				  				maxWidth:160,	
							      			xtype:'textfield',
							      			maxLength:36,
							      			fieldLabel:'',
											name:'qrcode' ,
											listeners: { specialkey :  enterQuery }
							 }
			      		,
							 {
			      							xtype:'button' ,
				  				  			margin:'0 10 0 0',
				  				  			maxWidth:80,
			      				  			iconCls:'icon-search',
							      			text:'查询',
							      			handler:function(){
							      				var values=this.up('form').getForm().getValues();
							      				qrcodelogin_store.load({params:values});
							      			} 
							 },{
			      							xtype:'button' ,
				  				  			margin:'0 10 0 0',
				  				  			maxWidth:80,
			      				  			iconCls:'icon-edit',
							      			text:'重置',
							      			handler:function(){
							      				this.up('form').getForm().reset();
							      			} 
							 } 
			  		 ]
				   }
				]
			});
		
		
		function enterQuery(field, e) {
             if (e.getKey() == Ext.EventObject.ENTER) {    
             	var values=qrcodelogin_searchform.getForm().getValues();
 				qrcodelogin_store.load({params:values});
             }
         }
		
		Ext.onReady(function() {
 			//文字快速提示
 			Ext.QuickTips.init();
 			
 			//使用viewprot自适应宽高
 			Ext.create('Ext.container.Viewport', {
 			    layout: 'border',
 			    items: [qrcodelogin_grid,qrcodelogin_searchform] ,
 				listeners:{
 					afterrender:function(){//延迟加载
 						qrcodelogin_grid.setHeight(Ext.getBody().getHeight());
 						qrcodelogin_grid.setWidth(Ext.getBody().getWidth());
 						qrcodelogin_store.load();
 					}
 				}
 			}); 
 			
 		});
	 </script>
  </head>
  
  <body>
  </body>
</html>
