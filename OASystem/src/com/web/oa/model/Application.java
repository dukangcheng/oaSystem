package com.web.oa.model;

import java.util.Date;
/**
 * �������̵���Ϣ��
 * @author dukangcheng
 *
 */
public class Application {
	public final static int APPLICATION_STATUS_INIT = 0;
	public final static int APPLICATION_STATUS_START = 1;
	public final static int APPLICATION_STATUS_COMPLETE = 2;
    private String id;   //��ȡ��ʷ����ʵ����ID
	private String name; // ���̵�����
	private String description; // ���̵����� �����ñ��������� ���Լ���Ƶı� ���Դ���

	private String startUserName; // ������
	private Date startTime;  //����ʱ��
    private Date endTime;   //���̽���ʱ��
	private int status=APPLICATION_STATUS_INIT;   //��������̵�״̬

	
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
