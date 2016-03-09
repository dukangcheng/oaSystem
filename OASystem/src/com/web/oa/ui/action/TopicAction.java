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
 * 帖子控制器
 * @author dukangcheng
 *
 */
public class TopicAction extends BaseAction<Topic> {
    private int forumId;
	/**
	 *  主贴和回复列表
	 * @return
	 */
	public String show(){
       //准备数据
		Topic topic=topicService.findById(model.getId());
		ActionContext.getContext().put("topic", topic);
		System.out.println(topic.getTitle()+"****************");
	    //准备数据   原始版
	    //System.out.println(replyService.findByTopic(topic).size()+"*****");
        /*List<Reply> replyList=replyService.findByTopic(topic);
		ActionContext.getContext().put("replyList", replyList);*/
		
		/*//准备列表的分页信息   //版本1
		PageBean pageBean=replyService.findPageBean(pageNum,topic);
		ActionContext.getContext().getValueStack().push(pageBean);*/
		//版本 2
		/*String hql="from Reply r where r.topic=? order by r.postTime asc";
		String countSql="select count(*) from Reply r where r.topic=?";
		List<Object> parameters=new ArrayList<Object>();
		parameters.add(topic);
		PageBean pageBean=replyService.findPageBean(pageNum, parameters,hql,countSql);
		ActionContext.getContext().getValueStack().push(pageBean);*/
		
		//最终版
		HqlHelper hh=new HqlHelper(Reply.class, "r");
		hh.addWhereClause("topic=? ",topic)
		.addOrderByClause(true, false,"r.postTime");
		
		PageBean pageBean=replyService.findPageBean(pageNum, hh);
		ActionContext.getContext().getValueStack().push(pageBean);
    	return "show";
    }
    
	/**
	 *  addUI页面 发表帖子页面
	 * @return
	 */
	public String addUI() {
		Forum forum=forumService.findById(forumId);
		ActionContext.getContext().put("forum", forum);
		
		return "addUI";
	}

	/**
	 *  发表帖子
	 * @return
	 */
	public String add() {
		//modelz中已经封装了title，content，faceIcon
		model.setForum(forumService.findById(forumId));
		//当前可以直接获取的信息
		model.setAuthor(getUser());
		model.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		
		//topicService.add(model);
		
		//需要在业务中进行添加的数据
		topicService.add(model);
		return "toShow";
	}
	/**
	 * 设置帖子类型  精华帖  置顶帖  普通贴
	 * @return
	 */
	public String setType(){
	    Topic topic=topicService.findById(model.getId());
	    topic.setType(model.getType());
	    topicService.update(topic);
	    //准备数据   获取版块
	    Forum forum=forumService.findById(forumId);
	    ActionContext.getContext().put("forum", forum);
	    //准备数据   获取帖子列表 
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
