package com.web.oa.ui.action;

import java.util.List;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.task.Task;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.model.Application;
import com.web.oa.model.ApproveInfo;
import com.web.oa.model.User;
import com.web.oa.ui.form.WorkflowBean;
/**
 * 表单的控制器
 * @author dukangcheng
 *
 */
public class FormAction extends BaseAction<WorkflowBean> {
	/**开启流程 并获取表单页面*/
	public String start() {
		// 获取当前流程代理人
		User user = (User) ActionContext.getContext().getSession().get("user");
		workflowService.addStartProcessInstance(model, user);
		return "toMyTaskList";
	}

    /**提交当前任务页面*/
	public String submitTaskUI() {
		// 根据流程实例ID获取当前的任务
		Task task = workflowService.findTaskByTaskId(model.getTaskId());		
		// 根据任务ID查询 表单页面的参数
		Object renderedTaskForm =null;
        int count=approveInfoService.findCountByTaskId(task.getProcessInstanceId());
		String formEngineName=null;
		model.setCount(count);
        //设置taskId 获取  与任务相对应的form文件
       if(count!=0){
		ApproveInfo approveInfo1=approveInfoService.findByAfterTaskId(task.getId()); //获取上一个任务的审批信息
		model.setIsAgree(approveInfo1.getIsAgree());
	    if(approveInfo1.getIsAgree()!=null&&approveInfo1.getIsAgree().equals("不同意")){
           model.setAssine(approveInfo1.getUserName());
           model.setApproveInfo(approveInfo1.getComment());
	    }
       }
		renderedTaskForm=workflowService.findRenderedTaskFormByTaskId(task.getId(),formEngineName);
		ActionContext.getContext().put("renderedTaskForm", renderedTaskForm);
		//获取当前任务之前的历史审批信息
		List<ApproveInfo> approveInfoList=workflowService.findApproveInfoList(task.getProcessInstanceId());
		ActionContext.getContext().put("approveInfoList", approveInfoList);
		// 获取任务之后的输出连线名称
		List<String> outcomeList = workflowService
				.findSequenceNameListByTaskId(model.getTaskId());
		ActionContext.getContext().put("outcomeList", outcomeList);
		return "submitTaskUI";
	}

	/**提交任务*/
	public String submitTask() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		
		
		workflowService.completeSubmitTask(model, user);
		return "toMyTaskList";
	}

	/**当前用户的任务列表*/
	public String myTaskList() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		List<Task> listTask = workflowService
				.findTaskListByName(user.getName());
		ActionContext.getContext().put("taskList", listTask);
		return "myTaskList";
	}

	/**获取我的申请查询列表  抛出异常  如果查询结果为空则报出此异常*/
	public String myApplicationList() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		//获取我的审批记录
        List<Application> hpiList = workflowService.findProcessInstance(user.getName());
		ActionContext.getContext().put("myApplicationList", hpiList);

		// 获取所有的ProcessDefinitionEntity
		List<ProcessDefinitionEntity> applicationList = workflowService.findAllApplicationTemplateList();
		ActionContext.getContext().put("applicationTemplateList",
				applicationList);
		return "myApplicationList";
	}
	/**模版列表*/
	public String applicationTemplateList(){
		//获取所有的ProcessDefinitionEntity
    	List<ProcessDefinitionEntity> applicationList=workflowService.findAllApplicationTemplateList();
    	ActionContext.getContext().put("applicationTemplateList", applicationList);
		return "applicationTemplateList";
	}
	/**查看审批记录*/
	public String approveHistory(){
		User user=(User) ActionContext.getContext().getSession().get("user");
		List<ApproveInfo> approveInfoList=workflowService.findApproveInfoList(model.getProcessInstanceId());
		ActionContext.getContext().put("approveInfoList", approveInfoList);
		return "approveHistory";
	}
}
