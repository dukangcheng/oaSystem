package com.web.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
/**
 * 抽象接口的实现类
 * @author dukangcheng
 *
 * @param <T>
 */
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements
		BaseDao<T> {
	protected Class<T> clazz;
	 /**
     * 每次创建Dao对象会先从本类无参构造器中继承获取泛型类型。
     */
	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
		
		// System.out.println(clazz.getSimpleName());
	}
    
	@Override
	public void save(T entities) {
		this.getHibernateTemplate().save(entities);
	}
    
	@Override
	public void update(T entites) {
		this.getHibernateTemplate().update(entites);
	}
    
	@Override
	public void delete(int id) {
		Object obj = this.getHibernateTemplate().get(clazz, id);
		if (obj != null) {
			this.getHibernateTemplate().delete(obj);
		}
	}
   
	@Override
	public T getById(Integer id) {
		if (id == 0 || id == null) {
			return null;
		}
		return this.getHibernateTemplate().get(clazz, id);
	}
    
	@Override
	public List getByIds(final int[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}
		int n = ids.length;
		final Integer[] idss = new Integer[n];
		for (int i = 0; i < n; i++) {
			idss[i] = new Integer(ids[i]);
		}
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback<List>() {
					@Override
					public List doInHibernate(Session session)
							throws HibernateException {
						// TODO Auto-generated method stub
						return session
								.createQuery(
										"from " + clazz.getSimpleName()
												+ "  a where a.id in (:ids)")
								.setParameterList("ids", idss).list();
					}
				});
	}
 
	@Override
	public List getAll() {
		return this.getHibernateTemplate()
				.find("from " + clazz.getSimpleName());
	}
    
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
						if (parameters.size() != 0 && parameters != null) {
							for (int i = 0; i < objs.length; i++) {
								queryList.setParameter(i, objs[i]);
							}
						}
						return queryList
								.setFirstResult((pageNum - 1) * pageSize)
								.setMaxResults(pageSize).list();

					}
				});
	}
    
	@Override
	public int getBeanCount(final List parameters, final String countSql) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {

					@Override
					public Integer doInHibernate(Session session)
							throws HibernateException {
						Query countQuery = session.createQuery(countSql);
						Object[] objs = parameters.toArray();
						if (parameters.size() != 0 && parameters != null) {
							for (int i = 0; i < objs.length; i++) {
								countQuery.setParameter(i, objs[i]);
							}
						}
						Long count = (Long) countQuery.uniqueResult();
						return count.intValue();
					}
				});
	}
}
