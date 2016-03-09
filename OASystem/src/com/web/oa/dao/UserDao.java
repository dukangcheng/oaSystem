package com.web.oa.dao;

import com.web.oa.base.BaseDao;
import com.web.oa.model.User;
/**
 * 用户Dao接口
 * @author dukangcheng
 *
 */
public interface UserDao extends BaseDao<User> {
    /**
     * 根据用户名和密码获取当前用户
     * @param loginName
     * @param password
     * @return
     */
	User getByLoginNameAndPassword(String loginName, String password);

}
