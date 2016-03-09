package com.web.oa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * ����
 * @author dukangcheng
 *
 */
public class Topic extends Article implements Serializable {
	// ��ͨ��
	public static final int TYPE_NORMAL = 0;
	// ������
	public static final int TYPE_BEST = 1;
	// �ö���
	public static final int TYPE_TOP = 2;
	/*
	 * ��� many to one
	 */
	private Forum forum;
	/*
	 * �ظ� one to many
	 */
	private Set<Reply> replies = new HashSet<Reply>();
	/*
	 * ��������
	 */
	private int type;
	/*
	 * �ظ�����
	 */
	private int replyCount;
	/*
	 * ���ظ�ʱ��
	 */
	private Date lastUpdateTime;  //�����ֶ�
	/*
	 * ���ظ�
	 */
	private Reply lastReply;   //�����ֶ�

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
