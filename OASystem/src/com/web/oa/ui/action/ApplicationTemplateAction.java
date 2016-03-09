package com.web.oa.ui.action;

import java.util.List;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.ui.form.WorkflowBean;
/**
 * ��������ģ��Ŀ�����
 * @author dukangcheng
 *
 */
public class ApplicationTemplateAction extends BaseAction<WorkflowBean> {
	/**
	 * ��ȡģ���б�
	 * @return
	 */
    public String list(){
    	//��ȡ���е�ProcessDefinitionEntity
    	List<ProcessDefinitionEntity> applicationList=workflowService.findAllApplicationTemplateList();
    	ActionContext.getContext().put("applicationTemplateList", applicationList);
    	return "list";
    }
}
