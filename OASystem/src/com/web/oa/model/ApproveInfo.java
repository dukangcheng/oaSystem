package com.web.oa.model;

import java.util.Date;
import java.util.Map;

/**
 * 审批信息类
 */
public class ApproveInfo {
	private int id;
	private String name;// 审批人名称
	private String isAgree; // 是否通过
	private String comment; // 审批内容
	private String taskId; // 流程任务ID 用于记录审批信息的所在的任务ID
	private String processInstanceId; // 流程实例ID
	private String processDefinitionId; // 流程定义ID
	private String userName; // 审批人
	private Date createTime; // 创建时间
    
	private String afterTaskId;  //该任务执行之后的 连线所指向的任务ID
	
	
	private Map<String,String> properties;
	
	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public String getAfterTaskId() {
		return afterTaskId;
	}

	public void setAfterTaskId(String afterTaskId) {
		this.afterTaskId = afterTaskId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(String isAgree) {
		this.isAgree = isAgree;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

}