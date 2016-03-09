package com.web.oa.model;

import java.io.Serializable;
/**
 * »Ø¸´
 */
public class Reply extends Article implements Serializable{
    /*
     * Ìû×Ó
     */
	private Topic topic;
 
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
    
}
