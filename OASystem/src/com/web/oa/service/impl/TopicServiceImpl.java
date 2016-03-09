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
 * ����Service��ʵ��
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
		//�������Բ�����
	   topic.setReplyCount(0);
	   //topic.setLastReply(null);
       topic.setLastUpdateTime(topic.getPostTime());   //Ĭ��Ϊ���ⷢ����ʱ��
       topic.setType(Topic.TYPE_NORMAL);
       //���浽���ݿ�
       topicDao.save(topic);
       
       //ά�������Ϣ
       Forum forum=topic.getForum();
       forum.setArticleCount(forum.getArticleCount()+1);
       forum.setTopicCount(forum.getTopicCount()+1);
       forum.setLastTopic(topic);
       forumDao.update(forum);  //����
	}
	
	
	
	@Override
	public void update(Topic topic) {
		topicDao.update(topic);
	}
	@Deprecated
	@Override   //�汾һ
	public PageBean findPageBean(int pageNum, Forum forum) {
		int pageSize=Configuration.pageSize;
		//��ѯ��ҳ�����б�
		List list=topicDao.getPageList(pageSize,forum,pageNum);
		//��ѯ�ܼ�¼��
		int count=topicDao.getBeanCount(forum);
		return  new PageBean(list, pageNum, pageSize, count);
	}
	@Deprecated
	@Override   //�汾2
	public PageBean findPageBean(int pageNum, Forum forum, String hql) {
		int pageSize=Configuration.pageSize;
		List list=topicDao.getPageList(pageSize, forum, pageNum,hql);
		int count=topicDao.getBeanCount(forum);
		return new PageBean(list, pageNum, pageSize, count);
	}
	
	@Deprecated
	@Override   //�汾3
	public PageBean findPageBean(int pageNum, List parameters, String hql,
			String countSql) {
        int pageSize=Configuration.pageSize;
        List list=topicDao.getPageList(pageSize, parameters, pageNum, hql);
        int count=topicDao.getBeanCount(parameters,countSql);
		return new PageBean(list, pageNum, pageSize, count);
	}
	//���հ�
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
