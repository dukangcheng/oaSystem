package com.web.oa.ui.form;

import java.io.File;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

/**
 * modelDriven的辅助类
 * 传递页面和服务器之前的数据交互所使用到的参数
 * @author dukangcheng
 *
 */
public class WorkflowBean {
	private File file;     //上传部署流程的文件
	private int id;        //业务对象Id
	
	//流程部署相关的属性
	private String fileName;    //部署流程的名称
	private String fileFileName; //文件名称
	private String fileContentType; //文件类型 
	private String deploymentId;   //流程部署ID
	private String imageName;     //当前流程图片的图片名称
	
	//与任务相关的属性
	private String taskId;    //任务ID
	private String outcome;   //任务完成之后的连线名称        
	private String comment;   //审批信息
    
	private int count;  //记录当前的任务   之前  所有审批总数
	
	private String isAgree;//是否通过
	Map<String, String[]> parameterMap;  //流程表单的解析数据
	
	private String assine;  //代办人
	private String approveInfo;  //审批信息
	
	
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

	//获取审批者的审批信息
    public Map<String, String[]> getParameterMap() {
		return ServletActionContext.getRequest().getParameterMap();
	}

	public void setParameterMap(Map<String, String[]> parameterMap) {
		this.parameterMap = parameterMap;
	}

	//获取流程定义的Id
	private String processDefinitionId;
	
	//流程实例ID
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
