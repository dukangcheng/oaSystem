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
		log.debug("记录debug级别");
		log.info("记录info级别");
		log.warn("记录warn级别");
		log.error("记录error级别");
		log.fatal("记录fatal级别");
	}

	//测试SessionFactory
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		Session session=sessionFactory.getCurrentSession();
		Forum forum=new Forum();
		forum.setTitle("男生女生");
		forum.setDescription("交友板块");
		session.save(forum);
		//System.out.println(sessionFactory);
	}

	//测试事务
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
