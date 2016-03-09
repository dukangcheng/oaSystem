package com.web.oa.ui.action;

import java.util.List;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.ui.form.WorkflowBean;
/**
 * 审批流程模版的控制器
 * @author dukangcheng
 *
 */
public class ApplicationTemplateAction extends BaseAction<WorkflowBean> {
	/**
	 * 获取模版列表
	 * @return
	 */
    public String list(){
    	//获取所有的ProcessDefinitionEntity
    	List<ProcessDefinitionEntity> applicationList=workflowService.findAllApplicationTemplateList();
    	ActionContext.getContext().put("applicationTemplateList", applicationList);
    	return "list";
    }
}
