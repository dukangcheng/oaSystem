package com.web.oa.test.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.oa.model.User;


@Service("testService")
public class TestService {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void saveTwoUsers() {
		Session session = sessionFactory.getCurrentSession();
		session.save(new User());
		// int a = 1 / 0; 
		session.save(new User());
	}

	@Transactional
	public void saveUsers_23() {
		Session session = sessionFactory.getCurrentSession();

		for (int i = 1; i < 23; i++) {
			User user = new User();
			user.setName("²¿ÃÅ-" + i);
			session.save(user);
		}
	}
}
