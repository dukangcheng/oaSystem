package com.web.oa.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.web.oa.base.BaseDaoImpl;
import com.web.oa.dao.ReplyDao;
import com.web.oa.model.Reply;
import com.web.oa.model.Topic;
/**
 * 回复Dao的实现类
 * @author dukangcheng
 *
 */
public class ReplyDaoImpl extends BaseDaoImpl<Reply> implements ReplyDao{

	@Override
	public List<Reply> getByTopic(final Topic topic) {
		// TODO Auto-generated method stub
		System.out.println("**************");
		return (List<Reply>) this.getHibernateTemplate().execute(new HibernateCallback<List<Reply>>() {

			@Override
			public List<Reply> doInHibernate(Session session)
					throws HibernateException {
 
				return session.createQuery("FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")
						.setParameter(0, topic)
						.list();
			}
		});
	}

	@Override
	public List getPageList(final int pageNum,final  int pageSize,final Topic topic) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Topic>>() {

			@Override
			public List<Topic> doInHibernate(Session session)
					throws HibernateException {
				return session.createQuery("from Reply r where r.topic =? order by r.postTime asc")
						.setParameter(0, topic)
						.setFirstResult((pageNum-1)*pageSize)
						.setMaxResults(pageSize)
						.list();
			}
		});
	}

	@Override
	public int getCount(final Topic topic) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException {
				// TODO Auto-generated method stub
				Long count=(Long) session.createQuery("select count(*) from Reply r where r.topic=?")
						.setParameter(0, topic).uniqueResult();
				return count.intValue();
			}
		});
	}

}
