package com.web.oa.service;

import java.util.List;

import com.web.oa.model.Forum;
import com.web.oa.model.PageBean;
import com.web.oa.model.Topic;
import com.web.oa.utils.HqlHelper;
/**
 * ����Service�ӿ�
 * @author dukangcheng
 *
 */
public interface TopicService {
    /**
     * ��ȡ���е�����
     * @return
     */
	public List<Topic> findAll();
	/**
	 * ������
	 * @param topic
	 */
	public void add(Topic topic);
	/**
	 * ����ID��ȡ��ǰ����
	 * @param id
	 * @return
	 */
	public Topic findById(int id);
	/**
	 * ��������
	 * @param topic
	 */
	public void update(Topic topic);
	
	@Deprecated   //�汾1   �ѹ���
	public PageBean findPageBean(int pageNum, Forum forum);
	@Deprecated    //�汾2 �ѹ���
	public PageBean findPageBean(int pageNum, Forum forum, String hql);
	@Deprecated    //�汾3 �ѹ���  
	public PageBean findPageBean(int pageNum, List parameters, String hql,
			String countSql);
	/**
	 * ʹ�÷�ҳ�ķ�ʽ��ȡ����
	 * @param pageNum
	 * @param hh
	 * @return
	 */
	public PageBean findPageBean(int pageNum, HqlHelper hh);
}
