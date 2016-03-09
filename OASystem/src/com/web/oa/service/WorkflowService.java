package com.web.oa.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.web.oa.model.Application;
import com.web.oa.model.ApproveInfo;
import com.web.oa.model.User;
import com.web.oa.ui.form.WorkflowBean;

/**
 * 工作流Service接口
 * @author dukangcheng
 *
 */
public interface WorkflowService {
    /**
     * 部署任务
     * @param file
     * @param fileName
     */
	public void addDeployment(File file, String fileName);
    /**
     * 获取部署流程列表
     * @return
     */
	public List<Deployment> findDeployments();
    /**
     * 获取流程定义列表
     * @return
     */
	public List<ProcessDefinition> findProcessDefinitions();
    /**
     * 删除部署的流程
     * @param deploymentId
     */
	public void deleteDeployment(String deploymentId);
    /**
     * 获取流程图的输入流
     * @param deploymentId
     * @param imageName
     * @return
     */
	public InputStream findImageInputStream(String deploymentId,
			String imageName);
    
    /**
     * 根据任务名称获取当前用户的任务列表
     * @param name
     * @return
     */
	public List<Task> findTaskListByName(String name);
    /**
     * 获取任务的流向
     * @param taskId
     * @return
     */
	public String findTaskFormKeyByTaskId(String taskId);
    /**
     * 获取任务完成之后的任务ID
     * @param taskId
     * @return
     */
	public List<String> findSequenceNameListByTaskId(String taskId);
    /**
     * 完成任务
     * @param model
     * @param user
     */
	public void completeSubmitTask(WorkflowBean model,User user);
    /**
     * 获取当前的流程图
     * @param taskId
     * @return
     */
	public Map<String,Object> findCurrentImageMessage(String taskId);
    /**
     * 根据当前的任务ID获取当前的流程定义
     * @param taskId
     * @return
     */
	public ProcessDefinition findProcessDefinitionByTaskId(String taskId);
    /**
     * 获取所有的表单模版名称
     * @return
     */
	public List<ProcessDefinitionEntity> findAllApplicationTemplateList();
    /**
     * 返回任务Id
     * @param model
     * @param user
     * @return
     */
	public ProcessInstance addStartProcessInstance(WorkflowBean model,User user);
    /**
     * 根据流程实例ID获取任务
     * @param processInstanceId
     * @return
     */
	public Task findTaskByProcessInstanceId(String processInstanceId);
    /**
     * 获取表单内容
     * @param id
     * @param formEngineName
     * @return
     */
	public Object findRenderedTaskFormByTaskId(String id, String formEngineName);


	public Task findTaskByTaskId(String taskId);
	
	//获取当前用户的申请列表
	public  List<Application> findProcessInstance(String name);
	//获取当前任务之前的历史审批信息
	public List<ApproveInfo> findApproveInfoList(String id);
	//根据流程实例对象获取审批信息
	public List<ApproveInfo> findApproveInfoListByProcessInstanceId(
			String processInstanceId);
	//根据任务ID获取表单数据
	public Object findTaskFormData(String taskId);
}
