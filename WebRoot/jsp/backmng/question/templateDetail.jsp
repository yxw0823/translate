<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
%>
  <c:forEach items="${mQuestion.mqrList }" var="m">
	<div id="postlist" class="pl bm"> 
     <table cellspacing="0" cellpadding="0" class="ad"> 
      <tbody>
       <tr> 
        <td class="pls"> </td> 
        <td class="plc"> </td> 
       </tr> 
      </tbody>
     </table>
     <div id="post_23774">
      <table id="pid23774" class="plhin" summary="pid23774" cellspacing="0" cellpadding="0"> 
       <tbody>
        <tr> 
         <td class="pls" rowspan="2"> 
          <div id="favatar23774" class="pls favatar"> 
           <a name="newpost"></a> 
         
           <div> 
            <div class="sidea_v_avatar" onmouseover="showauthor(this, 'userinfo23774')">
             <a href="http://bbs.exmobi.cn/space-uid-1686.html" class="avtm" target="_blank"><img src="<%=path %>/js/question/avatar(1).php" /></a>
            </div> 
           </div> 
           <div class="pi sidea_v_pi"> 
            <div class="authi">
             <a href="http://bbs.exmobi.cn/space-uid-1686.html" target="_blank" class="xw1">${m.userName}</a> 
            </div> 
           </div> 
         
           <div class="sidea_pls_info">
            <div class="tns xg2">
             <table cellspacing="0" cellpadding="0">
              <tbody>
               <tr>
                <th><p>60</p>问题</th>
                <th><p>215</p>已回复</th>
                <td><p>798</p>未回复</td>
               </tr>
              </tbody>
             </table>
            </div> 
          
         
          </div> </td> 
         <td class="plc"> 
          <div class="pi sidea_top_pi"> 
           <div class="pti"> 
            <div class="pdbt"> 
            </div> 
            <div class="authi"> 
             <img class="authicn vm" id="authicon23774" src="<%=path %>/js/question/online_member.gif" /> 
             <em id="authorposton23774">发表于 <span title="${m.reply_time}"> ${m.reply_time}</span></em> 
            </div> 
           </div> 
          </div>
          <div class="pct">
           <style type="text/css">.pcb{margin-right:0}</style>
           <div class="pcb"> 
            <div class="t_fsz"> 
             <table cellspacing="0" cellpadding="0">
              <tbody>
               <tr>
                <td class="t_f" id="postmessage_23774"> ${m.reply_content}</td>
               </tr>
              </tbody>
             </table> 
            </div> 
           
          </div> </td>
        </tr> 
       
        <tr id="_postposition23774"></tr> 
        <tr> 
         <td class="pls"></td> 
         <td class="plc" style="overflow:visible;"> 
          <div class="po hin"> 
           <div class="pob cl"> 
            <p> <a href="javascript:delReply('${m.reply_id}','${m.question_id}');">删除</a> </p> 
            <ul id="mgc_post_23774_menu" class="p_pop mgcmn" style="display: none;"> 
            </ul> 
            <script type="text/javascript" reload="1">checkmgcmn('post_23774')</script> 
           </div> 
          </div> </td> 
        </tr> 
       </tbody>
      </table> 
     </div>
     </c:forEach>
     
<jsp:include page="/common/jspbottom.jsp" >
 <jsp:param name="tab" value="A1FF0953-E20F-A241-1EF4-71CE6C943D43"/>
</jsp:include>