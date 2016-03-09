package com.web.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.web.oa.base.BaseService;
import com.web.oa.dao.ApproveInfoDao;
import com.web.oa.model.Application;
import com.web.oa.model.ApproveInfo;
import com.web.oa.model.User;
import com.web.oa.service.WorkflowService;
import com.web.oa.ui.form.WorkflowBean;
/**������Service��ʵ��
 * �������ķ������  �����˲ֿ⡢����ʱ��������ʷ������service���
 * �����̵���ת�Ĳ���
 * @author dukangcheng
 *
 */
public class WorkflowServiceImpl extends BaseService implements WorkflowService {

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public HistoryService getHistoryService() {
		return historyService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	public FormService getFormService() {
		return formService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}

	/**�������� */
	@Override
	public void addDeployment(File file, String fileName) {
		try {
			FileInputStream inputStream = new FileInputStream(file);
			ZipInputStream zipInputStream = new ZipInputStream(inputStream);
			repositoryService.createDeployment()// �������̲������
					.name(fileName)// ��Ӳ�������
					.addZipInputStream(zipInputStream)// ���Ҫ������ļ������̲������
					.deploy();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/** ��ѯ��������� */
	@Override
	public List<Deployment> findDeployments() {
		return repositoryService.createDeploymentQuery()// �������̲����ѯ
				.orderByDeploymenTime().asc().list();
	}

	/** ��ѯ���̶��� */
	@Override
	public List<ProcessDefinition> findProcessDefinitions() {
		return repositoryService.createProcessDefinitionQuery()// �������̶����ѯ
				.orderByDeploymentId().asc().list();
	}

	/** ɾ������ */
	@Override
	public void deleteDeployment(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
	}

	/** ��ȡͼƬ������ */
	@Override
	public InputStream findImageInputStream(String deploymentId,
			String imageName) {
		return repositoryService.getResourceAsStream(deploymentId, imageName);
	}

	/** ���ݵ�ǰ��¼�û����û�����ѯ��ǰ��½�û��������б� */
	public List<Task> findTaskListByName(String name) {
		List<Task> list = taskService.createTaskQuery()// ������ǰ�û��������ѯ
				.taskAssignee(name)// ��������İ�����Ϊ��ǰ��½�û�
				.orderByTaskCreateTime().asc().list();
		return list;
 
	}

	/** ���ݵ�ǰִ�е�����ID��ȡ����ִ��֮���URL����URL��ָ�����̵ľ������� */
	@Override
	public String findTaskFormKeyByTaskId(String taskId) {
		// ��Ҫʹ�ñ�service��ȡ��ȡformKey
		return formService.getTaskFormData(taskId).getFormKey();
	}

	/**��������ID��ȡ����ִ�к����ߵ����� */
	@Override
	public List<String> findSequenceNameListByTaskId(String taskId) {
		List<String> lists = new ArrayList<String>();
		Task task = taskService.createTaskQuery()// ���������ѯ
				.taskId(taskId).singleResult();

		String processDefinitionId = task.getProcessDefinitionId();// ��ȡ���̶���ID
		// ��ȡProcesDefinitionEntityʵ��
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(processDefinitionId);
		String processInstanceId = task.getProcessInstanceId();
		// ��ȡ����ʵ��
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()// ��������ʵ����ѯ
				.processInstanceId(processInstanceId).singleResult();
		// ��ȡ��ǰ���ID
		String activitiId = processInstance.getActivityId();
		// ��ȡ��ǰ�
		ActivityImpl activityImpl = pde.findActivity(activitiId);

		// ��ȡ��ǰ���������� ������� ��Ϊ�����һ���ж�
		List<PvmTransition> pvms = activityImpl.getOutgoingTransitions();
		if (pvms != null && pvms.size() > 0) {
			for (PvmTransition pvm : pvms) {
				String sequenceName = (String) pvm.getProperty("name");
				// �����������Ϊ�� ���һ���ж� �Ƿ��������
				if (StringUtils.isBlank(sequenceName)) {
					// ��ȡĿ��
					PvmActivity pa = pvm.getDestination();
					// ��ȡĿ�������
					String gateName = (String) pa.getProperty("name");
					// �ж�Ŀ���������Ƿ�Ϊ���ص����� (���ж��Ƿ�Ϊ����) ����� ���ȡ ���ص������������
					// ����ӵ�lists������
					if (gateName != null
							&& gateName.equals("Exclusive Gateway")) {
						List<PvmTransition> ppvms = pa.getOutgoingTransitions();
						for (PvmTransition ppvm : ppvms) {
							String sequenceName1 = (String) ppvm
									.getProperty("name");
							lists.add(sequenceName1);
						}
					} else { // ������� ��list���������������ΪĬ���ύ
						lists.add("Ĭ���ύ");
					}
				} else {
					lists.add("ִ��");
				}
			}
		}
		return lists;
	}

	/**��ɲ��ύ��ǰ���� */
	@Override
	public void completeSubmitTask(WorkflowBean model, User user) {
		// ��ȡ�����ʽ
		String outcome = model.getOutcome();
		// ��ǰ������ID
		String taskId = model.getTaskId();
      
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId=task.getProcessInstanceId();  //��ȡ��ǰ��������ʵ����ID
		//����������Ϣ�����������ص�����
		ApproveInfo approveInfo=new ApproveInfo();
		approveInfo.setIsAgree(outcome);
		approveInfo.setComment(model.getComment());
		approveInfo.setCreateTime(new Date());
		approveInfo.setUserName(user.getName());
		approveInfo.setProcessInstanceId(processInstanceId);
		approveInfo.setProcessDefinitionId(task.getProcessDefinitionId());
		approveInfo.setTaskId(taskId);
		approveInfo.setName(((ProcessDefinitionEntity)repositoryService
				   .getProcessDefinition(task.getProcessDefinitionId())).getName()); //���õ�ǰ���̴�������
		// ָ����ǰ����İ�����(�����)
		Authentication.setAuthenticatedUserId(user.getName());
		// ����һ��HashMap���� ���ڽ��������ʽ��Ϣ �������ʱ���뵽
		Map<String, Object> maps = new HashMap<String, Object>();
		if (outcome != null && !outcome.equals("Ĭ���ύ")){
			maps.put("outcome", outcome);
		}else{
			maps.put("outcome", "Ĭ���ύ");
		}
		// ��ȡ����
		Map<String, String[]> parameterMap = model.getParameterMap();
		Map<String,String> parameterProperties=new HashMap<String, String>();
		// ת��������ʽ
		Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			String key = entry.getKey();
			if (StringUtils.defaultString(key).startsWith("fp_")) {
				String[] parameters = key.split("_");
				parameterProperties.put(parameters[1], entry.getValue()[0]);
			}
		}
		// �ж��Ƿ��ǵ�һ������
		String usertask1 = task.getTaskDefinitionKey();
 
		if (usertask1.equals("usertask1")) {
			parameterProperties.put("userId", user.getName());
		}
		formService.saveFormData(taskId,parameterProperties);
		
		// �������
        taskService.complete(taskId, maps);
        
        Task task1=taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        if(task1!=null){
        	approveInfo.setAfterTaskId(task1.getId());  //��ǰ�������֮���
        	
        }else{
        	approveInfo.setAfterTaskId(null);
        }
        approveInfoDao.save(approveInfo);  //��������Ϣ�������ݿ�
	}

	/** ���ݵ�ǰ������ID��ѯ��ǰ������ͼ */
	@Override
	public Map<String, Object> findCurrentImageMessage(String taskId) {
		Map<String, Object> maps = new HashMap<String, Object>();
		// ��ȡ��ǰ������
		Task task = taskService.createTaskQuery()// ���������ѯ
				.taskId(taskId).singleResult();
		// ��ȡ��ǰ�����̶���ID
		String processDefinitionId = task.getProcessDefinitionId();
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(processDefinitionId);
		// ��ȡ��ǰ������ʵ��ID
		String processInstanceId = task.getProcessInstanceId();
		// ��ȡ����ʵ��
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()// ��������ʵ����ѯ
				.processInstanceId(processInstanceId).singleResult();
		// ��ȡ��ǰ�ĻID
		String activityId = processInstance.getActivityId();// ��ȡ�ID
		ActivityImpl act = processDefinitionEntity.findActivity(activityId);// ��ȡ��ǰ�Ļ
		// ���ݵ�ǰ���ȡ��ǰ��Ļ�����Ϣ
		if (act != null) {
			maps.put("x", act.getX());
			maps.put("y", act.getY());
			maps.put("width", act.getWidth());
			maps.put("height", act.getHeight());
		}
		return maps;
	}

	/** ��������ID��ѯ���̶��� */
	@Override
	public ProcessDefinition findProcessDefinitionByTaskId(String taskId) {
		Task task = taskService.createTaskQuery()// ���������ѯ
				.taskId(taskId).singleResult();

		String processDefinitionId = task.getProcessDefinitionId();// ��ȡ��ǰ�����̶���ID
		// ��ȡ���̶���
		ProcessDefinition processDefinition = repositoryService
				.getProcessDefinition(processDefinitionId);
		return processDefinition;
	}

	/** ��ȡ���еı�ģ������ */
	@Override
	public List<ProcessDefinitionEntity> findAllApplicationTemplateList() {
		// ��ѯ���е����̶���
		List<ProcessDefinition> pds = repositoryService
				.createProcessDefinitionQuery()// �������̶����ѯ
				.orderByProcessDefinitionVersion() // ���ݰ汾��������
				.asc().list();
		Map<String, ProcessDefinition> maps = new HashMap<String, ProcessDefinition>();
		if (pds != null && pds.size() > 0) {
			for (ProcessDefinition pd : pds) {
				ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService
						.getProcessDefinition(pd.getId());
				String name = pde.getName(); //��ȡProcessDefinitionEntity������
				maps.put(name, pde);
			}
		}
		List<ProcessDefinitionEntity> lists = new ArrayList<ProcessDefinitionEntity>(
				(Collection<? extends ProcessDefinitionEntity>) maps.values());
		return lists;
	}

	/** ��������ʵ�� */
	@Override
	public ProcessInstance addStartProcessInstance(WorkflowBean model, User user) {
		Map<String, String> formProperties = new HashMap<String, String>();
		// ���õ�ǰ����Ĵ�����
		identityService.setAuthenticatedUserId(user.getName());
		formProperties.put("inputUser", user.getName()); 
		// �������̶���ID ��������ʵ��
		ProcessInstance processInstance = formService.submitStartFormData(
				model.getProcessDefinitionId(), formProperties);
		// ��ȡ��ǰ������ �����ð�����
		Task task = taskService.createTaskQuery()
				.executionId(processInstance.getId()).singleResult();
		task.setAssignee(user.getName());
		taskService.saveTask(task);
		return processInstance;
	}

	/** ��������ʵ����ȡ��ǰ���� */
	@Override
	public Task findTaskByProcessInstanceId(String processInstanceId) {
		List<Task> tasks = taskService.createTaskQuery()
				.processInstanceId(processInstanceId).list();
		if (tasks.size() == 0) {
			return null;
		}
		Task task = tasks.get(0);
		return task;
	}

	/** ��������ID��ȡ������ */
	@Override
	public Object findRenderedTaskFormByTaskId(String taskId,String name) {
		return formService.getRenderedTaskForm(taskId,name);
	}

	/** ��ѯ���� */
	@Override
	public Task findTaskByTaskId(String taskId) {

		return taskService.createTaskQuery().taskId(taskId).singleResult();
	}

	/** ��ȡ��ǰ�û�����������ʵ�� */
	@Override
	public List<Application> findProcessInstance(String name) {
		List<Application> applicationList = new ArrayList<Application>();

		List<HistoricProcessInstance> hpiList = historyService
				.createHistoricProcessInstanceQuery().startedBy(name).list();
		for (HistoricProcessInstance hi : hpiList) {
			Application application = new Application();
             Date t=hi.getEndTime();
			if (t==null||t.equals("")) {
				application.setStatus(Application.APPLICATION_STATUS_START);
			} else {
				application.setStatus(Application.APPLICATION_STATUS_COMPLETE);
			}
			application.setStartUserName(hi.getStartUserId());
			application.setStartTime(hi.getStartTime());
			application.setId(hi.getId());
			ProcessDefinition pde = repositoryService.getProcessDefinition(hi
					.getProcessDefinitionId());
			application.setName(pde.getName());
			applicationList.add(application);
		}
		return applicationList;
	}
    /**���ݵ�ǰ�������ȡ֮ǰ����ʷ������Ϣ*/
	@Override
	public List<ApproveInfo> findApproveInfoList(String id) {
	    List<ApproveInfo> approveInfoList=approveInfoDao.getApproveInfosByProcessInstanceId(id);
	    for(int i=0;i<approveInfoList.size();i++){
	    	ApproveInfo approveInfo=approveInfoList.get(i);
	    	if(approveInfo.getIsAgree()!=null&&approveInfo.getIsAgree().equals("Ĭ���ύ")){
	    		approveInfoList.remove(i);
	    	}
	    }
		return approveInfoList;
	}

	@Override
	public List<ApproveInfo> findApproveInfoListByProcessInstanceId(
			String processInstanceId) {
	   
		return null;
	}

	@Override
	public Object findTaskFormData(String taskId) {
		return null;
	}
}
