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
 * ���Ʋ�ĳ���
 * ���Ʋ���඼��Ҫ�̳�ActionSupport
 * ʵ��ModelDriven  ������������
 * ���е�Service�����ͨ��Spring����ע��
 * @author dukangcheng
 *
 * @param <T>
 */
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
   
    protected T model;
    /**
     * ��ȡ���Ͳ�������  Ȼ����ݲ����������÷���ķ�ʽ����Model����
     */
	public BaseAction(){
		try{
	    //�õ�������Ϣ
    	ParameterizedType pt=(ParameterizedType)this.getClass().getGenericSuperclass();
        Class clazz=(Class) pt.getActualTypeArguments()[0];
        //ͨ�����䴴����������
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

	protected int pageNum=1;  //ҳ��Ĭ��Ϊ��һҳ
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
	 * �����뵽session�е��û���Ϣȡ�� �ĳ���
	 * @return
	 */
	public User getUser(){
    	return (User) ActionContext.getContext().getSession().get("user");
    }

}
