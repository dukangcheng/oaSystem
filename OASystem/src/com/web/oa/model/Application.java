package com.web.oa.model;

import java.util.Date;
/**
 * 申请流程的信息类
 * @author dukangcheng
 *
 */
public class Application {
	public final static int APPLICATION_STATUS_INIT = 0;
	public final static int APPLICATION_STATUS_START = 1;
	public final static int APPLICATION_STATUS_COMPLETE = 2;
    private String id;   //获取历史流程实例的ID
	private String name; // 流程的名称
	private String description; // 流程的描述 在外置表单表中暂无 如自己设计的表单 可以带上

	private String startUserName; // 申请人
	private Date startTime;  //申请时间
    private Date endTime;   //流程结束时间
	private int status=APPLICATION_STATUS_INIT;   //申请的流程的状态

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartUserName() {
		return startUserName;
	}

	public void setStartUserName(String startUserName) {
		this.startUserName = startUserName;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
