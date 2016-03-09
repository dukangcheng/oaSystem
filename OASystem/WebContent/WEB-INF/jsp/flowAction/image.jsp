<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看当前流程图</title>
</head>
<body>
<!-- 1.获取到规则流程图 -->
<img style="position: absolute;top: 0px;left: 0px;" src="<s:url action='workflowAction_processImage'>
                              <s:param name='deploymentId' value='%{#deploymentId}'/><s:param name='imageName' value='%{#imageName}'/></s:url>"></img>
<!--2.根据当前活动的坐标，动态绘制DIV-->
<s:div cssStyle="position: absolute;border:1px solid red;top:%{#imageMessage.y}px;left: %{#imageMessage.x}px;width: %{#imageMessage.width}px;height:%{#imageMessage.height}px;"></s:div>