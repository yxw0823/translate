<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<HTML>
<HEAD>
<TITLE>404错误页面</TITLE>
<!--STATUS OK-->
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK href="<%=path%>/favicon.ico" type=image/x-icon
	rel="shortcut icon">
<SCRIPT>
	window.wpo = {
		start : new Date * 1,
		pid : 109,
		page : 'qing'
	}
</SCRIPT>
<link href="<%=path%>/css/qbase_datauri.css" type=text/css rel=stylesheet>
<!--<![endif]-->
<STYLE type=text/css>
.mod-notfound {
	BORDER-RIGHT: #e1e1e1 1px solid;
	BORDER-TOP: #e1e1e1 1px solid;
	MARGIN-TOP: 10px;
	BACKGROUND: #fff;
	BORDER-LEFT: #e1e1e1 1px solid;
	BORDER-BOTTOM: #e1e1e1 1px solid;
	HEIGHT: 485px;
	TEXT-ALIGN: center;
	border-radius: 10px
}
</STYLE>

<META content="MSHTML 6.00.6000.17117" name=GENERATOR>
</HEAD>
<BODY>
	<SECTION class=mod-page-body>
		<DIV class="mod-page-main wordwrap clearfix">
			<DIV class=x-page-container>
				<DIV class="mod-notfound grid-98">
					<IMG class=img-notfound height=320 src="<%=path%>/images/notfound.gif"
						width=520>
					<P style="FONT-SIZE: 24px; LINE-HEIGHT: 70px">啊~哦~
						您要查看的资源不存在或已删除！</P>
					<P style="MARGIN-BOTTOM: 30px">请检查您输入的网址是否正确，或者点击链接继续浏览空间</P>
					<P style="FONT-SIZE: 14px; LINE-HEIGHT: 20px">
						您可以回到 <A href="javascript:history.go(-1);">上一页</A> 或回到 <A href="<%=path%>">登录页</A>	
					</P>
				</DIV>
			</DIV>
		</DIV>
	</SECTION>
	<FOOTER class="mod-footer mod-cs-footer">
		<DIV class="clearfix hidden-box"></DIV>
		<DIV class=footer-box>
			<DIV class=inner-box></DIV>
			<DIV class=copy-box>Copyright ©China mobile All Rights
				Reserved南京烽火科技股份有限公司</DIV>
		</DIV>
	</FOOTER>

</BODY>
</HTML>
