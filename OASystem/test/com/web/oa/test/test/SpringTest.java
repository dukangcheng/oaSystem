package com.web.oa.test.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.web.oa.model.Forum;

public class SpringTest {

	private ApplicationContext ac = new ClassPathXmlApplicationContext("application*.xml");

	private Log log = LogFactory.getLog(getClass());

	@Test
	public void testLog() throws Exception {
		log.debug("��¼debug����");
		log.info("��¼info����");
		log.warn("��¼warn����");
		log.error("��¼error����");
		log.fatal("��¼fatal����");
	}

	//����SessionFactory
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		Session session=sessionFactory.getCurrentSession();
		Forum forum=new Forum();
		forum.setTitle("����Ů��");
		forum.setDescription("���Ѱ��");
		session.save(forum);
		//System.out.println(sessionFactory);
	}

	//��������
	@Test
	public void testTransaction() throws Exception {
		TestService testService = (TestService) ac.getBean("testService");
		testService.saveTwoUsers();
	}
	
	@Test
	public void test23() throws Exception {
		TestService testService = (TestService) ac.getBean("testService");
		testService.saveUsers_23();
	}

}
