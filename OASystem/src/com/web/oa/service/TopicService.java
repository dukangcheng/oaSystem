package com.web.oa.service;

import java.util.List;

import com.web.oa.model.Forum;
import com.web.oa.model.PageBean;
import com.web.oa.model.Topic;
import com.web.oa.utils.HqlHelper;
/**
 * 帖子Service接口
 * @author dukangcheng
 *
 */
public interface TopicService {
    /**
     * 获取所有的帖子
     * @return
     */
	public List<Topic> findAll();
	/**
	 * 发帖子
	 * @param topic
	 */
	public void add(Topic topic);
	/**
	 * 根据ID获取当前帖子
	 * @param id
	 * @return
	 */
	public Topic findById(int id);
	/**
	 * 更新帖子
	 * @param topic
	 */
	public void update(Topic topic);
	
	@Deprecated   //版本1   已过期
	public PageBean findPageBean(int pageNum, Forum forum);
	@Deprecated    //版本2 已过期
	public PageBean findPageBean(int pageNum, Forum forum, String hql);
	@Deprecated    //版本3 已过期  
	public PageBean findPageBean(int pageNum, List parameters, String hql,
			String countSql);
	/**
	 * 使用分页的方式获取帖子
	 * @param pageNum
	 * @param hh
	 * @return
	 */
	public PageBean findPageBean(int pageNum, HqlHelper hh);
}
