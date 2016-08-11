<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="um landscape min-width-240px min-width-320px min-width-480px min-width-768px min-width-1024px">
<head>
        <title>新华社客户端下载</title>
        <meta charset="utf-8">
        <meta name="viewport" content="target-densitydpi=device-dpi, width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
		<link rel="stylesheet" href="<%=path%>/css/new_file.css">
	
        <script src="<%=path%>/download_files/zy_control.js">
        </script><style type="text/css"></style>
        <script src="<%=path%>/download_files/zy_click.js">
        </script>
    <style>@-moz-keyframes nodeInserted{from{opacity:0.99;}to{opacity:1;}}@-webkit-keyframes nodeInserted{from{opacity:0.99;}to{opacity:1;}}@-o-keyframes nodeInserted{from{opacity:0.99;}to{opacity:1;}}@keyframes nodeInserted{from{opacity:0.99;}to{opacity:1;}}embed,object{animation-duration:.001s;-ms-animation-duration:.001s;-moz-animation-duration:.001s;-webkit-animation-duration:.001s;-o-animation-duration:.001s;animation-name:nodeInserted;-ms-animation-name:nodeInserted;-moz-animation-name:nodeInserted;-webkit-animation-name:nodeInserted;-o-animation-name:nodeInserted;}</style></head>
<body class="um-vp c-bg" ontouchstart="">
 <div id="tips" style="position:fixed; top:0px; display:none" onclick="$('#tips').hide()"></div>
 	<div id="divBody">
     <div id="page_0" class="up ub ub-ver" tabindex="0"  >
            <!--header开始-->
            <div id="header" class="uh ub">
            	<div class="umw3" ontouchstart="zy_touch()" onclick="">
                <!--插入按钮控件-->
            	</div>
            	<div class = "dd" style="text-align: center" >
					<img src="${appInfo2.appLogo}" class="lis-th1"/>
                	<a class="font1">${appInfo2.appName }</a>
                </div>
				<div class="umw3" ontouchstart="zy_touch()" onclick="">
                <!--插入按钮控件-->
            	</div>
            </div><!--
           
		   --><!--header结束-->
		   <!--content开始-->
			 <div id="content" class="ub-f1">
            	 <div class="uinn" style="">
				 <div class="uinn c-b4c uc-t" id="mttl">应用简介</div>
                	<div class="uc-b ubb ubl ubr b-a7b c-wh">
						 <div class="lis1">
						 ${appInfo2.appAbstract }
						 </div>  
						 <div class="lis1 ubb b-a7b">应用下载</div>
						 <div class="uinn01">
							<a href="${appInfo2.appURL_IOS }" 
							style="display:block;" ontouchstart="zy_touch(&#39;btn-act&#39;)" onmousedown="zy_touch(&#39;btn-act&#39;)" 
							class="tx-c uba b-af umar-b c-gra-m2 uc-a ub t-28 uinn ub-ac  ub-pc" id="mu30">   
							<div class="ufl"><img src="<%=path%>/download_files/ios-icon.png" class="umwh-icon umar-r" /></div>
							<div class=" ut-s tx-c" style="line-height:1.8em;">ios版下载</div><div class="clear"></div></a>
							<a href="${appInfo2.appURL_Android }" 
							style="display:block;" ontouchstart="zy_touch(&#39;btn-act&#39;)" onmousedown="zy_touch(&#39;btn-act&#39;)" 
							class="tx-c uba b-af umar-b c-gra-m2 uc-a ub t-28 uinn ub-ac  ub-pc" id="mu30">  
							 <div class="ufl"><img src="<%=path%>/download_files/android-icon.png" class="umwh-icon umar-r" /></div>
							 <div class=" ut-s tx-c" style="line-height:1.8em;">Android下载</div><div class="clear"></div>
							 </a>
						 </div><!-- 
						 <div class="lis1 ubb b-a7b">扫描二维码</div>
						 <div style="text-align: center" >
						   <img  style="width:150px;height:150px;  margin:16px 0;" id ="iphoneimg" src="<%=path%>/apps/qrCode/QrCodeAction.do?method=createQrCode&appID=${dAppInfo.appID }" />
						 </div>
                	--></div>
                </div>
		</div>
	</div>
	</div>
	</body>
	<%

   String userAgent = request.getHeader("User-Agent");
    boolean result = false;
    
        if (userAgent != null)
        {
            int index = userAgent.indexOf("OS ");
            if (index >-1)
            {
                result =true;
			%>
			<c:set var="osurl" value="${iosurl}"></c:set>
			<input type="hidden" id ="aHref" value="<%=path %>/images/tips_ios.png"/>
			<%
            }else{
            %>
            <c:set var="osurl" value="${androidurl}"></c:set>
            <input type="hidden" id ="aHref" value="<%=path %>/images/tips_android.png"/>
            <%
            result = false;
            }
        }
   
 %>
 <script src="<%=path%>/js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	var aHerf =document.getElementById("aHref").value;
	
	if(isWeiXin()){
		 $('#divBody').hide()
		 $('#tips').show()
		 
	 	 document.getElementById("tips").innerHTML="<img src='" + aHerf + "' width='100%' />";
	}
	 function downloadWeiXin(){
	  	$('#tips').show()
	 	 document.getElementById("tips").innerHTML="<img src='" + aHerf + "' width='100%' />";
	 }
	 function  isWeiXin(){ 
        var ua = window.navigator.userAgent.toLowerCase(); 
        if(ua.match(/MicroMessenger/i) == 'micromessenger'){ 
        	return true; 
        }else{ 
        	return false; 
        } 
    }
    //create();
    function create(){
    locationUrl ='<%=path%>/';
	 myid=4;
	 var url='<%=path%>/download/DownloadAppAction.do?method=mselectDAppInfo&appID=d0403';
	 $.ajax({
			 url:'<%=path%>/apps/sys/AccountAction.do?method=updateCreateQrClient',
			 type:'post',
			 data:{'qrid':myid,'qrurl':url},
			 success:function(responseText){
				$("#iphoneimg").attr("src",locationUrl+responseText);
			 }
		 });
 }	
</script>
</html>