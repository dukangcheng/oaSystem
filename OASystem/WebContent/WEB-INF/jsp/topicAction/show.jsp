<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<html>
<head>
<title>查看主题：${topic.title}</title>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/forum.css" />

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
				查看主题
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--内容显示-->
	<div id="MainArea" align="center">
		<div id="PageHead"></div>
		<div class="ItemBlock_Title1" style="width: 98%">
			<font class="MenuPoint"> &gt; </font>
			<s:a action="forumAction_list">论坛</s:a>
			<font class="MenuPoint"> &gt; </font>
			<s:a action="forumAction_show?id=%{#topic.forum.id}">${topic.forum.title}</s:a>
			<font class="MenuPoint"> &gt; </font>
			<s:a action="topicAction_show?id=%{#topic.id}">${topic.title}</s:a>
			<font class="MenuPoint"> &gt;&gt; </font> 帖子阅读 <span
				style="margin-left: 30px;"> <s:a
					action="topicAction_addUI?forumId=%{#topic.forum.id}">
					<img align="absmiddle"
						src="${pageContext.request.contextPath}/style/blue/images/button/publishNewTopic.png" />
				</s:a>
			</span>
		</div>

		<div class="ForumPageTableBorder dataContainer" datakey="replyList">

			<!--显示主题标题等-->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
					<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><b>本帖主题：${topic.title}</b></td>
					<td class="ForumPageTableTitle" align="right"
						style="padding-right: 12px;"><s:a cssClass="detail"
							action="replyAction_addUI?topicId=%{#topic.id}">
							<img border="0"
								src="${pageContext.request.contextPath}/style/images/reply.gif" />
							回复
						</s:a> <a href="moveUI.html"><img border="0"
							src="${pageContext.request.contextPath}/style/images/edit.gif" />移动到其他版块</a>
						<s:a
							action="topicAction_setType?id=%{id}&type=1&forumId=%{#topic.forum.id}"
							onClick="return confirm('要把本主题设为精华吗？')">
							<img border="0"
								src="${pageContext.request.contextPath}/style/images/topicType_1.gif" />精华</s:a>
						<s:a
							action="topicAction_setType?id=%{id}&type=2&forumId=%{#topic.forum.id}"
							onClick="return confirm('要把本主题设为置顶吗？')">
							<img border="0"
								src="${pageContext.request.contextPath}/style/images/topicType_2.gif" />置顶</s:a>
						<s:a
							action="topicAction_setType?id=%{id}&type=0&forumId=%{#topic.forum.id}"
							onClick="return confirm('要把本主题设为普通吗？')">
							<img border="0"
								src="${pageContext.request.contextPath}/style/images/topicType_0.gif" />普通</s:a>
					</td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine">
					<td colspan="4"></td>
				</tr>
			</table>

			<!-- ~~~~~~~~~~~~~~~ 显示主帖（主帖只在第1页显示） ~~~~~~~~~~~~~~~ -->
	<s:if test="currentPage == 1"> 
			<div class="ListArea">
				<table border="0" cellpadding="0" cellspacing="1" width="98%">
					<tr>
						<td rowspan="3" width="130" class="PhotoArea" align="center"
							valign="top">
							<!--作者头像-->
							<div class="AuthorPhoto">
								<div>
									<span>[楼主]</span>
								</div>
								<img border="0" width="110" height="110"
									src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif"
									onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
							</div> <!--作者名称-->
							<div class="AuthorName">${topic.author.name}</div>
						</td>
						<td align="center">
							<ul class="TopicFunc">
								<!--操作列表-->
								<%-- <li class="TopicFuncLi">
									<a class="detail" href="${pageContext.request.contextPath}/BBS_Topic/saveUI.html"><img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />编辑</a>
									<a class="detail" href="#" onClick="return confirm('确定要删除本帖吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/delete.gif" />删除</a>
								</li> --%>
								<!-- 文章表情与标题 -->
								<li class="TopicSubject"><img width="19" height="19"
									src="${pageContext.request.contextPath}/style/images/face/${topic.faceIcon}.gif" />
									${topic.title}</li>
							</ul>
						</td>
					</tr>
					<tr>
						<!-- 文章内容 -->
						<td valign="top" align="center">
							<div class="Content">${topic.content}</div>
						</td>
					</tr>
					<tr>
						<!--显示楼层等信息-->
						<td class="Footer" height="28" align="center" valign="bottom">
							<ul style="margin: 0px; width: 98%;">
								<li style="float: left; line-height: 18px;"><font
									color=#C30000>[楼主]</font> <s:date name="%{#topic.postTime}"
										format="yyyy-MM-dd HH:mm:ss" /></li>
								<li style="float: right;"><a href="javascript:scroll(0,0)">
										<img border="0"
										src="${pageContext.request.contextPath}/style/images/top.gif" />
								</a></li>
							</ul>
						</td>
					</tr>
				</table>
			</div>
			</s:if>
			<!-- ~~~~~~~~~~~~~~~ 显示主帖结束 ~~~~~~~~~~~~~~~ -->


			<!-- ~~~~~~~~~~~~~~~ 显示回复列表 ~~~~~~~~~~~~~~~ -->
			<s:iterator value="recordList" status="status">
				<div class="ListArea template">
					<table border="0" cellpadding="0" cellspacing="1" width="98%">
						<tr>
							<td rowspan="3" width="130" class="PhotoArea" align="center"
								valign="top">
								<!--作者头像-->
								<div class="AuthorPhoto">
									<img border="0" width="110" height="110"
										src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif"
										onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
								</div> <!--作者名称-->
								<div class="AuthorName">${author.name}</div>
							</td>
							<td align="center">
								<ul class="TopicFunc">
									<!--操作列表-->
									<li class="TopicFuncLi"><a class="detail" href="#"
										onClick="return confirm('确定要删除本帖吗？')"><img border="0"
											src="${pageContext.request.contextPath}/style/images/delete.gif" />删除</a>
									</li>
									<!-- 文章表情与标题 -->
									<li class="TopicSubject"><img width="19" height="19"
										src="${pageContext.request.contextPath}/style/images/face/${faceIcon}.gif" />
										${title}</li>
								</ul>
							</td>
						</tr>
						<tr>
							<!-- 文章内容 -->
							<td valign="top" align="center">
								<div class="Content">${content}</div>
							</td>
						</tr>
						<tr>
							<!--显示楼层等信息-->
							<td class="Footer" height="28" align="center" valign="bottom">
								<ul style="margin: 0px; width: 98%;">
									<li style="float: left; line-height: 18px;"><font
										color=#C30000>[${(currentPage - 1) * pageSize + status.count}楼]</font>
										<s:date name="%{postTime}" format="yyyy-MM-dd HH:mm:ss" /></li>
									<li style="float: right;"><a href="javascript:scroll(0,0)">
											<img border="0"
											src="${pageContext.request.contextPath}/style/images/top.gif" />
									</a></li>
								</ul>
							</td>
						</tr>
					</table>
				</div>
			</s:iterator>
			<!-- ~~~~~~~~~~~~~~~ 显示回复列表结束 ~~~~~~~~~~~~~~~ -->
		</div>

		<!--分页信息-->
		<s:form action="topicAction_show?id=%{id}"> </s:form>
		<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>


		<div class="ForumPageTableBorder" style="margin-top: 25px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
					<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><b>快速回复</b></td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine">
					<td colspan="3"></td>
				</tr>
			</table>
		</div>

		<!--快速回复-->
		<div class="QuictReply">
			<s:form action="replyAction_add?topicId=%{id}">
				<div style="padding-left: 3px;">
					<table border="0" cellspacing="1" width="98%" cellpadding="5"
						class="TableStyle">
						<tr height="30" class="Tint">
							<td width="50px" class="Deep"><b>标题</b></td>
							<td class="no_color_bg"><s:textfield type="text"
									name="title" cssClass="InputStyle" style="width:90%"
									value="回复：%{#topic.title}" /></td>
						</tr>
						<tr class="Tint" height="200">
							<td valign="top" rowspan="2" class="Deep"><b>内容</b></td>
							<td valign="top" class="no_color_bg"><s:textarea
									name="content" style="width: 95%; height: 150px" id="editor1"></s:textarea>
								<script>
									CKEDITOR.replace('editor1', {
										customConfig : 'myconfig.js'
									});
								</script></td>
						</tr>
						<tr height="30" class="Tint">
							<td class="no_color_bg" colspan="2" align="center"><input
								type="image"
								src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG"
								style="margin-right: 15px;" /></td>
						</tr>
					</table>
				</div>
			</s:form>
		</div>
	</div>

	<div class="Description">
		说明：<br /> 1，主帖只在第一页显示。<br />
		2，只有是管理员才可以进行“移动”、“编辑”、“删除”、“精华”、“置顶”的操作。<br /> 3，删除主帖，就会删除所有的跟帖（回复）。<br />
	</div>
	<%--   <ckeditor:replace replace="editor1" basePath="${pageContext.request.contextPath}/ckeditor/"/> --%>

</body>
</html>
