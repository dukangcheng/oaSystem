package com.web.oa.dao;

import java.util.List;

import com.web.oa.base.BaseDao;
import com.web.oa.model.Orgnization;
/**
 * ���Ż���Dao�ӿ�
 * @author dukangcheng
 *
 */
public interface OrgnizationDao extends BaseDao<Orgnization>{
    /**
     * �����ϼ����Ż�ȡ���е��Ӳ���
     * @param parentId
     * @return
     */
	List<Orgnization> getChildrenByParentId(int parentId);

}
