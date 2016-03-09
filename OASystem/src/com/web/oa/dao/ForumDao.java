package com.web.oa.dao;

import java.util.List;

import com.web.oa.base.BaseDao;
import com.web.oa.model.Forum;
import com.web.oa.model.Topic;
/**
 * 论坛版块Dao的抽象接口
 * @author dukangcheng
 *
 */
public interface ForumDao extends BaseDao<Forum>{
    /**
     * 根据当前版块的位置上一个版块位置
     * @param postion
     * @return
     */
	public Forum getUpByPostion(int postion);
    /**
     * 根据当前版块的位置获取下个版块位置
     * @param postion
     * @return
     */
	public Forum getDownByPostion(int postion);
     /**
      * 获取当前版块的所有帖子
      * @param forum
      * @return
      */
	public List<Topic> findByForum(Forum forum);
       
}
