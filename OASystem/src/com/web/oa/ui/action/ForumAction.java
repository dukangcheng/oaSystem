package com.web.oa.ui.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.model.Forum;
import com.web.oa.model.PageBean;
import com.web.oa.model.Topic;
import com.web.oa.utils.HqlHelper;
/**
 * 版块控制器  
 * @author dukangcheng
 *
 */
public class ForumAction extends BaseAction<Forum> {
	/**
	 * 0 表示全部主题 <br>
	 * 1 表示只看精华帖
	 */
	private int showType;
	/**
	 * 0:'默认排序(所有置顶帖在前面，并按最后更新时间降序排列)'<br/>
	 * 1:'只按最后更新时间排序' 2:'只按主题发表时间排序' 3:'只按回复数量排序'
	 */
	private int orderBy;
	/**
	 * true 表示升序<br>
	 * false 表示降序
	 */
	private boolean asc;

	/**
	 * 板块列表
	 * @return
	 */
	public String list() {
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	/**
	 *  显示单个板块
	 * @return
	 */
	public String show() {
		// 获取当前的版块
		Forum forum = forumService.findById(model.getId());
		ActionContext.getContext().put("forum", forum);
		/*
		 * // 获取帖子列表  版本1 
		 * List<Topic> topicList = forumService.findByForum(forum);
		 * ActionContext.getContext().put("topicList", topicList);
		 */
		// 准备列表的分页信息
		/*
		 * PageBean pageBean=topicService.findPageBean(pageNum,forum);
		 * ActionContext.getContext().getValueStack().push(pageBean);
		 */
		// 准备列表数据  版本2
		// String
		// hql="from Topic t where t.forum=? order by(case t.type when 2 then 2 else 0 end)DESC ,t.lastUpdateTime desc";
        //版本 3
        /*String hql = "from Topic t where t.forum =? ";
		String countSql = "select count(*) from Topic t where t.forum= ?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(forum);

		if (showType == 1) { // 查询精品帖
			hql += " and t.type=?";
			countSql+= " and  t.type=?";
			parameters.add(Topic.TYPE_BEST);
		}
		// 排序条件
		if (orderBy == 0) {// 0.按照默认情况排序
			hql += " order by (case t.type when 2 then 2 else 0 end)DESC, t.lastUpdateTime desc";
		}
		if (orderBy == 1) {// 1.只按最后更新时间排序
			hql += " order by t.lastUpdateTime " + (asc ? "asc" : "desc");
		}
		if (orderBy == 2) {// 2.只按主题发表时间排序
			hql += " order by t.postTime " + (asc ? "asc" : "desc");
		}
		if (orderBy == 3) {// 3.只按回复数量排序
			hql += " order by t.replyCount " + (asc ? "asc" : "desc");
		}

		System.out.println(hql + "*************");*/
		
        /*PageBean pageBean = topicService.findPageBean(pageNum, parameters, hql,
				countSql);
		ActionContext.getContext().getValueStack().push(pageBean);*/		
		HqlHelper hh=new HqlHelper(Topic.class, "t")
		.addWhereClause(" forum=? ", forum)
		.addWhereClause(showType==1, " type= ? ", forum,1)
		.addOrderByClause(orderBy==1, asc, "t.lastUpdateTime ")
		.addOrderByClause(orderBy==2, asc, "t.postTime ")
		.addOrderByClause(orderBy==3, asc, "t.replyCount ")
		.addOrderByClause(orderBy==0, false, "(case t.type when 2 then 2 else 0 end) ")
		.addOrderByClause(orderBy==0, false, "lastUpdateTime ");
		//准备分页信息
		PageBean pageBean=topicService.findPageBean(pageNum, hh);
        ActionContext.getContext().getValueStack().push(pageBean);
		return "show";
	}

	public int getShowType() {
		return showType;
	}

	public void setShowType(int showType) {
		this.showType = showType;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	// ------

}
