package com.web.oa.dao;

import java.util.List;

import com.web.oa.base.BaseDao;
import com.web.oa.model.Reply;
import com.web.oa.model.Topic;
/**
 *回复Dao接口
 * @author dukangcheng
 *
 */
public interface ReplyDao extends BaseDao<Reply>{
    /**
     * 获取当前主题下的回复
     * @param topic
     * @return
     */
	public List<Reply> getByTopic(Topic topic);
    /**
     * 按照分页的方式获取根据当前帖子获取回复
     * @param pageNum
     * @param pageSize
     * @param topic
     * @return
     */
	public List getPageList(int pageNum, int pageSize, Topic topic);
    /**
     * 获取回复的数量
     * @param topic
     * @return
     */
	public int getCount(Topic topic);
   
}
