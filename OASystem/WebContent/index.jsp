<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
  response.sendRedirect(request.getContextPath()+"/homeAction_index.action");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>系统首页</title>
  </head>
  
  <body>
        <%--    欢迎[${user.name}]登录系统  <br>
   <a href="${pageContext.request.contextPath}/userAction_logout.action" onclick="return delConfirm('您确定要退出吗？')">退出系统</a><br>
   <s:debug></s:debug> --%>
     <br>
  </body>
</html>
