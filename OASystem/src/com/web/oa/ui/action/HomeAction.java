package com.web.oa.ui.action;

import com.opensymphony.xwork2.ActionSupport;
import com.web.oa.service.UserService;
/**
 * 首页控制器
 *   控制首页的显示以及frame框架的每个部分的显示情况
 * @author dukangcheng
 *
 */
public class HomeAction extends ActionSupport {

	private UserService userService;

	/**
	 *  首页页面布局
	 * @return
	 */
	public String index() {
       
		return "index";
	}

	/**
	 * 顶部呈现
	 * @return
	 */
	public String top() {
		return "top";
	}

	/**
	 *  底部呈现
	 * @return
	 */
	public String bottom() {
		
		
		return "bottom";
	}

	/**
	 *  左边呈现
	 * @return
	 */
	public String left() {
		return "left";
	}

	/**
	 *  右边呈现
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
