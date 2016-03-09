package com.web.oa.utils;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.model.Privilege;
import com.web.oa.service.PrivilegeService;
/**
 * ��ʼ����������
 * �磺��¼�û���ӵ�еĵ�Ȩ������
 * @author dukangcheng
 *
 */
public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sc) {
      
	}

	@Override
	public void contextInitialized(ServletContextEvent sc) {
		// ��ȡ��������ص�Servlet����
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(sc.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ac
				.getBean("privilegeService");
		// ��ȡ����Ȩ��
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		
		//��������Ȩ�޶�����application��
		sc.getServletContext().setAttribute("topPrivilegeList",
				topPrivilegeList);
		System.out.println("---------->��׼������   topPrivilegeList<-----------");
		List<String> appPrivilegeurls=privilegeService.findAllPrivilege();
		System.out.println("**********"+appPrivilegeurls.size()+"************");
		//��������Ȩ�޶�����application��
		sc.getServletContext().setAttribute("allPrivilegeUrls", appPrivilegeurls);
        System.out.println("---------->��׼������   appPrivilegeurls<-----------");		
	}

}
