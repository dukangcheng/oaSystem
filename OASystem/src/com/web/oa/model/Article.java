package com.web.oa.model;

import java.util.Date;

/**
 * 
 * ������
 */
public class Article {
	private int id;
	private String title; //������
	private String content;//���� 
	private String faceIcon; //����
	private User author;// �û�
	private Date postTime;// ����ʱ��
	private String ipAddress;//IP��ַ

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFaceIcon() {
		return faceIcon;
	}

	public void setFaceIcon(String faceIcon) {
		this.faceIcon = faceIcon;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
}
