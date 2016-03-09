package com.web.oa.service;

import java.util.List;

import com.web.oa.model.Role;

public interface RoleService {

	public List<Role> findAll();

	public void delete(int id);

	public void addRole(Role role);

	public Role findById(int id);

	public void update(Role role);

	public List<Role> findByIds(int[] roleIds);

}
