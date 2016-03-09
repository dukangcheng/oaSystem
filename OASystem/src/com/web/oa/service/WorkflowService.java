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
 * ������Service�ӿ�
 * @author dukangcheng
 *
 */
public interface WorkflowService {
    /**
     * ��������
     * @param file
     * @param fileName
     */
	public void addDeployment(File file, String fileName);
    /**
     * ��ȡ���������б�
     * @return
     */
	public List<Deployment> findDeployments();
    /**
     * ��ȡ���̶����б�
     * @return
     */
	public List<ProcessDefinition> findProcessDefinitions();
    /**
     * ɾ�����������
     * @param deploymentId
     */
	public void deleteDeployment(String deploymentId);
    /**
     * ��ȡ����ͼ��������
     * @param deploymentId
     * @param imageName
     * @return
     */
	public InputStream findImageInputStream(String deploymentId,
			String imageName);
    
    /**
     * �����������ƻ�ȡ��ǰ�û��������б�
     * @param name
     * @return
     */
	public List<Task> findTaskListByName(String name);
    /**
     * ��ȡ���������
     * @param taskId
     * @return
     */
	public String findTaskFormKeyByTaskId(String taskId);
    /**
     * ��ȡ�������֮�������ID
     * @param taskId
     * @return
     */
	public List<String> findSequenceNameListByTaskId(String taskId);
    /**
     * �������
     * @param model
     * @param user
     */
	public void completeSubmitTask(WorkflowBean model,User user);
    /**
     * ��ȡ��ǰ������ͼ
     * @param taskId
     * @return
     */
	public Map<String,Object> findCurrentImageMessage(String taskId);
    /**
     * ���ݵ�ǰ������ID��ȡ��ǰ�����̶���
     * @param taskId
     * @return
     */
	public ProcessDefinition findProcessDefinitionByTaskId(String taskId);
    /**
     * ��ȡ���еı�ģ������
     * @return
     */
	public List<ProcessDefinitionEntity> findAllApplicationTemplateList();
    /**
     * ��������Id
     * @param model
     * @param user
     * @return
     */
	public ProcessInstance addStartProcessInstance(WorkflowBean model,User user);
    /**
     * ��������ʵ��ID��ȡ����
     * @param processInstanceId
     * @return
     */
	public Task findTaskByProcessInstanceId(String processInstanceId);
    /**
     * ��ȡ������
     * @param id
     * @param formEngineName
     * @return
     */
	public Object findRenderedTaskFormByTaskId(String id, String formEngineName);


	public Task findTaskByTaskId(String taskId);
	
	//��ȡ��ǰ�û��������б�
	public  List<Application> findProcessInstance(String name);
	//��ȡ��ǰ����֮ǰ����ʷ������Ϣ
	public List<ApproveInfo> findApproveInfoList(String id);
	//��������ʵ�������ȡ������Ϣ
	public List<ApproveInfo> findApproveInfoListByProcessInstanceId(
			String processInstanceId);
	//��������ID��ȡ������
	public Object findTaskFormData(String taskId);
}
