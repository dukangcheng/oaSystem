<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>我的申请查询</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 我的申请查询
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<div id="QueryArea">
	<div style="height: 30px">
		<s:form action="formAction_myApplicationList">
			<table border=0 cellspacing=3 cellpadding=5>
				<tr>
				<td>按条件查询：</td>
					<td>
						<s:select name="applicationTemplateId" cssClass="SelectStyle"
							list="#applicationTemplateList" listKey="id" listValue="name"
							headerKey="" headerValue="查看全部模板"
						/>
					</td>
					<td>
						<s:select name="applicationStatus" cssClass="SelectStyle"
							list="{'审批中', '未通过', '已通过'}"
							headerKey="" headerValue="查看全部状态"
						/>
					</td>
					<td><a href=""><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></a></td>
				</tr>
			</table>
		</s:form>
	</div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
				<td width="50px">编号</td>
				<td width="115px">名称</td>
				<td width="115px">流程启动人</td>
				<td width="180px">流程启动时间</td>
				<td width="115px">流程状态</td>
				<td>相关操作</td>
			</tr>
		</thead>	

		<!--显示数据列表：正在审批或审批完成的表单显示示例-->
        <tbody id="TableData" class="dataContainer" datakey="formList">
        <s:if test="#myApplicationList!=null && #myApplicationList.size()>0">
		   <s:iterator value="#myApplicationList" status="stat">
			<tr class="TableDetail1 template">
				<td align="center">${stat.count}</td>
				<td align="center"><s:property value="name"/>&nbsp;</td>
				<td align="center"><s:property value="startUserName"/>&nbsp;</td>
				<td align="center"><s:date name="startTime" format="yyyy-MM-dd  HH:mm:ss"/></td>
				<s:if test="status==2">
				    <td align="center">审核完成</td>
				</s:if>
				<s:else>
				    <td align="center">审核中&nbsp;</td>
				</s:else>
				<td>
					<s:a action="formAction_approveHistory?processInstanceId=%{id}">查看流转记录</s:a>
				</td>
			</tr>
		    </s:iterator>
         </s:if>
        
         <s:else>
             <tr class="TableDetail1 template" >
                <td colspan="6" align="center">您还没有申请相关的流程！</td>
             </tr>    
         </s:else>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail"><div id="TableTail_inside"></div></div>
</div>

<!--分页信息-->
<%-- <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %> --%>


<div class="Description">
	说明：<br />
	1，排序是：按申请时间降序排列（最后的申请在最前面）。<br>
</div>

</body>
</html>
