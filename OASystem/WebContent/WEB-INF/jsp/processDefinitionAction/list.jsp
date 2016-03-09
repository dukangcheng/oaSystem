<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>审批流程列表</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
    <script type="text/javascript">
	    function showProcessImage( pdId,resourceName){
	    	// alert("原文：" + pdId);
	    	
	    	pdId = encodeURI(pdId);
	    	// alert("第一次URL编码：" + pdId);

	    	pdId = encodeURI(pdId);
	    	// alert("第二次URL编码：" + pdId);
	    	resourceName=encodeURI(resourceName);
	    	
	    	resourceName=encodeURI(resourceName);
	    	
            var url = "processDefinitionAction_processImage.action?deploymentId=" + pdId + "&t=" + new Date() 
                      +"&imageName="+ resourceName;
            window.open(url, "查看流程图", "dialogHeight:500px;dialogWidth:600px;resizable:yes", null);
        }
    </script> 
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 审批流程管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
 <!-- 流程部署的列表 -->
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="200px">ID</td>
				<td width="80px">流程名称</td>
                <td width="300px">发布时间</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="processDefList">
        
        <s:iterator value="#dpList" status="st">
			<tr class="TableDetail1 template">
				<td>${st.count}&nbsp;</td>
				<td align="CENTER"><s:property value="name"/>&nbsp;</td>
				<td><s:date name="deploymentTime" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
				<td>
					<s:a action="processDefinitionAction_delete?deploymentId=%{id}" onclick="return delConfirm('确定要删除次流程吗？')">
<%-- 						<s:param name="key" value="%{@java.net.URLEncoder@encode(key, 'utf-8')}"></s:param> --%>
						删除
					</s:a>
				</td>
			</tr>
		</s:iterator>	
        </tbody>
    </table>
    <br>
    <hr>
    <br>
    <!-- 流程定义的列表 -->
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
             <tr align=center valign=middle id=TableTitle>
		        <td width="12%" height="20">ID</td>
		        <td width="18%" height="20">名称</td>
		        <td width="10%" height="20">流程定义的KEY</td>
		        <td width="10%" height="20">流程定义的版本</td>
		        <td width="15%" height="20">流程定义的规则文件名称</td>
		        <td width="15%" height="20">流程定义的规则图片名称</td>
		        <td width="10%" height="20">部署ID</td>
		        <td width="10%" height="20">操作</td>
		      </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="processDefList">
        
        <s:iterator value="#pdList" status="ss">
			<tr class="TableDetail1 template">
				<td align="CENTER">${ss.count}&nbsp;</td>
				<td align="CENTER"><s:property value="name"/>&nbsp;</td>
				<td align="CENTER"><s:property value="key"/>&nbsp;</td>
				<td align="CENTER"><s:property value="version"/>&nbsp;</td>
				<td align="CENTER"><s:property value="resourceName"/>&nbsp;</td>
				<td align="CENTER"><s:property value="diagramResourceName"/>&nbsp;</td>
				<td align="CENTER"><s:property value="deploymentId"/>&nbsp;</td>
				<td>
					<a href="javascript: showProcessImage('<s:property value="deploymentId"/>','<s:property value="diagramResourceName"/>')">查看流程图</a>
				</td>
			</tr>
			
		</s:iterator>	
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <table border="0" cellspacing="0" cellpadding="10" align="left">
                <tr>
			        <td><div class="FuncBtn">
                            <div class=FuncBtnHead></div>
                            <div class=FuncBtnMemo><s:a action="processDefinitionAction_addUI">部署流程定义文档</s:a></div>
                            <div class=FuncBtnTail></div>
                        </div></td>
                </tr>
			</table>
        </div>
    </div>
</div>

<div class="Description">
	说明：<br />
	1，列表显示的是所有流程定义（不同Key）的最新版本。<br />
	2，删除流程定义时，此Key的所有版本的流程定义都会被删除。<br />
	3，查看流程图时显示的这个最新版本的流程定义的图片。
</div>

</body>
</html>
