package com.web.oa.service.impl;

import java.util.List;

import com.web.oa.base.BaseService;
import com.web.oa.dao.ForumDao;
import com.web.oa.model.Forum;
import com.web.oa.model.Topic;
import com.web.oa.service.ForumService;
/**
 * ���service��ʵ��
 * @author dukangcheng
 *
 */
public class ForumServiceImpl extends BaseService  implements ForumService{ 
	@Override
	public List<Forum> findAll() {
		return forumDao.getAll();
	}

	@Override
	public void delete(int id) {
		forumDao.delete(id);
	}

	@Override
	public void add(Forum model) {
		forumDao.save(model);
	}

	@Override
	public Forum findById(int id) {
		return (Forum) forumDao.getById(id);
	}

	@Override
	public void update(Forum model) {
		Forum forum=forumDao.getById(model.getId());
		forum.setTitle(model.getTitle());
		forum.setDescription(model.getDescription());
		forumDao.update(forum);
	}

	@Override
	public void moveUp(int id) {
        //��ǰ��fourm
		Forum forum=forumDao.getById(id);
		//��һ��forum
		Forum other=forumDao.getUpByPostion(forum.getPostion());
		//����forum��other��postion
		int temp=forum.getPostion();
		forum.setPostion(other.getPostion());
		other.setPostion(temp);
	    //���µ����ݿ���    
		forumDao.update(forum);
		forumDao.update(other);
	}

	@Override
	public void moveDown(int id) {
		//��ǰ��forum
		Forum forum=forumDao.getById(id);
		//��һ��forum
		Forum other=forumDao.getDownByPostion(forum.getPostion());
		
		//����forum��other��postion
		int temp=forum.getPostion();
		forum.setPostion(other.getPostion());
		other.setPostion(temp);
	    //���µ����ݿ���
		forumDao.update(forum);
		forumDao.update(other);
	}
	

	@Override
	public List<Topic> findByForum(Forum forum) {
		return forumDao.findByForum(forum);
	}

}
