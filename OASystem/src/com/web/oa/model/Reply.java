package com.web.oa.model;

import java.io.Serializable;
/**
 * �ظ�
 */
public class Reply extends Article implements Serializable{
    /*
     * ����
     */
	private Topic topic;
 
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
    
}
