package com.web.oa.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.web.oa.base.BaseDaoImpl;
import com.web.oa.dao.UserDao;
import com.web.oa.model.User;
/**
 * 用户Dao的实现类
 * @author dukangcheng
 *
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public User getByLoginNameAndPassword(final String loginName, final String password) {
		return this.getHibernateTemplate().execute(new HibernateCallback<User>() {
			@Override
			public User doInHibernate(Session session) throws HibernateException {
				return (User) session.createQuery("from User u where u.loginName =? and u.password=? ")
						.setParameter(0, loginName)
						.setParameter(1, password)
						.uniqueResult();
			}
		});
	}

}
