package com.web.oa.service.impl;

import java.util.List;

import com.web.oa.base.BaseService;
import com.web.oa.cfg.Configuration;
import com.web.oa.dao.ForumDao;
import com.web.oa.dao.ReplyDao;
import com.web.oa.dao.TopicDao;
import com.web.oa.model.Forum;
import com.web.oa.model.PageBean;
import com.web.oa.model.Reply;
import com.web.oa.model.Topic;
import com.web.oa.service.ReplyService;
import com.web.oa.utils.HqlHelper;
/**
 * �ظ�Service��ʵ��
 * @author dukangcheng
 *
 */
public class ReplyServiceImpl extends BaseService implements ReplyService {
	@Override
	public void add(Reply reply) {
		replyDao.save(reply);
		//ά��topic�е������Ϣ   topic�е�����ֶ�
		Topic topic=reply.getTopic();
		topic.setLastUpdateTime(reply.getPostTime());  //�����������µĸ��µ�ʱ��
		topic.setReplyCount(topic.getReplyCount()+1);  //�������ӻظ�����
		topic.setLastReply(reply);                     //�������µĻظ�
         Forum forum=topic.getForum();    
         //�õ����Ӷ�Ӧ�İ��
         forum.setArticleCount(forum.getArticleCount()+1);  //������������
         //���µ����ݿ���    
        forumDao.update(forum);
        topicDao.update(topic);
	}

	@Override
	public List<Reply> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reply> findByTopic(Topic topic) {
		// TODO Auto-generated method stub
		System.out.println("**************");
		return replyDao.getByTopic(topic);
	}
	@Override
	public PageBean findPageBean(int pageNum, Topic topic) {
        int pageSize=Configuration.pageSize;
        List list=replyDao.getPageList(pageNum,pageSize,topic);
        int count=replyDao.getCount(topic);
		return new PageBean(list, pageNum, pageSize, count);
	}
	@Override
	@Deprecated
	public PageBean findPageBean(int pageNum, List<Object> parameters,String hql,String countSql) {
		// TODO Auto-generated method stub
		int pageSize=Configuration.pageSize;
		List list=replyDao.getPageList(pageSize, parameters, pageNum, hql);
		int pageCount=replyDao.getBeanCount(parameters, countSql);
		return new PageBean(list, pageNum, pageSize, pageCount);
	}
	@Override
	public PageBean findPageBean(int pageNum, HqlHelper hh) {
		// TODO Auto-generated method stub
		int pageSize=Configuration.pageSize;
		List list=replyDao.getPageList(pageSize,hh.getParameters(), pageNum, hh.getHqlList());
		int pageCount=replyDao.getBeanCount(hh.getParameters(), hh.getHqlCount());
		return new PageBean(list, pageNum, pageSize, pageCount);
	}

}
