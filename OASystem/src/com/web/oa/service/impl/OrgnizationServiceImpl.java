package com.web.oa.service.impl;

import java.util.List;

import com.web.oa.base.BaseService;
import com.web.oa.dao.OrgnizationDao;
import com.web.oa.model.Orgnization;
import com.web.oa.service.OrgnizationService;
/**
 * 部门Service的实现
 * @author dukangcheng
 *
 */
public class OrgnizationServiceImpl extends BaseService implements OrgnizationService {

	@Override
	public List<Orgnization> findAll() {
		return orgnizationDao.getAll();
	}

	@Override
	public void delete(int id) {
	   orgnizationDao.delete(id);	
	}

	@Override
	public void addOrg(Orgnization model) {
		orgnizationDao.save(model);
	}

	@Override
	public Orgnization findById(int id) {
		return orgnizationDao.getById(id);
	}

	@Override
	public void update(Orgnization orgnization) {
		orgnizationDao.update(orgnization);
	}

	@Override
	public List<Orgnization> findTopList() {
		int parentId=0;
		return orgnizationDao.getChildrenByParentId(parentId);
	}

	@Override
	public List<Orgnization> findChildren(int parentId) {
		return orgnizationDao.getChildrenByParentId(parentId);
	}

}
