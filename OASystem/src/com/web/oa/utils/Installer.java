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
 * ��ʼ�����ݵİ�װ��
 * @author dukangcheng
 *
 */
public class Installer{
		private static SessionFactory sessionFactory;
		private static Session session=sessionFactory.getCurrentSession();
	    //��װ����ʵ��
		public  void installer(Forum forum){
    	
    	//=======================================
        //���ó�������Ա
        User user=new User();
    	user.setName("��������Ա");
        user.setLoginName("admin");
        user.setPassword(DigestUtils.md5Hex("admin"));
        user.setCreateTime(new Date());
        user.setDescription("�����еĲ�����ӵ��Ȩ��");
        session.save(user);
        Privilege  menu10=new Privilege("����Ȩ��", "roleAction_setPrivilege", null, null);
        session.save(menu10);
    
    	Topic topic=null;
    	for(int i=0;i<=150;i++){
    		topic=new Topic();
    		topic.setTitle("test"+i);
    		topic.setForum(forum);
    		topic.setContent("�����һ�������ӣ���"+i);
    		topic.setType(i/75);
    		topic.setPostTime(new Date());
    		
    	}
    	//����Ȩ��
    	Privilege menu,menu1,menu2,menu3,menu4,menu5;
    	//ϵͳ����ģ��Ȩ������
    	menu=new Privilege("ϵͳ����", null, "FUNC20082.gif", null);
    	menu1=new Privilege("��λ����", "roleAction_list", null, menu);
    	menu2=new Privilege("�û�����", "userAction_list", null, menu);
    	menu3=new Privilege("���Ź���", "orgnizationAction_list", null, menu);      
        //session�б������Ȩ��
    	//========================================
        session.save(menu);
        session.save(menu1);
        session.save(menu2);
        session.save(menu3);
        session.save(new Privilege("��λ�б�","roleAction_list", null, menu1));
        session.save(new Privilege("��λɾ��", "roleAction_delete", null, menu1));
        session.save(new Privilege("��λ���", "roleAcion_add", null, menu1));
        session.save(new Privilege("��λ�޸�", "roleAction_update", null, menu1));
        session.save(new Privilege("�û��б�","userAction_list", null, menu2));
        session.save(new Privilege("�û�ɾ��", "userAction_delete", null, menu2));
        session.save(new Privilege("�û����", "userAcion_add", null, menu2));
        session.save(new Privilege("�û��޸�", "userAction_update", null, menu2));
        session.save(new Privilege("�û���ʼ������", "userAction_initPassword", null, menu2));
        session.save(new Privilege("�����б�","orgnizationAction_list", null, menu3));
        session.save(new Privilege("����ɾ��", "orgnizationAction_delete", null, menu3));
        session.save(new Privilege("�������", "orgnizationAcion_add", null, menu3));
        session.save(new Privilege("�����޸�", "orgnizationAction_update", null, menu3));
      //��̳����Ȩ������
        menu=new Privilege("���Ͻ���", null, "FUNC20064.gif", null);
        menu1=new Privilege("��̳����", "forumManagerAction_list", null, menu);
        menu2=new Privilege("��̳", "forumAction_list",null, menu);
        
        //���浽���ݿ�
        session.save(menu); 
        session.save(menu1);
        session.save(menu2);
      //����������Ȩ������
        menu=new Privilege("������ת", null, "FUNC20057.gif", null);
        menu1=new Privilege("�������̹���", "processDefinitionAction_list", null, menu);//���̶���  ���� ��������̹���
        menu2=new Privilege("����ģ�����", "applicationTemplateAction_list",null, menu); //������ٵ�֮���ģ��(������ɾ�Ĳ����)
        menu3=new Privilege("�������", "flowAction_applicationTemplateList", null, menu);   //���еı�ģ���¼
        menu4=new Privilege("��������", "flowAction_myTaskList",null, menu);          //�ҵ������ѯ
        menu5=new Privilege("�ҵ������ѯ", "flowAction_myApplicationList",null, menu);   //���������ٵ����������Ĳ�ѯ  
        //���浽���ݿ�
        session.save(menu);
        session.save(menu1);
        session.save(menu2);
        session.save(menu3);
        session.save(menu4);
        session.save(menu5);
    	//����������Ȩ������
        menu=new Privilege("������ת", null, "FUNC20057.gif", null);
        menu1=new Privilege("�������̹���", "workflowAction_list", null, menu);
        menu2=new Privilege("����ģ�����", "workflowAction_deploymentList",null, menu);
        menu3=new Privilege("�������", "workflowAction_addApplication", null, menu);
        menu4=new Privilege("��������", "workflowAction_taskList",null, menu);
        menu5=new Privilege("�ҵ������ѯ", "workflowAction_myProcessList",null, menu);
        
        session.save(menu);
        session.save(menu1);
        session.save(menu2);
        session.save(menu3);
        session.save(menu4);
        session.save(menu5);
	}
    
    public static void main(String[]args){
     	System.out.println("��װ��ʽ��ʼ��������");
    	ApplicationContext ac=new ClassPathXmlApplicationContext("application-common.xml");
         Installer installer=new Installer();
         sessionFactory=(SessionFactory) ac.getBean("sessionFactory");
    	Forum forum=(Forum)session.load(Forum.class, 9);
        installer.installer(forum);
        System.out.println("��װ����");
    }
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
