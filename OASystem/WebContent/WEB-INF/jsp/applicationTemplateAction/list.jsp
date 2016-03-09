<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>表单模板列表</title>
   	<%@ include file="/WEB-INF/jsp/public/common.jspf" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 模板管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="10%">编号</td>
            	<td width="10%">ID</td>
            	<td width="15%">模板名称</td>
				<td width="15%">所用流程</td>
				<td width="15%">版本</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="documentTemplateList">
        
        <s:iterator value="#applicationTemplateList" status="sc">
			<tr class="TableDetail1 template">
			    <td align="center">${sc.count}</td>
			    <td align="center">${id}</td>
				<td align="center">${name}&nbsp;</td> 
				<td align="center">${key}&nbsp;</td>
				<td align="center">${version}</td>
				<td>
					<s:a action="applicationTemplateAction_delete?processDefinitionId=%{id}" onclick="return delConfirm()">删除</s:a>
				</td>
			</tr>
		</s:iterator>	
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
<%-- 			<s:a action="applicationTemplateAction_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png"/></s:a> --%>
        </div>
    </div>

	<div class="description">
		说明：<br />
		1，删除时，相应的文件也被删除。<br />
		2，下载时，文件名默认为：{表单模板名称}.doc<br />
	</div>

</div>
</body>
</html>
