package com.web.oa.dao;

import com.web.oa.base.BaseDao;
import com.web.oa.model.User;
/**
 * �û�Dao�ӿ�
 * @author dukangcheng
 *
 */
public interface UserDao extends BaseDao<User> {
    /**
     * �����û����������ȡ��ǰ�û�
     * @param loginName
     * @param password
     * @return
     */
	User getByLoginNameAndPassword(String loginName, String password);

}
