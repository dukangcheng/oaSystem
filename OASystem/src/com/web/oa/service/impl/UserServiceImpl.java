package com.web.oa.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.web.oa.base.BaseService;
import com.web.oa.cfg.Configuration;
import com.web.oa.dao.UserDao;
import com.web.oa.model.PageBean;
import com.web.oa.model.User;
import com.web.oa.service.UserService;
import com.web.oa.utils.HqlHelper;
/**
 * 用户Service的实现
 * @author dukangcheng
 *
 */
public class UserServiceImpl extends BaseService implements UserService {
	@Override
	public List<User> findAll() {
		return userDao.getAll();
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public void addUser(User model) {
		   userDao.save(model);		
	}

	@Override
	public User findById(int id) {
		return userDao.getById(id);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public User findByLoginNameAndPassword(String loginName, String password) {
		return userDao.getByLoginNameAndPassword(loginName,password);
	}

	@Override
	@Deprecated
	public PageBean findPageBean(int pageNum, String hqlList, String hqlCount,
			List<Object> parameters) {
		int pageSize=Configuration.pageSize;
		List list=userDao.getPageList(pageSize, parameters, pageNum, hqlList);
		int count=userDao.getBeanCount(parameters, hqlCount);
		return new PageBean(list, pageNum, pageSize, count);
	}

	@Override
	public PageBean findPageBean(int pageNum, HqlHelper hh) {
		int pageSize=Configuration.pageSize;
		List list=userDao.getPageList(pageSize, hh.getParameters(), pageNum, hh.getHqlList());
		int count=userDao.getBeanCount(hh.getParameters(), hh.getHqlCount());
		return new PageBean(list, pageNum, pageSize, count);
	}
}
