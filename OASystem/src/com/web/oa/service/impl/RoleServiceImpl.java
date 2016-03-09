package com.web.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.web.oa.base.BaseService;
import com.web.oa.dao.RoleDao;
import com.web.oa.model.Role;
import com.web.oa.service.RoleService;
/**
 * 角色Service的实现
 * @author dukangcheng
 *
 */
public class RoleServiceImpl  extends BaseService implements RoleService{
	@Override
	public List<Role> findAll() {
		return roleDao.getAll();
	}

	@Override
	public void delete(int id) {
		roleDao.delete(id);
	}

	@Override
	public void addRole(Role role) {
		roleDao.save(role);
	}

	@Override
	public Role findById(int id) {
		return roleDao.getById(id);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public List<Role> findByIds(int[] roleIds) {
		return roleDao.getByIds(roleIds);
	}
    
    
}
