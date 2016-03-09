package com.web.oa.ui.action;

import com.opensymphony.xwork2.ActionSupport;
import com.web.oa.service.UserService;
/**
 * ��ҳ������
 *   ������ҳ����ʾ�Լ�frame��ܵ�ÿ�����ֵ���ʾ���
 * @author dukangcheng
 *
 */
public class HomeAction extends ActionSupport {

	private UserService userService;

	/**
	 *  ��ҳҳ�沼��
	 * @return
	 */
	public String index() {
       
		return "index";
	}

	/**
	 * ��������
	 * @return
	 */
	public String top() {
		return "top";
	}

	/**
	 *  �ײ�����
	 * @return
	 */
	public String bottom() {
		
		
		return "bottom";
	}

	/**
	 *  ��߳���
	 * @return
	 */
	public String left() {
		return "left";
	}

	/**
	 *  �ұ߳���
	 * @return
	 */
	public String right() {
		return "right";
	}

	// --------
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
