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
 *Activity�е�������������ʵ��  ��������תʵ�־���Ŀ���
 * @author dukangcheng
 *
 */
public class ManagerTaskHandler implements TaskListener{

	@Override
	public void notify(DelegateTask delegateTask) {
		//��session�л�ȡuser
	    User user=(User) ActionContext.getContext().getSession().get("user");
		//���ݵ�¼���������ȡ�û�
		String loginName=user.getLoginName();
		String password=user.getPassword();
		WebApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(
				ServletActionContext.getServletContext());
		UserService userService=(UserService) ac.getBean("userService");
		User user1=userService.findByLoginNameAndPassword(loginName, password);
		//��ȡ�û����ϼ�  ��������һ���Ĵ�����
       String manager=user1.getManager().getName();
       if(manager!=null&& !manager.equals("")){
		  delegateTask.setAssignee(manager);
       }else{
    	   delegateTask.setAssignee(null);
           
       }
	}

}
