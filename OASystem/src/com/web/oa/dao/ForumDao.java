package com.web.oa.dao;

import java.util.List;

import com.web.oa.base.BaseDao;
import com.web.oa.model.Forum;
import com.web.oa.model.Topic;
/**
 * ��̳���Dao�ĳ���ӿ�
 * @author dukangcheng
 *
 */
public interface ForumDao extends BaseDao<Forum>{
    /**
     * ���ݵ�ǰ����λ����һ�����λ��
     * @param postion
     * @return
     */
	public Forum getUpByPostion(int postion);
    /**
     * ���ݵ�ǰ����λ�û�ȡ�¸����λ��
     * @param postion
     * @return
     */
	public Forum getDownByPostion(int postion);
     /**
      * ��ȡ��ǰ������������
      * @param forum
      * @return
      */
	public List<Topic> findByForum(Forum forum);
       
}
