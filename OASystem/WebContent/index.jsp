<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
  response.sendRedirect(request.getContextPath()+"/homeAction_index.action");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>ϵͳ��ҳ</title>
  </head>
  
  <body>
        <%--    ��ӭ[${user.name}]��¼ϵͳ  <br>
   <a href="${pageContext.request.contextPath}/userAction_logout.action" onclick="return delConfirm('��ȷ��Ҫ�˳���')">�˳�ϵͳ</a><br>
   <s:debug></s:debug> --%>
     <br>
  </body>
</html>
