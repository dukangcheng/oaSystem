package com.web.oa.base;

import java.util.List;
/**
 * 将数据访问层抽象出此接口作为整个
 * 数据层的基础接口，里面提供了整个数据访问层
 * 最通用的几种方法 接口中表示引入类型的泛型，可以是任何
 * 项目中的model类
 * @author dukangcheng
 *
 * @param <T>
 */
public interface BaseDao<T> {
	
	/**
	 * 对持久层数据的存储操作的抽象
	 */
	public void save(T entities);
	
	/**
     * 对持久层数据的修改操作的抽象
     */
	public void update(T entites);
	
	/**
     * 对持久化对象的删除操作的抽象（根据ID）
     */
	public void delete(int id);
	/**
     * 对持久化对象的查询（根据Id）的抽象
     */
	public T getById(Integer id);
	/**
     * 对持久化对象的批量查询（根据ID）的抽象
     */
	public List<T> getByIds(int[] ids);
	/**
     * 对所有持久化对象进行查询的抽象
     */
	public List<T> getAll();

	/**
     * 采取分页的方式对持久化对象查询的抽象
     */
	public List getPageList(int pageSize, List parameters, int pageNum,String hql);
	/**
     * 指定条件下的持久化对象的计数器方法的抽象
     */
	public int getBeanCount(List parameters, String countSql);
}
