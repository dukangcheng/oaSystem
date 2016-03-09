package com.web.oa.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.web.oa.base.BaseDaoImpl;
import com.web.oa.dao.ForumDao;
import com.web.oa.model.Forum;
import com.web.oa.model.Topic;
/**
 * 版块Dao接口的实现
 * @author dukangcheng
 *
 */
public class ForumDaoImpl extends BaseDaoImpl<Forum> implements ForumDao {
	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return (List) this.getHibernateTemplate().execute(new HibernateCallback<List>() {

			@Override
			public List doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				return session.createQuery("from Forum f order by f.postion").list();
			}
		});
	}
	@Override
	public void save(Forum forum) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(forum);
		//指定postiion的值为最大
	    forum.setPostion(forum.getId());
	    //持久化状态不需要调用update方法
	    
	}
	@Override
	public Forum getUpByPostion(final int postion) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().execute(new HibernateCallback<Forum>() {

			@Override
			public Forum doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				return (Forum) session.createQuery("from Forum f where f.postion < ? order by f.postion DESC")
						.setParameter(0, postion)
						.setFirstResult(0)
						.setMaxResults(1)
						.uniqueResult();
			}
		});
	}
	@Override
	public Forum getDownByPostion(final int postion) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().execute(new HibernateCallback<Forum>() {

			@Override
			public Forum doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				return (Forum) session.createQuery("from Forum f where f.postion >? order by f.postion ASC")
						.setParameter(0, postion)
						.setFirstResult(0)
						.setMaxResults(1)
						.uniqueResult();
			}
		});
	}
	@Override
	public List<Topic> findByForum(final Forum forum) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Topic>>() {
			@Override
			public List<Topic> doInHibernate(Session session)
					throws HibernateException {
				// TODO Auto-generated method stub
				return session.createQuery("from Topic t where t.forum =? order by (CASE  t.type WHEN 2 THEN 2 ELSE 0 END)DESC"
						+ ", t.lastUpdateTime DESC")
						.setParameter(0, forum)
						.list();
			}
		});
	}
}
