package com.web.oa.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.web.oa.base.BaseDaoImpl;
import com.web.oa.dao.OrgnizationDao;
import com.web.oa.model.Orgnization;
/**
 * 部门Dao的实现类
 * @author dukangcheng
 *
 */
public class OrgnizationDaoImpl extends BaseDaoImpl<Orgnization> implements OrgnizationDao{

	@Override
	public List<Orgnization> getChildrenByParentId(final int parentId) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Orgnization>>() {

			@Override
			public List<Orgnization> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub	
                if(parentId==0){
                	return session.createQuery("from Orgnization o where o.parent is null").list();
                }
                else{
                	return session.createQuery("from Orgnization o where o.parent.id = ? ").setParameter(0, parentId).list();
                }
                
			}
		});
	}

}
