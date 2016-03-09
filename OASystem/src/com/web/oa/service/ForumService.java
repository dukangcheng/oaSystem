package com.web.oa.service;

import java.util.List;

import com.web.oa.model.Forum;
import com.web.oa.model.Topic;
/**
 * 版块Service
 * @author dukangcheng
 *
 */
public interface ForumService {
    /**
     * 获取所有的版块
     * @return
     */
	public List<Forum> findAll();
    /**
     * 删除版块
     * @param id
     */
	public void delete(int id);
    /**
     * 添加版块
     * @param model
     */
	public void add(Forum model);
    /**
     * 根据ID获取版块
     * @param id
     * @return
     */
	public Forum findById(int id);
    /**
     * 更新板块
     * @param forum
     */
	public void update(Forum forum);
    /**
     * 上移版块
     * @param id
     */
	public void moveUp(int id);
    /**
     * 下一板块
     * @param id
     */
	public void moveDown(int id);
    /**
     * 根据当前板块获取板块内所有帖子
     * @param forum
     * @return
     */
	public List<Topic> findByForum(Forum forum);

   
}
