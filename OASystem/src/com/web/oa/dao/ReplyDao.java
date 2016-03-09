package com.web.oa.dao;

import java.util.List;

import com.web.oa.base.BaseDao;
import com.web.oa.model.Reply;
import com.web.oa.model.Topic;
/**
 *�ظ�Dao�ӿ�
 * @author dukangcheng
 *
 */
public interface ReplyDao extends BaseDao<Reply>{
    /**
     * ��ȡ��ǰ�����µĻظ�
     * @param topic
     * @return
     */
	public List<Reply> getByTopic(Topic topic);
    /**
     * ���շ�ҳ�ķ�ʽ��ȡ���ݵ�ǰ���ӻ�ȡ�ظ�
     * @param pageNum
     * @param pageSize
     * @param topic
     * @return
     */
	public List getPageList(int pageNum, int pageSize, Topic topic);
    /**
     * ��ȡ�ظ�������
     * @param topic
     * @return
     */
	public int getCount(Topic topic);
   
}
