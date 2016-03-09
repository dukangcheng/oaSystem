package com.web.oa.model;

import java.util.Date;
import java.util.Map;

/**
 * ������Ϣ��
 */
public class ApproveInfo {
	private int id;
	private String name;// ����������
	private String isAgree; // �Ƿ�ͨ��
	private String comment; // ��������
	private String taskId; // ��������ID ���ڼ�¼������Ϣ�����ڵ�����ID
	private String processInstanceId; // ����ʵ��ID
	private String processDefinitionId; // ���̶���ID
	private String userName; // ������
	private Date createTime; // ����ʱ��
    
	private String afterTaskId;  //������ִ��֮��� ������ָ�������ID
	
	
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