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
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>新华网APP下载页面</title>
		<link type="text/css" rel="stylesheet"
			href="<%=path%>/css/download.css" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.8.1.min.js"></script>
	</head>
	<body>
		<div class="wrapper">
			<div class="ad-pic">
				<img src="<%=path%>/images/topic.jpg" width="909" height="500" />
			</div>
			<div class="srch">
				<input type="text" class="fl" value="${dAppInfo.appName }" id="search" placeholder="请输入应用名称" />
				<button class="srch-btn fr" onclick="javascript:search()" id="t"></button>
			</div>
			<div class="download-app cl">
				<ul>
				<c:forEach items="${applist }" var="conferenceGuestVO">
					<li>
						<img class="app-logo" src="${conferenceGuestVO.appLogo}" />
						<dl>
							<dd>
								${conferenceGuestVO.appName}
							</dd>
							<dt>
								${conferenceGuestVO.appVersion}
							</dt>
						</dl>
						<div class="dload">
							<a class="android-dload" href="javascript:void(0)"></a>
							<a class="ios-dload" href="javascript:void(0)"></a>
						</div>
						<input type="hidden" id="appQRCodeIOS" value="${conferenceGuestVO.appQRCode_IOS }">
						<input type="hidden" id="appQRCodeAndroid" value="${conferenceGuestVO.appQRCode_Android }">
						<input type="hidden" id="appURLAndroid" value="${conferenceGuestVO.appURL_Android }">
						<input type="hidden" id="appURLIOS" value="${conferenceGuestVO.appURL_IOS }">
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>
<script type="text/javascript">
	$(document).ready(function(){
		var liLength=$(".download-app li").length;
		for(var i=0;i<liLength;i++){
			if((i+1)%3==0){
				$(".download-app li").eq(i).addClass("no-b-r")
				}
			}
		$(".android-dload,.ios-dload").hover(
			function(){
				_this=$(this);
				var _thisOffset=_this.offset();
				_thisIndex=_this.parents("li").index();
				addPopupAndroid="<div class='popup'><a class='to-localhost' href='"+popupJson[_thisIndex].androidUrl+"'>下载到本地</a><img src='"+popupJson[_thisIndex].androidCode+"'/></div>"
				addPopupIos="<div class='popup'><a class='to-localhost' href='"+popupJson[_thisIndex].iosUrl+"'>下载到本地</a><img src='"+popupJson[_thisIndex].iosCode+"'/></div>"
				if(_this.hasClass("android-dload")){
					$(".wrapper").append(addPopupAndroid);
					}
				else if(_this.hasClass("ios-dload")){$(".wrapper").append(addPopupIos);}
				
				$(".popup").css({top:_thisOffset.top+22,left:_thisOffset.left-10})
				$(".popup").last().siblings(".popup").remove();
				}
		)
		$(document).click(function(){$(".popup").remove()});
		var appQRCodeIOSArray = new Array();
		var appQRCodeAndroidArray = new Array();
		var appURLAndroidArray = new Array();
		var appURLIOSArray = new Array(); 
		for(var i=0;i<liLength;i++){
				appQRCodeIOSArray[i] = $(".download-app li #appQRCodeIOS").eq(i).attr("value");
				appQRCodeAndroidArray[i] = $(".download-app li #appQRCodeAndroid").eq(i).attr("value");
				if(""==$(".download-app li #appURLAndroid").eq(i).attr("value"))
				{
					appURLAndroidArray[i] = "javascript:void(0)";
				}else
				{
					appURLAndroidArray[i] = $(".download-app li #appURLAndroid").eq(i).attr("value");
				}
				if(""==$(".download-app li #appURLAndroid").eq(i).attr("value"))
				{
					appURLIOSArray[i] = "javascript:void(0)";
				}else
				{
					appURLIOSArray[i] = $(".download-app li #appURLIOS").eq(i).attr("value");
				}
		}
		var str2 = [{"androidUrl":appURLAndroidArray[0],"androidCode":appQRCodeAndroidArray[0],"iosUrl":appURLIOSArray[0],"iosCode":appQRCodeIOSArray[0]}];
		for(var i=1;i<liLength;i++)
		{
			var str1 = [{"androidUrl":appURLAndroidArray[i],"androidCode":appQRCodeAndroidArray[i],"iosUrl":appURLIOSArray[i],"iosCode":appQRCodeIOSArray[i]}];
			str2 = str2.concat(str1);
		}
			popupJson=str2;
		});
		
	function search(){
	   var name=$("#search").val().trim();
		var	reg = /^[\u4E00-\u9FA5]{0,5}$/;
		if(!reg.test(name)){
			alert("您输入的搜索条件有误,请重新输入!(0-5个汉字)");
 		}else{
 			location.href='<%=path %>/download/DownloadAppAction.do?method=selectDAppInfo&appName='+name;
 		}
 	}
	</script>

	</body>
</html>