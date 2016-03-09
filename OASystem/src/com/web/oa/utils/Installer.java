package com.web.oa.utils;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.web.oa.model.Forum;
import com.web.oa.model.Privilege;
import com.web.oa.model.Topic;
import com.web.oa.model.User;
/**
 * 初始化数据的安装包
 * @author dukangcheng
 *
 */
public class Installer{
		private static SessionFactory sessionFactory;
		private static Session session=sessionFactory.getCurrentSession();
	    //安装方法实现
		public  void installer(Forum forum){
    	
    	//=======================================
        //设置超级管理员
        User user=new User();
    	user.setName("超级管理员");
        user.setLoginName("admin");
        user.setPassword(DigestUtils.md5Hex("admin"));
        user.setCreateTime(new Date());
        user.setDescription("对所有的操作都拥有权限");
        session.save(user);
        Privilege  menu10=new Privilege("设置权限", "roleAction_setPrivilege", null, null);
        session.save(menu10);
    
    	Topic topic=null;
    	for(int i=0;i<=150;i++){
    		topic=new Topic();
    		topic.setTitle("test"+i);
    		topic.setForum(forum);
    		topic.setContent("添加了一个新帖子！！"+i);
    		topic.setType(i/75);
    		topic.setPostTime(new Date());
    		
    	}
    	//设置权限
    	Privilege menu,menu1,menu2,menu3,menu4,menu5;
    	//系统管理模块权限数据
    	menu=new Privilege("系统管理", null, "FUNC20082.gif", null);
    	menu1=new Privilege("岗位管理", "roleAction_list", null, menu);
    	menu2=new Privilege("用户管理", "userAction_list", null, menu);
    	menu3=new Privilege("部门管理", "orgnizationAction_list", null, menu);      
        //session中保存各种权限
    	//========================================
        session.save(menu);
        session.save(menu1);
        session.save(menu2);
        session.save(menu3);
        session.save(new Privilege("岗位列表","roleAction_list", null, menu1));
        session.save(new Privilege("岗位删除", "roleAction_delete", null, menu1));
        session.save(new Privilege("岗位添加", "roleAcion_add", null, menu1));
        session.save(new Privilege("岗位修改", "roleAction_update", null, menu1));
        session.save(new Privilege("用户列表","userAction_list", null, menu2));
        session.save(new Privilege("用户删除", "userAction_delete", null, menu2));
        session.save(new Privilege("用户添加", "userAcion_add", null, menu2));
        session.save(new Privilege("用户修改", "userAction_update", null, menu2));
        session.save(new Privilege("用户初始化密码", "userAction_initPassword", null, menu2));
        session.save(new Privilege("部门列表","orgnizationAction_list", null, menu3));
        session.save(new Privilege("部门删除", "orgnizationAction_delete", null, menu3));
        session.save(new Privilege("部门添加", "orgnizationAcion_add", null, menu3));
        session.save(new Privilege("部门修改", "orgnizationAction_update", null, menu3));
      //论坛管理权限数据
        menu=new Privilege("网上交流", null, "FUNC20064.gif", null);
        menu1=new Privilege("论坛管理", "forumManagerAction_list", null, menu);
        menu2=new Privilege("论坛", "forumAction_list",null, menu);
        
        //保存到数据库
        session.save(menu); 
        session.save(menu1);
        session.save(menu2);
      //工作流管理权限数据
        menu=new Privilege("审批流转", null, "FUNC20057.gif", null);
        menu1=new Privilege("审批流程管理", "processDefinitionAction_list", null, menu);//流程定义  或者 部署的流程管理
        menu2=new Privilege("申请模版管理", "applicationTemplateAction_list",null, menu); //比如请假单之类的模版(进行增删改查操作)
        menu3=new Privilege("起草申请", "flowAction_applicationTemplateList", null, menu);   //所有的表单模版记录
        menu4=new Privilege("待我审批", "flowAction_myTaskList",null, menu);          //我的任务查询
        menu5=new Privilege("我的申请查询", "flowAction_myApplicationList",null, menu);   //我申请的请假单、报销单的查询  
        //保存到数据库
        session.save(menu);
        session.save(menu1);
        session.save(menu2);
        session.save(menu3);
        session.save(menu4);
        session.save(menu5);
    	//工作流管理权限数据
        menu=new Privilege("审批流转", null, "FUNC20057.gif", null);
        menu1=new Privilege("审批流程管理", "workflowAction_list", null, menu);
        menu2=new Privilege("申请模版管理", "workflowAction_deploymentList",null, menu);
        menu3=new Privilege("起草申请", "workflowAction_addApplication", null, menu);
        menu4=new Privilege("待我审批", "workflowAction_taskList",null, menu);
        menu5=new Privilege("我的申请查询", "workflowAction_myProcessList",null, menu);
        
        session.save(menu);
        session.save(menu1);
        session.save(menu2);
        session.save(menu3);
        session.save(menu4);
        session.save(menu5);
	}
    
    public static void main(String[]args){
     	System.out.println("安装正式开始。。。。");
    	ApplicationContext ac=new ClassPathXmlApplicationContext("application-common.xml");
         Installer installer=new Installer();
         sessionFactory=(SessionFactory) ac.getBean("sessionFactory");
    	Forum forum=(Forum)session.load(Forum.class, 9);
        installer.installer(forum);
        System.out.println("安装结束");
    }
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
