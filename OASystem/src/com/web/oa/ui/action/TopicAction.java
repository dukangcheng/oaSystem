package com.web.oa.ui.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.model.Forum;
import com.web.oa.model.PageBean;
import com.web.oa.model.Reply;
import com.web.oa.model.Topic;
import com.web.oa.utils.HqlHelper;
/**
 * ���ӿ�����
 * @author dukangcheng
 *
 */
public class TopicAction extends BaseAction<Topic> {
    private int forumId;
	/**
	 *  �����ͻظ��б�
	 * @return
	 */
	public String show(){
       //׼������
		Topic topic=topicService.findById(model.getId());
		ActionContext.getContext().put("topic", topic);
		System.out.println(topic.getTitle()+"****************");
	    //׼������   ԭʼ��
	    //System.out.println(replyService.findByTopic(topic).size()+"*****");
        /*List<Reply> replyList=replyService.findByTopic(topic);
		ActionContext.getContext().put("replyList", replyList);*/
		
		/*//׼���б�ķ�ҳ��Ϣ   //�汾1
		PageBean pageBean=replyService.findPageBean(pageNum,topic);
		ActionContext.getContext().getValueStack().push(pageBean);*/
		//�汾 2
		/*String hql="from Reply r where r.topic=? order by r.postTime asc";
		String countSql="select count(*) from Reply r where r.topic=?";
		List<Object> parameters=new ArrayList<Object>();
		parameters.add(topic);
		PageBean pageBean=replyService.findPageBean(pageNum, parameters,hql,countSql);
		ActionContext.getContext().getValueStack().push(pageBean);*/
		
		//���հ�
		HqlHelper hh=new HqlHelper(Reply.class, "r");
		hh.addWhereClause("topic=? ",topic)
		.addOrderByClause(true, false,"r.postTime");
		
		PageBean pageBean=replyService.findPageBean(pageNum, hh);
		ActionContext.getContext().getValueStack().push(pageBean);
    	return "show";
    }
    
	/**
	 *  addUIҳ�� ��������ҳ��
	 * @return
	 */
	public String addUI() {
		Forum forum=forumService.findById(forumId);
		ActionContext.getContext().put("forum", forum);
		
		return "addUI";
	}

	/**
	 *  ��������
	 * @return
	 */
	public String add() {
		//modelz���Ѿ���װ��title��content��faceIcon
		model.setForum(forumService.findById(forumId));
		//��ǰ����ֱ�ӻ�ȡ����Ϣ
		model.setAuthor(getUser());
		model.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		
		//topicService.add(model);
		
		//��Ҫ��ҵ���н�����ӵ�����
		topicService.add(model);
		return "toShow";
	}
	/**
	 * ������������  ������  �ö���  ��ͨ��
	 * @return
	 */
	public String setType(){
	    Topic topic=topicService.findById(model.getId());
	    topic.setType(model.getType());
	    topicService.update(topic);
	    //׼������   ��ȡ���
	    Forum forum=forumService.findById(forumId);
	    ActionContext.getContext().put("forum", forum);
	    //׼������   ��ȡ�����б� 
	  	List<Topic> topicList=forumService.findByForum(forum);
	    ActionContext.getContext().put("topicList", topicList);
		return "toShow";
	}
	
	
	// -----
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public int getForumId() {
		return forumId;
	}
}
