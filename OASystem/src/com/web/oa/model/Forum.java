package com.web.oa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 版块
 * @author dukangcheng
 *
 */
public class Forum implements Serializable{

	private int id;
	/* 版块标题 */
	private String title;
	/* 版块描述 */
	private String description;
	/* 表示版块位置 */
	private int postion;
	/*帖子   
	 *one to many 
	 */
	private Set<Topic> topics = new HashSet<Topic>();
	/*
	 * 帖子总数 
	 */
	private int topicCount;
	/* 
	 *文章总数
	 */
	private int articleCount;
	/* 最后发布帖子
	 * one to one; 
	 */
	private Topic lastTopic;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPostion() {
		return postion;
	}

	public void setPostion(int postion) {
		this.postion = postion;
	}

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	public int getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public Topic getLastTopic() {
		return lastTopic;
	}

	public void setLastTopic(Topic lastTopic) {
		this.lastTopic = lastTopic;
	}

}
