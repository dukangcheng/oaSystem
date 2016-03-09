package com.web.oa.ui.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.model.Forum;
/**
 * �����������
 * @author dukangcheng
 *
 */
public class ForumManagerAction extends BaseAction<Forum>{
	/**
	 * ����б�
	 * @return
	 */
    public String list(){
    	List<Forum> forumList=forumService.findAll();
    	ActionContext.getContext().put("forumList", forumList);
    	return "list";
    }
    /**
     * ɾ�����
     * @return
     */
    public String delete(){
    	forumService.delete(( model).getId());
    	return "toList";
    }
    /**
     * �������ҳ��
     * @return
     */
    public String addUI(){
    	return "saveUI";
    }
    /**
     * ��Ӱ��
     * @return
     */
    public String add(){
    	forumService.add(model);
    	return "toList";
    }
    /**
     * �����޸�ҳ��
     * @return
     */
    public String editUI(){
    	//�����û�   ҳ�����
    	Forum forum=forumService.findById(model.getId());
    	ActionContext.getContext().getValueStack().push(forum);
    	return "saveUI";
    }
    /**
     *�޸İ�� 
     * @return
     */
    public String edit(){
    	forumService.update(model);
    	return "toList";
    }
    /**
     * ���ư��
     * @return
     */
    public String moveUP(){
    	forumService.moveUp(model.getId());
    	return "toList";
    }
    /**
     * ���ư��
     * @return
     */
    public String moveDown(){
    	forumService.moveDown(model.getId());
    	return "toList";
    }
}
