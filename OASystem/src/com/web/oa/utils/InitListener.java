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
 * 初始化监听器类
 * 如：登录用户所拥有的的权限数据
 * @author dukangcheng
 *
 */
public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sc) {
      
	}

	@Override
	public void contextInitialized(ServletContextEvent sc) {
		// 获取与容器相关的Servlet对象
		ApplicationContext ac = WebApplicationContextUtils
				.getWebApplicationContext(sc.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ac
				.getBean("privilegeService");
		// 获取顶级权限
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		
		//将顶级的权限都放入application中
		sc.getServletContext().setAttribute("topPrivilegeList",
				topPrivilegeList);
		System.out.println("---------->已准备数据   topPrivilegeList<-----------");
		List<String> appPrivilegeurls=privilegeService.findAllPrivilege();
		System.out.println("**********"+appPrivilegeurls.size()+"************");
		//将顶级的权限都放入application中
		sc.getServletContext().setAttribute("allPrivilegeUrls", appPrivilegeurls);
        System.out.println("---------->已准备数据   appPrivilegeurls<-----------");		
	}

}
