package com.web.oa.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.oa.dao.ApproveInfoDao;
import com.web.oa.model.User;
import com.web.oa.service.ApproveInfoService;
import com.web.oa.service.ForumService;
import com.web.oa.service.OrgnizationService;
import com.web.oa.service.PrivilegeService;
import com.web.oa.service.ReplyService;
import com.web.oa.service.RoleService;
import com.web.oa.service.TopicService;
import com.web.oa.service.UserService;
import com.web.oa.service.WorkflowService;
/**
 * 控制层的抽象：
 * 控制层的类都需要继承ActionSupport
 * 实现ModelDriven  将此类抽象出来
 * 所有的Service组件都通过Spring容器注入
 * @author dukangcheng
 *
 * @param <T>
 */
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
   
    protected T model;
    /**
     * 获取泛型参数类型  然后根据参数类型利用反射的方式创建Model对象
     */
	public BaseAction(){
		try{
	    //得到类型信息
    	ParameterizedType pt=(ParameterizedType)this.getClass().getGenericSuperclass();
        Class clazz=(Class) pt.getActualTypeArguments()[0];
        //通过反射创年势力对象
        model=(T)clazz.newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	protected TopicService topicService;
	protected ReplyService replyService;
	protected ForumService forumService;
	protected UserService userService;
	protected OrgnizationService orgnizationService;
	protected RoleService roleService;
	protected PrivilegeService privilegeService;
	protected WorkflowService workflowService;
    protected ApproveInfoService approveInfoService;
	public ApproveInfoService getApproveInfoService() {
		return approveInfoService;
	}

	public void setApproveInfoService(ApproveInfoService approveInfoService) {
		this.approveInfoService = approveInfoService;
	}

	public WorkflowService getWorkflowService() {
		return workflowService;
	}

	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	protected int pageNum=1;  //页码默认为第一页
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageNum() {
		return pageNum;
	}
	public TopicService getTopicService() {
		return topicService;
	}
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	public ReplyService getReplyService() {
		return replyService;
	}
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}
	public ForumService getForumService() {
		return forumService;
	}
    public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
    public UserService getUserService() {
		return userService;
	}
	public OrgnizationService getOrgnizationService() {
		return orgnizationService;
	}
	public void setOrgnizationService(OrgnizationService orgnizationService) {
		this.orgnizationService = orgnizationService;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	@Override
	public T getModel() {
		return model;
	}
	public void setModel(T model) {
		this.model = model;
	}
	/**
	 * 将存入到session中的用户信息取出 的抽象
	 * @return
	 */
	public User getUser(){
    	return (User) ActionContext.getContext().getSession().get("user");
    }

}
