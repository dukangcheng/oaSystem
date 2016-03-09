package com.web.oa.service;

import java.util.List;

import com.web.oa.model.PageBean;
import com.web.oa.model.User;
import com.web.oa.utils.HqlHelper;
/**
 * 用户Service接口
 * @author dukangcheng
 *
 */
public interface UserService {
    /**
     * 获取所有的用户
     * @return
     */
	public List<User> findAll();
    /**
     * 删除用户
     * @param id
     */
	public void delete(int id);
    /**
     * 添加用户
     * @param model
     */
	public void addUser(User model);
    /**
     * 根据ID获取用户
     * @param id
     * @return
     */
	public User findById(int id);
    /**
     * 更新用户
     * @param user
     */
	public void update(User user);
    /**
     * 根据用户名、密码获取用户
     * @param loginName
     * @param password
     * @return
     */
	public User findByLoginNameAndPassword(String loginName, String password);
	//版本1
    @Deprecated
	public PageBean findPageBean(int pageNum, String hqlList, String hqlCount,
			List<Object> parameters);
   /**
    * 版本2
    * 使用分页的方式获取获取用户
    * @param pageNum
    * @param hh
    * @return
    */
	public PageBean findPageBean(int pageNum, HqlHelper hh);
    
}
