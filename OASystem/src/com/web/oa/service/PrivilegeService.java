package com.web.oa.service;

import java.util.List;

import com.web.oa.model.Privilege;
/**
 * 权限Service接口
 * @author dukangcheng
 *
 */
public interface PrivilegeService {
    /**
     * 获取顶级权限
     * @return
     */
	public List<Privilege> findTopList();
    /**
     * 批量获取权限（根据ID）
     * @param privilegeIds
     * @return
     */
	public List<Privilege> findByIds(int[] privilegeIds);
    /**
     * 获取所有的权限
     * @return
     */
	public List<String> findAllPrivilege();
    
}
