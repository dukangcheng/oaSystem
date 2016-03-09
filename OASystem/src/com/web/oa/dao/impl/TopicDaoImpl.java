package com.web.oa.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.web.oa.base.BaseDaoImpl;
import com.web.oa.dao.TopicDao;
import com.web.oa.model.Forum;
import com.web.oa.model.Topic;

/**
 * 帖子Dao的实现类
 * @author dukangcheng
 *
 */
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements TopicDao {
	@Deprecated
	@Override
	public List getPageList(final int pageSize, final Forum forum,
			final int pageNum) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<List<Topic>>() {
					@Override
					@SuppressWarnings("unchecked")
					public List<Topic> doInHibernate(Session session)
							throws HibernateException {
						return session
								.createQuery(
										"from Topic t where t.forum =? order by (CASE t.type WHEN 2 THEN 2 ELSE 0 END) desc, "
												+ "t.lastUpdateTime desc")
								.setParameter(0, forum)
								.setFirstResult((pageNum - 1) * pageSize)
								.setMaxResults(pageSize).list();
					}
				});
	}
	@Deprecated
	@Override
	public int getBeanCount(final Forum forum) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {

					@Override
					public Integer doInHibernate(Session session)
							throws HibernateException {
						Long count = (Long) session
								.createQuery(
										"select count(*) from Topic t where t.forum =?")
								.setParameter(0, forum).uniqueResult();
						return count.intValue();
					}
				});
	}
	@Deprecated
	@Override
	public List getPageList(final int pageSize, final Forum forum,
			final int pageNum, final String hql) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<List>() {

					@Override
					public List doInHibernate(Session session)
							throws HibernateException {
						return session.createQuery(hql).setParameter(0, forum)
								.setFirstResult((pageNum - 1) * pageSize)
								.setMaxResults(pageSize).list();
					}
				});
	}
	@Deprecated
	@Override
	public int getBeanCount(final Forum forum, final String countSql) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {

					@Override
					public Integer doInHibernate(Session session)
							throws HibernateException {
						Long count = (Long) session.createQuery(countSql)
								.setParameter(0, forum).uniqueResult();
						return count.intValue();
					}
				});
	}
	@Deprecated
	@Override
	public List getPageList(final int pageSize, final List parameters,
			final int pageNum, final String hql) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<List>() {

					@Override
					public List doInHibernate(Session session)
							throws HibernateException {
						Query queryList = session.createQuery(hql);
						Object[] objs = parameters.toArray();
						for (int i = 0; i < objs.length; i++) {
							queryList.setParameter(i, objs[i]);
						}
						return queryList
								.setFirstResult((pageNum - 1) * pageSize)
								.setMaxResults(pageSize).list();

					}
				});
	}
	@Deprecated
	@Override
	public int getBeanCount(final List parameters, final String countSql) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {

					@Override
					public Integer doInHibernate(Session session)
							throws HibernateException {
						Query countQuery = session.createQuery(countSql);
						Object[] objs = parameters.toArray();
						for (int i = 0; i < objs.length; i++) {
							countQuery.setParameter(i, objs[i]);
						}
						Long count = (Long) countQuery.uniqueResult();
						return count.intValue();
					}
				});
	}
}
