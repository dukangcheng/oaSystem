<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>提交申请</title>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				提交申请
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>
		<s:form action="formAction_submitTask" enctype="multipart/form-data">
			<s:hidden name="taskId" />
			<s:hidden name="processInstanceId" />
			<div class="ItemBlock_Title1">
				<!-- 信息说明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7"
						src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
					请输入请假信息
				</div>
			</div>
			<s:if test="count==0">
				<div class="ItemBlockBorder">
					<div style="margin: 0 auto;" class="ItemBlock">${renderedTaskForm}</div>
				</div>
			</s:if>
			<s:else>
				<div class="ItemBlockBorder">
						<s:if test="isAgree=='不同意'">
							<tr>
								<td><h3>
										对不起！您的申请在
										${assine}
										没有通过!
									</h3></td>
							</tr>
							<tr>
								<td><h4>
										驳回意见:
                                         ${approveInfo}
									</h4></td>
							</tr>
							<div class="ItemBlockBorder">
								<div style="margin: 0 auto;" class="ItemBlock">${renderedTaskForm}</div>
							</div>
						</s:if>
						<s:else>
							<div style="margin: 0 auto;" class="ItemBlock">${renderedTaskForm}
								<table>
									<s:iterator value="#approveInfoList">
										<tr>
											<td width="100"><s:property value="userName" />审批:</td>
											<td width="100"><s:property value="isAgree" /></td>
											<td width="200" height="40px"><s:property
													value="comment" /></td>
										</tr>
									</s:iterator>
								</table>
				             </div>
						</s:else>
						<table>
							<tr>
								<td>批&nbsp;&nbsp;&nbsp;&nbsp;注:</td>
								<td width="300px"><s:textarea name="comment" /></td>
							</tr>
						</table>
				</div>
			</s:else>
			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<!-- 使用连线的名称作为按钮 -->
				<s:if test="#outcomeList!=null && #outcomeList.size()>0">
					<s:iterator value="#outcomeList" var="str">
						<s:submit name="outcome" cssClass="button_ok" value="%{str}"></s:submit>
					</s:iterator>
				</s:if>
				<s:else>
					<s:submit name="outcome" value="默认提交" cssClass="button_ok"></s:submit>
				</s:else>
			</div>
		</s:form>
	</div>

	<div class="Description">
		使用说明：<br /> 1，下载模板文件。<br /> 2，填写文档中的表单。<br /> 3，选择这个填写好的文件进行提交。<br />
		<br /> 说明2：提交表单后，就会按照 模板对应的流程 开始流转。
	</div>

</body>
</html>
