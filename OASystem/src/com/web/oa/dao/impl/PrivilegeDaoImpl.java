package com.web.oa.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.web.oa.base.BaseDaoImpl;
import com.web.oa.dao.PrivilegeDao;
import com.web.oa.model.Privilege;
/**
 * 权限Dao的实现类
 * @author dukangcheng
 *
 */
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements PrivilegeDao{

	@Override
	public List<Privilege> getTopList() {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Privilege>>() {

			@Override
			public List<Privilege> doInHibernate(Session session)
					throws HibernateException {
				return session.createQuery("from Privilege p where p.parent is null").list();
			}
		});
	}

	@Override
	public List<String> getAllPrivileges() {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<String>>() {
			@Override
			public List<String> doInHibernate(Session session)
					throws HibernateException {
				return session.createQuery("select DISTINCT p.url from Privilege p where p.url is not null").list();
			}
		});
	}

	

}
