package com.web.oa.service;

import java.util.List;

import com.web.oa.model.Privilege;
/**
 * Ȩ��Service�ӿ�
 * @author dukangcheng
 *
 */
public interface PrivilegeService {
    /**
     * ��ȡ����Ȩ��
     * @return
     */
	public List<Privilege> findTopList();
    /**
     * ������ȡȨ�ޣ�����ID��
     * @param privilegeIds
     * @return
     */
	public List<Privilege> findByIds(int[] privilegeIds);
    /**
     * ��ȡ���е�Ȩ��
     * @return
     */
	public List<String> findAllPrivilege();
    
}
