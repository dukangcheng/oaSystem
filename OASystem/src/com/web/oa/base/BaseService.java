package com.web.oa.base;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

import com.web.oa.dao.ApproveInfoDao;
import com.web.oa.dao.ForumDao;
import com.web.oa.dao.OrgnizationDao;
import com.web.oa.dao.PrivilegeDao;
import com.web.oa.dao.ReplyDao;
import com.web.oa.dao.RoleDao;
import com.web.oa.dao.TopicDao;
import com.web.oa.dao.UserDao;

/**
 * 抽象service类 ，业务service实现类具体实现注入的dao和工作流service
 * 全部注入到此类，然后所有的service实现类全部继承自这个类
 * @author dukangcheng
 * 
 *
 */
public abstract class BaseService {
	protected UserDao userDao;
	protected RoleDao roleDao;
	protected ApproveInfoDao approveInfoDao;
	protected PrivilegeDao privilegeDao;
	protected OrgnizationDao orgnizationDao;
	protected ForumDao forumDao;
	protected ReplyDao replyDao;
	protected TopicDao topicDao;
	protected RepositoryService repositoryService;
	protected RuntimeService runtimeService;
	protected TaskService taskService;
	protected HistoryService historyService;
	protected FormService formService;
	protected IdentityService identityService;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public ApproveInfoDao getApproveInfoDao() {
		return approveInfoDao;
	}

	public void setApproveInfoDao(ApproveInfoDao approveInfoDao) {
		this.approveInfoDao = approveInfoDao;
	}

	public PrivilegeDao getPrivilegeDao() {
		return privilegeDao;
	}

	public void setPrivilegeDao(PrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}

	public OrgnizationDao getOrgnizationDao() {
		return orgnizationDao;
	}

	public void setOrgnizationDao(OrgnizationDao orgnizationDao) {
		this.orgnizationDao = orgnizationDao;
	}

	public ForumDao getForumDao() {
		return forumDao;
	}

	public void setForumDao(ForumDao forumDao) {
		this.forumDao = forumDao;
	}

	public ReplyDao getReplyDao() {
		return replyDao;
	}

	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public TopicDao getTopicDao() {
		return topicDao;
	}

	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public RuntimeService getRuntimeService() {
		return runtimeService;
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

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

	public IdentityService getIdentityService() {
		return identityService;
	}

	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

}
