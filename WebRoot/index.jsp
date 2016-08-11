<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>扫描登陆</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="<%=path %>/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=path %>/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path %>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path %>/css/style.min.css?v=4.1.0" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
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
    <style type="text/css">
    	.logindev{
		 position: absolute;
		  top: 50%;
		  left: 50%;
		  transform: translate(-50%, -50%);
	
		}
    </style>
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top" style="background: #c81623;">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/aboutHui.shtml">预提案交流平台</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">H-ui</a> <span class="logo navbar-slogan f-l mr-10 hidden-xs">v1.0</span> <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
		</div>
	</div>
</header>
<body class="gray-bg">

    <div class="   logindev fadeInDown ">
        <div>
            <div>
				<div id="qrcodae"  ></div>
						

            </div>
            <h3></h3>

            <p class="text-muted text-center"> <small>扫描登陆</small>
                </p>
        </div>
    </div>
    <form id="loginFrom" action="<%=path%>/apps/userinfo/VoUserinforAction.do?method=login" method="post" accept-charset="utf-8">
    	<input type="hidden" name="shortname" id="username" value=""/>
    	<input type="hidden" name="userpassword" id="password" value="">
    </form>
</body>
<script type="text/javascript" src="<%=path %>/lib/js/qrcode.js?v=2.1.4"></script>
<script type="text/javascript" src="<%=path %>/lib/js/jquery-2.0.3.min.js?v=2.1.4"></script>
<script>
	var qrcodevalue ="";
	var t1  =null;
	$.ajax({
		url: '<%=path%>/apps/qrlogin/QrcodeloginAction.do?method=saveQrcodeloginVO',
		type: 'get',
		dataType: 'json'
	})
	.done(function(data) {
		qrcodevalue = data.qrcode
		var qrcode = new QRCode(document.getElementById('qrcodae'), {
			  text:data.qrcode,
			  width: 126,
			  height: 126,
			  colorDark : '#000000',
			  colorLight : '#ffffff',
			  correctLevel : QRCode.CorrectLevel.H
		});
				// 使用 API
		qrcode.clear();
		qrcode.makeCode(data.qrcode);	
		//重复执行某个方法 
		 window.setInterval(login,2000); 
	})

	.fail(function() {
		console.log("error");
	})
	.always(function() {
		console.log("complete");
	});
	
	function login(){ 
			$.ajax({
				url: '<%=path%>/apps/qrlogin/QrcodeloginAction.do?method=selectQrcodeloginVOById',
				type: 'post',
				data: {qrcode: qrcodevalue},
				dataType: 'json'
			})
		  .done(function(data) {
			  console.log(data.user_name);
		  	var username= data.user_name;
		  	var password = data.password;
		  	if(username !=undefined && password !=undefined){
		  		if(username!="" && password !=""){
		  			$("#username").val(username)
		  			$("#password").val(password)
			  		//去掉定时器的方法 
					window.clearInterval(t1); 
			  		//开始登陆
			  		$("#loginFrom").submit();
			  	}
		  	}
		  	
		  })
		  .fail(function() {
		  	console.log("error");
		  })
		  .always(function() {
		  	console.log("complete");
		  });
		   

	} 




</script>
</html>
