<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path %>/hui/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/hui/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/hui/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/hui/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/hui/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path %>/hui/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>资讯列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 资讯管理 <span class="c-gray en">&gt;</span> 资讯列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c"> <span class="select-box inline">
		<form id="searchFrom">
		<select name="question_type" class="select">
			 <c:forEach items="${questionType}" var="m">
				<option value="${m.operatetype_id}" ${mQuestion.question_type ==m.operatetype_id ?" selected='selected' ":""  }>${m.content}</option>
			</c:forEach>
		</select>
			</span> 日期范围：
			<input type="text" value="${mQuestion.sdate}" name="sdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}'})" id="logmin" class="input-text Wdate" style="width:120px;">
			-
			<input type="text" value="${mQuestion.edate}" name="edate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d'})" id="logmax" class="input-text Wdate" style="width:120px;">
			<input type="text" value="${mQuestion.title}" name="title" id="" placeholder=" 预提案标题" style="width:250px" class="input-text">
			<button name="" id="" class="btn btn-success" type="button" onclick="search()"><i class="Hui-iconfont">&#xe665;</i> 搜预提案</button>
		</form>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="downFile()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" data-title="导出提案" _href="javascript:;" onclick="downFile()" href="javascript:;"><i class="Hui-iconfont">&#xe641;</i> 导出提案</a></span> <span class="r">共有数据：<strong>${page.resTotal }</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="80">ID</th>
					<th>标题</th>
					<th width="80">分类</th>
					<th width="80">来源</th>
					<th width="120">更新时间</th>
					<th width="75">浏览次数</th>
					<th width="60">发布状态</th>
					<th width="120">操作</th>
				</tr>
			</thead>
			<tbody>
			 <c:forEach items="${page.resList }" var="m">
			 	<tr id="list${m.question_id }" class="text-c">
					<td><input type="checkbox"  id="${m.question_id }"   value="" name=""></td>
					<td>10001</td>
					<td class="text-l"><u style="cursor:pointer" class="text-primary" onClick="article_edit('提案详情','<%=path%>/apps/question/MQuestionAction.do?method=selectMQuestionById&question_id=${m.question_id }','10001')" title="查看">${m.title }</u></td>
					<td>${m.userName }</td>
					<td>H-ui</td>
					<td>${m.question_time}</td>
					<td>21212 <input type='hidden'  id='${m.question_id }content' value="${m.content }"/></td>
					<td class="td-status">
						<c:choose>
	                  		<c:when test="${m.state == '1'}"><span id="${m.question_id }hfState" class="label label-success radius">已回复</span></c:when>
	                  		<c:otherwise><span  id="${m.question_id }hfState" class="label label-error radius"> 未回复</c:otherwise>
	                  	</c:choose>
					</td>
					<td class="f-14 td-manage"><a style="text-decoration:none" onClick="openFeedback('${m.question_id }')" href="javascript:;" title="审核">审核</a></td>
				</tr>
              </c:forEach>
			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript" src="<%=path %>/hui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path %>/hui/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="<%=path %>/hui/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=path %>/hui/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=path %>/hui/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="<%=path %>/hui/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	<%-- "bServerSide": true,  
	"sAjaxSource": "<%=path %>/apps/question/MQuestionAction.do?method=selectMQuestionVODatebasePage",
	"fnServerData": retrieveData,
	 "aoColumns" : [   
                    {"mData" : "question_id"},   
                    {"mData" : "title"}],   --%>
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,8]}// 不参与排序的列
	]
});

function retrieveData(sSource, aoData, fnCallback){
	 // 将客户名称加入参数数组  
	   //aoData.push( { "name": "customerName", "value": "asdas" } );//添加自己的额外参数  
	    $.ajax( {    
	        "type": "get",     
	        "contentType": "application/json",    
	        "url": sSource,     
	        "dataType": "json",    
	        "data": { aoData: JSON.stringify(aoData) }, // 以json格式传递  
	        "success": function(resp) {    
	            fnCallback(resp.aaData);   
	        }    
	    });    
}
/*资讯-添加*/
function article_add(title,url,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*资讯-编辑*/
function article_edit(title,url,id,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*资讯-删除*/
function article_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',1);
	});
}
/*资讯-审核*/
function article_shenhe(obj,id){
	layer.confirm('审核文章？', {
		btn: ['通过','不通过','取消'], 
		shade: false,
		closeBtn: 0
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布', {icon:6,time:1000});
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
		$(obj).remove();
    	layer.msg('未通过', {icon:5,time:1000});
	});	
}
/*资讯-下架*/
function article_stop(obj,id){
	layer.confirm('确认要下架吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
		$(obj).remove();
		layer.msg('已下架!',{icon: 5,time:1000});
	});
}

/*资讯-发布*/
function article_start(obj,id){
	layer.confirm('确认要发布吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布!',{icon: 6,time:1000});
	});
}
/*资讯-申请上线*/
function article_shenqing(obj,id){
	$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
	$(obj).parents("tr").find(".td-manage").html("");
	layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
}

function search(){
	var params = $("#searchFrom").serialize();
	 var searchCon=$("#search").val();
	 url='<%=path %>/apps/question/MQuestionAction.do?method=gotoView&type=grid&'+params;
   location.href=url;
	 
}
/**
 *  导出
 */
function downFile(){
	var params = $("#searchFrom").serialize();
	 var searchCon=$("#search").val();
	  $.ajax({
          type: "POST",
          url: "<%=path %>/apps/question/MQuestionAction.do?method=downExport",
          data: params,
          success: function(data){
        	  
        	  location.href="<%=path %>/framework/core/BaseToolAction.do?method=downLoad&filename="+data;
           }
      });
}


function del(){
	 var feedback_ids =  $("input[name='listckbox']:checked");
	 var ids=[];
	 for(var i=0;i<feedback_ids.length;i++){
		 ids.push(feedback_ids[i].id);
	 }
	 if(ids.length!=0){
		 $.dialog.tips('数据删除中...',600,'loading.gif');
		 $.ajax({
			 	url:'<%=path%>/apps/question/MQuestionAction.do?method=delMQuestionVO&'+getRandom(100000),
				 type:'POST',
				 dataType:'text',
				 data:{question_id:ids.join(',')},
				 success:function(data){
					 
					 $.dialog.tips('删除成功！',1,'tips.gif');
					 
					 for(var i=0;i<feedback_ids.length;i++){
						 ids.push();
						 $("#list"+feedback_ids[i].id). remove();	 
					 }
					
				 }
			 });
	 }else{
		 $.dialog.alert('请至少选择一个！');
	 }
}
</script> 
</body>
</html>