package com.web.oa.service;

import java.util.List;

import com.web.oa.model.Orgnization;
/**
 * 部门Service抽象接口
 * @author dukangcheng
 *
 */
public interface OrgnizationService {
    /**
     * 获取所有的部门
     * @return
     */
	public List<Orgnization> findAll();
    /**
     * 删除部门
     * @param id
     */
	public void delete(int id);
    /**
     * 添加部门
     * @param model
     */
	public void addOrg(Orgnization model);
    /**
     * 获取部门（根据ID）
     * @param id
     * @return
     */
	public Orgnization findById(int id);
    /**
     * 修改部门信息
     * @param orgnization
     */
	public void update(Orgnization orgnization);
    /**
     * 获取顶级部门
     * @return
     */
	public List<Orgnization> findTopList();
    /**
     * 获取上级部门下的所有子部门
     * @param parentId
     * @return
     */
	public List<Orgnization> findChildren(int parentId);


}
