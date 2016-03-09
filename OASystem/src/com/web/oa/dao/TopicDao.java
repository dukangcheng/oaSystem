package com.web.oa.dao;

import java.util.List;

import com.web.oa.base.BaseDao;
import com.web.oa.model.Forum;
import com.web.oa.model.PageBean;
import com.web.oa.model.Topic;
/**
 * 主题Dao接口
 * @author dukangcheng
 *
 */
public interface TopicDao extends BaseDao<Topic>{
    /**
     * 按照分页的方式获取帖子（已过期）
     * @param pageSize
     * @param forum
     * @param pageNum
     * @return
     */
	@Deprecated
	public List getPageList(int pageSize, Forum forum, int pageNum);
	/**
	 * 获取版块下的帖子数量（已过期）
	 * @param forum
	 * @return
	 */
    @Deprecated
	public int getBeanCount(Forum forum);
    /**
     * 根据分页获取版块的帖子（已过期）
     * @param pageSize
     * @param forum
     * @param pageNum
     * @param hql
     * @return
     */
    @Deprecated
	public List getPageList(int pageSize, Forum forum, int pageNum, String hql);
    /**
     * 按照分页获取版块的帖子（已过期）
     * @param forum
     * @param countSql
     * @return
     */
    @Deprecated
	public int getBeanCount(Forum forum, String countSql);
   /* @Deprecated
	public List getPageList(int pageSize, List parameters, int pageNum,
			String hql);
     @Deprecated
	public int getBeanCount(List parameters, String countSql);
*/



}
