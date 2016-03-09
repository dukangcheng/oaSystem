package com.web.oa.dao;

import java.util.List;

import com.web.oa.base.BaseDao;
import com.web.oa.model.Privilege;
/**
 * 权限Dao的接口
 * @author dukangcheng
 *
 */
public interface PrivilegeDao extends BaseDao<Privilege> {
    /**
     * 获取顶级权限列表
     * @return
     */
    public List<Privilege> getTopList();
    /**
     * 获取所有的权限列表
     * @return
     */
	public List<String> getAllPrivileges();

    
}
