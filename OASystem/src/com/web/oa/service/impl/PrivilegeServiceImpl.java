package com.web.oa.service.impl;

import java.util.List;

import com.web.oa.base.BaseService;
import com.web.oa.dao.PrivilegeDao;
import com.web.oa.model.Privilege;
import com.web.oa.service.PrivilegeService;
/**
 * 权限Service的实现
 * @author dukangcheng
 *
 */
public class PrivilegeServiceImpl extends BaseService implements PrivilegeService {

	@Override
	public List<Privilege> findTopList() {
		return privilegeDao.getTopList();
	}


	@Override
	public List<Privilege> findByIds(int[] privilegeIds) {
		return  privilegeDao.getByIds(privilegeIds);
	}
	
	@Override
	public List<String> findAllPrivilege() {
		return privilegeDao.getAllPrivileges();
	}

}
