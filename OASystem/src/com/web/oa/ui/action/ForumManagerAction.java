package com.web.oa.ui.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.model.Forum;
/**
 * 版块管理控制器
 * @author dukangcheng
 *
 */
public class ForumManagerAction extends BaseAction<Forum>{
	/**
	 * 板块列表
	 * @return
	 */
    public String list(){
    	List<Forum> forumList=forumService.findAll();
    	ActionContext.getContext().put("forumList", forumList);
    	return "list";
    }
    /**
     * 删除版块
     * @return
     */
    public String delete(){
    	forumService.delete(( model).getId());
    	return "toList";
    }
    /**
     * 进入添加页面
     * @return
     */
    public String addUI(){
    	return "saveUI";
    }
    /**
     * 添加版块
     * @return
     */
    public String add(){
    	forumService.add(model);
    	return "toList";
    }
    /**
     * 进入修改页面
     * @return
     */
    public String editUI(){
    	//查找用户   页面回显
    	Forum forum=forumService.findById(model.getId());
    	ActionContext.getContext().getValueStack().push(forum);
    	return "saveUI";
    }
    /**
     *修改板块 
     * @return
     */
    public String edit(){
    	forumService.update(model);
    	return "toList";
    }
    /**
     * 上移版块
     * @return
     */
    public String moveUP(){
    	forumService.moveUp(model.getId());
    	return "toList";
    }
    /**
     * 下移版块
     * @return
     */
    public String moveDown(){
    	forumService.moveDown(model.getId());
    	return "toList";
    }
}
