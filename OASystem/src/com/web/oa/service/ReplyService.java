package com.web.oa.service;

import java.util.List;

import com.web.oa.model.PageBean;
import com.web.oa.model.Reply;
import com.web.oa.model.Topic;
import com.web.oa.utils.HqlHelper;
/**
 * 回复Service接口
 * @author dukangcheng
 *
 */
public interface ReplyService {
	/**
	 * 添加回复
	 * @param reply
	 */
	public void add(Reply reply);
    /**
     * 获取所有的回复
     * @return
     */
	public List<Reply> findAll();
    /**
     * 根据帖子获取回复
     * @param topic
     * @return
     */
	public List<Reply> findByTopic(Topic topic);
	@Deprecated  //版本一
	public PageBean findPageBean(int pageNum, Topic topic);
    @Deprecated //版本2
	public PageBean findPageBean(int pageNum, List<Object> parameters,String hql,String countSql);
    /**
     * 使用分页获取所有的回复信息
     * @param pageNum
     * @param hh
     * @return
     */
	public PageBean findPageBean(int pageNum, HqlHelper hh);
}
