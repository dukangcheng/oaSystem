package com.web.oa.model;

import java.util.Date;

/**
 * ��ٵ�
 *
 */
public class LeaveBill {
	public final static int LEAVE_BILL_STATE_INIT=0;   
	public final static int LEAVE_BILL_STATE_START=1; 
	public final static int LEAVE_BILL_STATE_COMPLETE=2;
    private int id;  
    private String content;  //��ٵ�������
    private Integer days;  //���������
    private Date leaveTime=new Date();//���ʱ��
    private Date endTime=new Date();  
    private User user;   //��ٵ���������
    private String remark;  //��ٵ�����ע
    private int state=0;   //��ٵ���״̬
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
    
    
 }
