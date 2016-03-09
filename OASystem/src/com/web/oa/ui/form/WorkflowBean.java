package com.web.oa.ui.form;

import java.io.File;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

/**
 * modelDriven�ĸ�����
 * ����ҳ��ͷ�����֮ǰ�����ݽ�����ʹ�õ��Ĳ���
 * @author dukangcheng
 *
 */
public class WorkflowBean {
	private File file;     //�ϴ��������̵��ļ�
	private int id;        //ҵ�����Id
	
	//���̲�����ص�����
	private String fileName;    //�������̵�����
	private String fileFileName; //�ļ�����
	private String fileContentType; //�ļ����� 
	private String deploymentId;   //���̲���ID
	private String imageName;     //��ǰ����ͼƬ��ͼƬ����
	
	//��������ص�����
	private String taskId;    //����ID
	private String outcome;   //�������֮�����������        
	private String comment;   //������Ϣ
    
	private int count;  //��¼��ǰ������   ֮ǰ  ������������
	
	private String isAgree;//�Ƿ�ͨ��
	Map<String, String[]> parameterMap;  //���̱��Ľ�������
	
	private String assine;  //������
	private String approveInfo;  //������Ϣ
	
	
	public String getAssine() {
		return assine;
	}

	public void setAssine(String assine) {
		this.assine = assine;
	}

	public String getApproveInfo() {
		return approveInfo;
	}

	public void setApproveInfo(String approveInfo) {
		this.approveInfo = approveInfo;
	}

	public String getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(String isAgree) {
		this.isAgree = isAgree;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	//��ȡ�����ߵ�������Ϣ
    public Map<String, String[]> getParameterMap() {
		return ServletActionContext.getRequest().getParameterMap();
	}

	public void setParameterMap(Map<String, String[]> parameterMap) {
		this.parameterMap = parameterMap;
	}

	//��ȡ���̶����Id
	private String processDefinitionId;
	
	//����ʵ��ID
	private String processInstanceId;
	
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

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	

    public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
