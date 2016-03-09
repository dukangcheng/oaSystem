package com.web.oa.ui.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.model.Reply;
import com.web.oa.model.Topic;
/**
 * 回贴控制器
 * @author dukangcheng
 *
 */
public class ReplyAction extends BaseAction<Reply> {
    private int topicId;
	/**
	 * 回帖页面
	 * @return
	 */
	public String addUI(){
		Topic topic=topicService.findById(topicId);
		ActionContext.getContext().put("topic", topic);
		
		return "addUI";
	
	}
	/**
	 * 回帖
	 * @return
	 */
	public String add(){
		//设置回复的帖子
		model.setTopic(topicService.findById(topicId));
	     //title和content  faceIcon  已经被封装，不用重新设置
		//model.setTitle(title);
		//model.setContent(content);
		//model.setFaceIcon(faceIcon);
		model.setPostTime(new Date());
		model.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());  //获取ip地址
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
