package com.web.oa.ui.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.model.Reply;
import com.web.oa.model.Topic;
/**
 * ����������
 * @author dukangcheng
 *
 */
public class ReplyAction extends BaseAction<Reply> {
    private int topicId;
	/**
	 * ����ҳ��
	 * @return
	 */
	public String addUI(){
		Topic topic=topicService.findById(topicId);
		ActionContext.getContext().put("topic", topic);
		
		return "addUI";
	
	}
	/**
	 * ����
	 * @return
	 */
	public String add(){
		//���ûظ�������
		model.setTopic(topicService.findById(topicId));
	     //title��content  faceIcon  �Ѿ�����װ��������������
		//model.setTitle(title);
		//model.setContent(content);
		//model.setFaceIcon(faceIcon);
		model.setPostTime(new Date());
		model.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());  //��ȡip��ַ
		model.setAuthor(getUser());
	    	
		replyService.add(model);
		
		return "toShow";
	}
	

	//----
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	
}
