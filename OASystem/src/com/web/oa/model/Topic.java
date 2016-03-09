package com.web.oa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * 帖子
 * @author dukangcheng
 *
 */
public class Topic extends Article implements Serializable {
	// 普通贴
	public static final int TYPE_NORMAL = 0;
	// 精华帖
	public static final int TYPE_BEST = 1;
	// 置顶帖
	public static final int TYPE_TOP = 2;
	/*
	 * 版块 many to one
	 */
	private Forum forum;
	/*
	 * 回复 one to many
	 */
	private Set<Reply> replies = new HashSet<Reply>();
	/*
	 * 帖子类型
	 */
	private int type;
	/*
	 * 回复次数
	 */
	private int replyCount;
	/*
	 * 最后回复时间
	 */
	private Date lastUpdateTime;  //特殊字段
	/*
	 * 最后回复
	 */
	private Reply lastReply;   //特殊字段

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Reply getLastReply() {
		return lastReply;
	}

	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}

}
