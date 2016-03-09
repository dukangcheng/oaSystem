package com.web.oa.service;

import java.util.List;

import com.web.oa.model.Forum;
import com.web.oa.model.Topic;
/**
 * ���Service
 * @author dukangcheng
 *
 */
public interface ForumService {
    /**
     * ��ȡ���еİ��
     * @return
     */
	public List<Forum> findAll();
    /**
     * ɾ�����
     * @param id
     */
	public void delete(int id);
    /**
     * ��Ӱ��
     * @param model
     */
	public void add(Forum model);
    /**
     * ����ID��ȡ���
     * @param id
     * @return
     */
	public Forum findById(int id);
    /**
     * ���°��
     * @param forum
     */
	public void update(Forum forum);
    /**
     * ���ư��
     * @param id
     */
	public void moveUp(int id);
    /**
     * ��һ���
     * @param id
     */
	public void moveDown(int id);
    /**
     * ���ݵ�ǰ����ȡ�������������
     * @param forum
     * @return
     */
	public List<Topic> findByForum(Forum forum);

   
}
