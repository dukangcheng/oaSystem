package com.web.oa.dao;

import java.util.List;

import com.web.oa.base.BaseDao;
import com.web.oa.model.Forum;
import com.web.oa.model.PageBean;
import com.web.oa.model.Topic;
/**
 * ����Dao�ӿ�
 * @author dukangcheng
 *
 */
public interface TopicDao extends BaseDao<Topic>{
    /**
     * ���շ�ҳ�ķ�ʽ��ȡ���ӣ��ѹ��ڣ�
     * @param pageSize
     * @param forum
     * @param pageNum
     * @return
     */
	@Deprecated
	public List getPageList(int pageSize, Forum forum, int pageNum);
	/**
	 * ��ȡ����µ������������ѹ��ڣ�
	 * @param forum
	 * @return
	 */
    @Deprecated
	public int getBeanCount(Forum forum);
    /**
     * ���ݷ�ҳ��ȡ�������ӣ��ѹ��ڣ�
     * @param pageSize
     * @param forum
     * @param pageNum
     * @param hql
     * @return
     */
    @Deprecated
	public List getPageList(int pageSize, Forum forum, int pageNum, String hql);
    /**
     * ���շ�ҳ��ȡ�������ӣ��ѹ��ڣ�
     * @param forum
     * @param countSql
     * @return
     */
    @Deprecated
	public int getBeanCount(Forum forum, String countSql);
   /* @Deprecated
	public List getPageList(int pageSize, List parameters, int pageNum,
			String hql);
     @Deprecated
	public int getBeanCount(List parameters, String countSql);
*/



}
