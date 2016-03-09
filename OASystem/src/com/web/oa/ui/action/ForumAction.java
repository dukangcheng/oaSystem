package com.web.oa.ui.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.model.Forum;
import com.web.oa.model.PageBean;
import com.web.oa.model.Topic;
import com.web.oa.utils.HqlHelper;
/**
 * ��������  
 * @author dukangcheng
 *
 */
public class ForumAction extends BaseAction<Forum> {
	/**
	 * 0 ��ʾȫ������ <br>
	 * 1 ��ʾֻ��������
	 */
	private int showType;
	/**
	 * 0:'Ĭ������(�����ö�����ǰ�棬����������ʱ�併������)'<br/>
	 * 1:'ֻ��������ʱ������' 2:'ֻ�����ⷢ��ʱ������' 3:'ֻ���ظ���������'
	 */
	private int orderBy;
	/**
	 * true ��ʾ����<br>
	 * false ��ʾ����
	 */
	private boolean asc;

	/**
	 * ����б�
	 * @return
	 */
	public String list() {
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	/**
	 *  ��ʾ�������
	 * @return
	 */
	public String show() {
		// ��ȡ��ǰ�İ��
		Forum forum = forumService.findById(model.getId());
		ActionContext.getContext().put("forum", forum);
		/*
		 * // ��ȡ�����б�  �汾1 
		 * List<Topic> topicList = forumService.findByForum(forum);
		 * ActionContext.getContext().put("topicList", topicList);
		 */
		// ׼���б�ķ�ҳ��Ϣ
		/*
		 * PageBean pageBean=topicService.findPageBean(pageNum,forum);
		 * ActionContext.getContext().getValueStack().push(pageBean);
		 */
		// ׼���б�����  �汾2
		// String
		// hql="from Topic t where t.forum=? order by(case t.type when 2 then 2 else 0 end)DESC ,t.lastUpdateTime desc";
        //�汾 3
        /*String hql = "from Topic t where t.forum =? ";
		String countSql = "select count(*) from Topic t where t.forum= ?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(forum);

		if (showType == 1) { // ��ѯ��Ʒ��
			hql += " and t.type=?";
			countSql+= " and  t.type=?";
			parameters.add(Topic.TYPE_BEST);
		}
		// ��������
		if (orderBy == 0) {// 0.����Ĭ���������
			hql += " order by (case t.type when 2 then 2 else 0 end)DESC, t.lastUpdateTime desc";
		}
		if (orderBy == 1) {// 1.ֻ��������ʱ������
			hql += " order by t.lastUpdateTime " + (asc ? "asc" : "desc");
		}
		if (orderBy == 2) {// 2.ֻ�����ⷢ��ʱ������
			hql += " order by t.postTime " + (asc ? "asc" : "desc");
		}
		if (orderBy == 3) {// 3.ֻ���ظ���������
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
		//׼����ҳ��Ϣ
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
