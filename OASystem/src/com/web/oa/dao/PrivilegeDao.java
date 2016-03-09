package com.web.oa.dao;

import java.util.List;

import com.web.oa.base.BaseDao;
import com.web.oa.model.Privilege;
/**
 * Ȩ��Dao�Ľӿ�
 * @author dukangcheng
 *
 */
public interface PrivilegeDao extends BaseDao<Privilege> {
    /**
     * ��ȡ����Ȩ���б�
     * @return
     */
    public List<Privilege> getTopList();
    /**
     * ��ȡ���е�Ȩ���б�
     * @return
     */
	public List<String> getAllPrivileges();

    
}
