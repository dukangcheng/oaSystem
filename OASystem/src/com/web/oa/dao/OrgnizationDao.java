package com.web.oa.dao;

import java.util.List;

import com.web.oa.base.BaseDao;
import com.web.oa.model.Orgnization;
/**
 * 部门机构Dao接口
 * @author dukangcheng
 *
 */
public interface OrgnizationDao extends BaseDao<Orgnization>{
    /**
     * 根据上级部门获取所有的子部门
     * @param parentId
     * @return
     */
	List<Orgnization> getChildrenByParentId(int parentId);

}
