package com.web.oa.utils;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.model.User;
import com.web.oa.service.UserService;
/**
 *Activity中的任务监听器类的实现  对任务流转实现具体的控制
 * @author dukangcheng
 *
 */
public class ManagerTaskHandler implements TaskListener{

	@Override
	public void notify(DelegateTask delegateTask) {
		//从session中获取user
	    User user=(User) ActionContext.getContext().getSession().get("user");
		//根据登录名和密码获取用户
		String loginName=user.getLoginName();
		String password=user.getPassword();
		WebApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(
				ServletActionContext.getServletContext());
		UserService userService=(UserService) ac.getBean("userService");
		User user1=userService.findByLoginNameAndPassword(loginName, password);
		//获取用户的上级  即流程下一步的代理人
       String manager=user1.getManager().getName();
       if(manager!=null&& !manager.equals("")){
		  delegateTask.setAssignee(manager);
       }else{
    	   delegateTask.setAssignee(null);
           
       }
	}

}
