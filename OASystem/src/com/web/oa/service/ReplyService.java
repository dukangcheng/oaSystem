package com.web.oa.service;

import java.util.List;

import com.web.oa.model.PageBean;
import com.web.oa.model.Reply;
import com.web.oa.model.Topic;
import com.web.oa.utils.HqlHelper;
/**
 * �ظ�Service�ӿ�
 * @author dukangcheng
 *
 */
public interface ReplyService {
	/**
	 * ��ӻظ�
	 * @param reply
	 */
	public void add(Reply reply);
    /**
     * ��ȡ���еĻظ�
     * @return
     */
	public List<Reply> findAll();
    /**
     * �������ӻ�ȡ�ظ�
     * @param topic
     * @return
     */
	public List<Reply> findByTopic(Topic topic);
	@Deprecated  //�汾һ
	public PageBean findPageBean(int pageNum, Topic topic);
    @Deprecated //�汾2
	public PageBean findPageBean(int pageNum, List<Object> parameters,String hql,String countSql);
    /**
     * ʹ�÷�ҳ��ȡ���еĻظ���Ϣ
     * @param pageNum
     * @param hh
     * @return
     */
	public PageBean findPageBean(int pageNum, HqlHelper hh);
}
