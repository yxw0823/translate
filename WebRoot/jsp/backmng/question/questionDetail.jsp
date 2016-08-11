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
    <title>H+ 后台主题UI框架 - 文章页面</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> 
    <%-- <link href="<%=path %>/css/bootstrap.min.css?v=3.3.6" rel="stylesheet"> --%>
     <script src="<%=path %>/lib/bootstrap3.3.5/js/bootstrap.min.js" type="text/javascript"></script>
<link  href="<%=path %>/lib/bootstrap3.3.5/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    
   <!-- <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">-->
    
    <link href="<%=path %>/css/animate.min.css" rel="stylesheet">
     <link href="<%=path %>/css/style.min.css?v=4.1.0" rel="stylesheet">
     
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight article">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="pull-right">
                            <button class="btn btn-white btn-xs" type="button">${mQuestion.question_time }</button>
                            <button class="btn btn-white btn-xs" type="button">BeginOne</button>
                            <button class="btn btn-white btn-xs" type="button">乐视超级自行车</button>
                        </div>
                        <div class="text-center article-title">
                            <h1>
                                   ${mQuestion.title }
                                </h1>
                        </div>
                       ${mQuestion.content }
                        <hr>

                        <div class="row">
                            <div class="col-lg-12">

                                <h2>评论：</h2>
                                <c:forEach items="${mQuestion.mqrList }" var="m">
                                	 <div class="social-feed-box">
	                                    <div class="social-avatar">
	                                        <a href="" class="pull-left">
	                                            <img alt="image" src="<%=path %>/images/a1.jpg">
	                                        </a>
	                                        <div class="media-body">
	                                            <a href="#">
	                                                   ${m.userName}
	                                                </a>
	                                            <small class="text-muted">${m.reply_time}</small>
	                                        </div>
	                                    </div>
	                                    <div class="social-body">
	                                        <p>
	                                           ${m.reply_content}
	                                        </p>
	                                    </div>
                                </div>
                                </c:forEach>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </div>

    </div>
   <!--  <script src="js/jquery.min.js?v=2.1.4"></script>
   <script src="js/bootstrap.min.js?v=3.3.6"></script>
   <script src="js/content.min.js?v=1.0.0"></script> -->
   <!--  <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script> -->
</body>

</html>
