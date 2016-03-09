package com.web.oa.service.impl;

import java.util.List;

import com.web.oa.base.BaseService;
import com.web.oa.cfg.Configuration;
import com.web.oa.dao.ForumDao;
import com.web.oa.dao.TopicDao;
import com.web.oa.model.Forum;
import com.web.oa.model.PageBean;
import com.web.oa.model.Topic;
import com.web.oa.service.TopicService;
import com.web.oa.utils.HqlHelper;
/**
 * 帖子Service的实现
 * @author dukangcheng
 *
 */
public class TopicServiceImpl extends BaseService implements TopicService {
	@Override
	public List<Topic> findAll() {
		return  topicDao.getAll();
	}

	@Override
	public void add(Topic topic) {
		//设置属性并保存
	   topic.setReplyCount(0);
	   //topic.setLastReply(null);
       topic.setLastUpdateTime(topic.getPostTime());   //默认为主题发布的时间
       topic.setType(Topic.TYPE_NORMAL);
       //保存到数据库
       topicDao.save(topic);
       
       //维护相关信息
       Forum forum=topic.getForum();
       forum.setArticleCount(forum.getArticleCount()+1);
       forum.setTopicCount(forum.getTopicCount()+1);
       forum.setLastTopic(topic);
       forumDao.update(forum);  //更新
	}
	
	
	
	@Override
	public void update(Topic topic) {
		topicDao.update(topic);
	}
	@Deprecated
	@Override   //版本一
	public PageBean findPageBean(int pageNum, Forum forum) {
		int pageSize=Configuration.pageSize;
		//查询本页数据列表
		List list=topicDao.getPageList(pageSize,forum,pageNum);
		//查询总记录数
		int count=topicDao.getBeanCount(forum);
		return  new PageBean(list, pageNum, pageSize, count);
	}
	@Deprecated
	@Override   //版本2
	public PageBean findPageBean(int pageNum, Forum forum, String hql) {
		int pageSize=Configuration.pageSize;
		List list=topicDao.getPageList(pageSize, forum, pageNum,hql);
		int count=topicDao.getBeanCount(forum);
		return new PageBean(list, pageNum, pageSize, count);
	}
	
	@Deprecated
	@Override   //版本3
	public PageBean findPageBean(int pageNum, List parameters, String hql,
			String countSql) {
        int pageSize=Configuration.pageSize;
        List list=topicDao.getPageList(pageSize, parameters, pageNum, hql);
        int count=topicDao.getBeanCount(parameters,countSql);
		return new PageBean(list, pageNum, pageSize, count);
	}
	//最终版
	@Override
	public PageBean findPageBean(int pageNum, HqlHelper hh) {
		int pageSize=Configuration.pageSize;
        List list=topicDao.getPageList(pageSize, hh.getParameters(), pageNum,hh.getHqlList());
        int count=topicDao.getBeanCount(hh.getParameters(),hh.getHqlCount());
		return new PageBean(list, pageNum, pageSize, count);
	}
	@Override
	public Topic findById(int id) {
		return topicDao.getById(id);
	}
    //-------

	public ForumDao getForumDao() {
		return forumDao;
	}

	public void setForumDao(ForumDao forumDao) {
		this.forumDao = forumDao;
	}




	
}
