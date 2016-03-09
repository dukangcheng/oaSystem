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
 * ���Ŀ�����
 * @author dukangcheng
 *
 */
public class FormAction extends BaseAction<WorkflowBean> {
	/**�������� ����ȡ��ҳ��*/
	public String start() {
		// ��ȡ��ǰ���̴�����
		User user = (User) ActionContext.getContext().getSession().get("user");
		workflowService.addStartProcessInstance(model, user);
		return "toMyTaskList";
	}

    /**�ύ��ǰ����ҳ��*/
	public String submitTaskUI() {
		// ��������ʵ��ID��ȡ��ǰ������
		Task task = workflowService.findTaskByTaskId(model.getTaskId());		
		// ��������ID��ѯ ��ҳ��Ĳ���
		Object renderedTaskForm =null;
        int count=approveInfoService.findCountByTaskId(task.getProcessInstanceId());
		String formEngineName=null;
		model.setCount(count);
        //����taskId ��ȡ  ���������Ӧ��form�ļ�
       if(count!=0){
		ApproveInfo approveInfo1=approveInfoService.findByAfterTaskId(task.getId()); //��ȡ��һ�������������Ϣ
		model.setIsAgree(approveInfo1.getIsAgree());
	    if(approveInfo1.getIsAgree()!=null&&approveInfo1.getIsAgree().equals("��ͬ��")){
           model.setAssine(approveInfo1.getUserName());
           model.setApproveInfo(approveInfo1.getComment());
	    }
       }
		renderedTaskForm=workflowService.findRenderedTaskFormByTaskId(task.getId(),formEngineName);
		ActionContext.getContext().put("renderedTaskForm", renderedTaskForm);
		//��ȡ��ǰ����֮ǰ����ʷ������Ϣ
		List<ApproveInfo> approveInfoList=workflowService.findApproveInfoList(task.getProcessInstanceId());
		ActionContext.getContext().put("approveInfoList", approveInfoList);
		// ��ȡ����֮��������������
		List<String> outcomeList = workflowService
				.findSequenceNameListByTaskId(model.getTaskId());
		ActionContext.getContext().put("outcomeList", outcomeList);
		return "submitTaskUI";
	}

	/**�ύ����*/
	public String submitTask() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		
		
		workflowService.completeSubmitTask(model, user);
		return "toMyTaskList";
	}

	/**��ǰ�û��������б�*/
	public String myTaskList() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		List<Task> listTask = workflowService
				.findTaskListByName(user.getName());
		ActionContext.getContext().put("taskList", listTask);
		return "myTaskList";
	}

	/**��ȡ�ҵ������ѯ�б�  �׳��쳣  �����ѯ���Ϊ���򱨳����쳣*/
	public String myApplicationList() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		//��ȡ�ҵ�������¼
        List<Application> hpiList = workflowService.findProcessInstance(user.getName());
		ActionContext.getContext().put("myApplicationList", hpiList);

		// ��ȡ���е�ProcessDefinitionEntity
		List<ProcessDefinitionEntity> applicationList = workflowService.findAllApplicationTemplateList();
		ActionContext.getContext().put("applicationTemplateList",
				applicationList);
		return "myApplicationList";
	}
	/**ģ���б�*/
	public String applicationTemplateList(){
		//��ȡ���е�ProcessDefinitionEntity
    	List<ProcessDefinitionEntity> applicationList=workflowService.findAllApplicationTemplateList();
    	ActionContext.getContext().put("applicationTemplateList", applicationList);
		return "applicationTemplateList";
	}
	/**�鿴������¼*/
	public String approveHistory(){
		User user=(User) ActionContext.getContext().getSession().get("user");
		List<ApproveInfo> approveInfoList=workflowService.findApproveInfoList(model.getProcessInstanceId());
		ActionContext.getContext().put("approveInfoList", approveInfoList);
		return "approveHistory";
	}
}
