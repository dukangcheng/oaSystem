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
/**工作流Service的实现
 * 工作流的服务组件  包含了仓库、运行时、任务、历史、表单等service组件
 * 对流程的流转的操作
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

	/**部署流程 */
	@Override
	public void addDeployment(File file, String fileName) {
		try {
			FileInputStream inputStream = new FileInputStream(file);
			ZipInputStream zipInputStream = new ZipInputStream(inputStream);
			repositoryService.createDeployment()// 创建流程部署对象
					.name(fileName)// 添加部署名称
					.addZipInputStream(zipInputStream)// 添加要部署的文件到流程部署表中
					.deploy();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/** 查询部署的流程 */
	@Override
	public List<Deployment> findDeployments() {
		return repositoryService.createDeploymentQuery()// 创建流程部署查询
				.orderByDeploymenTime().asc().list();
	}

	/** 查询流程定义 */
	@Override
	public List<ProcessDefinition> findProcessDefinitions() {
		return repositoryService.createProcessDefinitionQuery()// 创建流程定义查询
				.orderByDeploymentId().asc().list();
	}

	/** 删除流程 */
	@Override
	public void deleteDeployment(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
	}

	/** 获取图片输入流 */
	@Override
	public InputStream findImageInputStream(String deploymentId,
			String imageName) {
		return repositoryService.getResourceAsStream(deploymentId, imageName);
	}

	/** 根据当前登录用户的用户名查询当前登陆用户的任务列表 */
	public List<Task> findTaskListByName(String name) {
		List<Task> list = taskService.createTaskQuery()// 创建当前用户的任务查询
				.taskAssignee(name)// 设置任务的办理人为当前登陆用户
				.orderByTaskCreateTime().asc().list();
		return list;
 
	}

	/** 根据当前执行的任务ID获取任务执行之后的URL，给URL会指向流程的具体走向 */
	@Override
	public String findTaskFormKeyByTaskId(String taskId) {
		// 主要使用表单service获取获取formKey
		return formService.getTaskFormData(taskId).getFormKey();
	}

	/**根据任务ID获取任务执行后连线的名称 */
	@Override
	public List<String> findSequenceNameListByTaskId(String taskId) {
		List<String> lists = new ArrayList<String>();
		Task task = taskService.createTaskQuery()// 创建任务查询
				.taskId(taskId).singleResult();

		String processDefinitionId = task.getProcessDefinitionId();// 获取流程定义ID
		// 获取ProcesDefinitionEntity实例
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(processDefinitionId);
		String processInstanceId = task.getProcessInstanceId();
		// 获取流程实例
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()// 穿件流程实例查询
				.processInstanceId(processInstanceId).singleResult();
		// 获取当前活动的ID
		String activitiId = processInstance.getActivityId();
		// 获取当前活动
		ActivityImpl activityImpl = pde.findActivity(activitiId);

		// 获取当前活动的输出连线 输出连线 不为空则进一步判断
		List<PvmTransition> pvms = activityImpl.getOutgoingTransitions();
		if (pvms != null && pvms.size() > 0) {
			for (PvmTransition pvm : pvms) {
				String sequenceName = (String) pvm.getProperty("name");
				// 如果连线名称为空 则进一步判断 是否存在网关
				if (StringUtils.isBlank(sequenceName)) {
					// 获取目标活动
					PvmActivity pa = pvm.getDestination();
					// 获取目标的名称
					String gateName = (String) pa.getProperty("name");
					// 判断目标活动的名称是否为网关的名称 (即判断是否为网关) 如果是 则获取 网关的输出连线名称
					// 并添加到lists集合中
					if (gateName != null
							&& gateName.equals("Exclusive Gateway")) {
						List<PvmTransition> ppvms = pa.getOutgoingTransitions();
						for (PvmTransition ppvm : ppvms) {
							String sequenceName1 = (String) ppvm
									.getProperty("name");
							lists.add(sequenceName1);
						}
					} else { // 如果不是 将list集合添加内容设置为默认提交
						lists.add("默认提交");
					}
				} else {
					lists.add("执行");
				}
			}
		}
		return lists;
	}

	/**完成并提交当前任务 */
	@Override
	public void completeSubmitTask(WorkflowBean model, User user) {
		// 获取输出方式
		String outcome = model.getOutcome();
		// 当前的任务ID
		String taskId = model.getTaskId();
      
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId=task.getProcessInstanceId();  //获取当前的流程是实例的ID
		//设置审批信息对象的其他相关的属性
		ApproveInfo approveInfo=new ApproveInfo();
		approveInfo.setIsAgree(outcome);
		approveInfo.setComment(model.getComment());
		approveInfo.setCreateTime(new Date());
		approveInfo.setUserName(user.getName());
		approveInfo.setProcessInstanceId(processInstanceId);
		approveInfo.setProcessDefinitionId(task.getProcessDefinitionId());
		approveInfo.setTaskId(taskId);
		approveInfo.setName(((ProcessDefinitionEntity)repositoryService
				   .getProcessDefinition(task.getProcessDefinitionId())).getName()); //设置当前流程带的名称
		// 指定当前任务的办理人(审核人)
		Authentication.setAuthenticatedUserId(user.getName());
		// 创建一个HashMap集合 用于接收输出方式信息 完成任务时存入到
		Map<String, Object> maps = new HashMap<String, Object>();
		if (outcome != null && !outcome.equals("默认提交")){
			maps.put("outcome", outcome);
		}else{
			maps.put("outcome", "默认提交");
		}
		// 获取参数
		Map<String, String[]> parameterMap = model.getParameterMap();
		Map<String,String> parameterProperties=new HashMap<String, String>();
		// 转换参数形式
		Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			String key = entry.getKey();
			if (StringUtils.defaultString(key).startsWith("fp_")) {
				String[] parameters = key.split("_");
				parameterProperties.put(parameters[1], entry.getValue()[0]);
			}
		}
		// 判断是否是第一个任务
		String usertask1 = task.getTaskDefinitionKey();
 
		if (usertask1.equals("usertask1")) {
			parameterProperties.put("userId", user.getName());
		}
		formService.saveFormData(taskId,parameterProperties);
		
		// 完成任务
        taskService.complete(taskId, maps);
        
        Task task1=taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        if(task1!=null){
        	approveInfo.setAfterTaskId(task1.getId());  //当前任务完成之后的
        	
        }else{
        	approveInfo.setAfterTaskId(null);
        }
        approveInfoDao.save(approveInfo);  //将审批信息存入数据库
	}

	/** 根据当前的任务ID查询当前的流程图 */
	@Override
	public Map<String, Object> findCurrentImageMessage(String taskId) {
		Map<String, Object> maps = new HashMap<String, Object>();
		// 获取当前的任务
		Task task = taskService.createTaskQuery()// 创建任务查询
				.taskId(taskId).singleResult();
		// 获取当前的流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(processDefinitionId);
		// 获取当前的流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		// 获取流程实例
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()// 创建流程实例查询
				.processInstanceId(processInstanceId).singleResult();
		// 获取当前的活动ID
		String activityId = processInstance.getActivityId();// 获取活动ID
		ActivityImpl act = processDefinitionEntity.findActivity(activityId);// 获取当前的活动
		// 根据当前活动获取当前活动的基本信息
		if (act != null) {
			maps.put("x", act.getX());
			maps.put("y", act.getY());
			maps.put("width", act.getWidth());
			maps.put("height", act.getHeight());
		}
		return maps;
	}

	/** 根据任务ID查询流程定义 */
	@Override
	public ProcessDefinition findProcessDefinitionByTaskId(String taskId) {
		Task task = taskService.createTaskQuery()// 创建任务查询
				.taskId(taskId).singleResult();

		String processDefinitionId = task.getProcessDefinitionId();// 获取当前的流程定义ID
		// 获取流程定义
		ProcessDefinition processDefinition = repositoryService
				.getProcessDefinition(processDefinitionId);
		return processDefinition;
	}

	/** 获取所有的表单模版名称 */
	@Override
	public List<ProcessDefinitionEntity> findAllApplicationTemplateList() {
		// 查询所有的流程定义
		List<ProcessDefinition> pds = repositoryService
				.createProcessDefinitionQuery()// 创建流程定义查询
				.orderByProcessDefinitionVersion() // 根据版本升序排列
				.asc().list();
		Map<String, ProcessDefinition> maps = new HashMap<String, ProcessDefinition>();
		if (pds != null && pds.size() > 0) {
			for (ProcessDefinition pd : pds) {
				ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService
						.getProcessDefinition(pd.getId());
				String name = pde.getName(); //获取ProcessDefinitionEntity的名称
				maps.put(name, pde);
			}
		}
		List<ProcessDefinitionEntity> lists = new ArrayList<ProcessDefinitionEntity>(
				(Collection<? extends ProcessDefinitionEntity>) maps.values());
		return lists;
	}

	/** 启动流程实例 */
	@Override
	public ProcessInstance addStartProcessInstance(WorkflowBean model, User user) {
		Map<String, String> formProperties = new HashMap<String, String>();
		// 设置当前任务的代理人
		identityService.setAuthenticatedUserId(user.getName());
		formProperties.put("inputUser", user.getName()); 
		// 根据流程定义ID 启动流程实例
		ProcessInstance processInstance = formService.submitStartFormData(
				model.getProcessDefinitionId(), formProperties);
		// 获取当前的任务 并设置办理人
		Task task = taskService.createTaskQuery()
				.executionId(processInstance.getId()).singleResult();
		task.setAssignee(user.getName());
		taskService.saveTask(task);
		return processInstance;
	}

	/** 根据流程实例获取当前任务 */
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

	/** 根据任务ID获取表单任务 */
	@Override
	public Object findRenderedTaskFormByTaskId(String taskId,String name) {
		return formService.getRenderedTaskForm(taskId,name);
	}

	/** 查询任务 */
	@Override
	public Task findTaskByTaskId(String taskId) {

		return taskService.createTaskQuery().taskId(taskId).singleResult();
	}

	/** 获取当前用户启动的流程实例 */
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
    /**根据当前的任务获取之前的历史审批信息*/
	@Override
	public List<ApproveInfo> findApproveInfoList(String id) {
	    List<ApproveInfo> approveInfoList=approveInfoDao.getApproveInfosByProcessInstanceId(id);
	    for(int i=0;i<approveInfoList.size();i++){
	    	ApproveInfo approveInfo=approveInfoList.get(i);
	    	if(approveInfo.getIsAgree()!=null&&approveInfo.getIsAgree().equals("默认提交")){
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
