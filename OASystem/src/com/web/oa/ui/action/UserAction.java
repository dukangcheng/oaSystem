package com.web.oa.ui.action;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.opensymphony.xwork2.ActionContext;
import com.web.oa.base.BaseAction;
import com.web.oa.model.Orgnization;
import com.web.oa.model.PageBean;
import com.web.oa.model.Role;
import com.web.oa.model.User;
import com.web.oa.service.OrgnizationService;
import com.web.oa.utils.HqlHelper;
import com.web.oa.utils.OrgnizationUtils;
/**
 * 用户控制器
 * @author dukangcheng
 *
 */
public class UserAction extends BaseAction<User> {
	private int orgnizationId;
	private int[] roleIds;

	/**
	 *  列表页面
	 * @return
	 */
	public String list() {
		/*// 获取全部的数据，然后放入值栈的map集合中  //版本1
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);*/
		
		//版本2
        HqlHelper hh=new HqlHelper(User.class, "u");
        hh.addOrderByClause(true, false, "u.createTime");
		PageBean pageBean=userService.findPageBean(pageNum,hh);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "list";
	}

	/**
	 *  删除
	 * @return
	 */
	public String delete() {
		userService.delete(model.getId());
		return "toList";
	}

	/**
	 *  添加页面
	 * @return
	 */
	public String addUI() {
		// 1.1下拉框中关联的属性 准备数据: orgnizationList;
		List<Orgnization> topList = orgnizationService.findTopList();
		List<Orgnization> orgnizationList = OrgnizationUtils
				.findChildren(topList);
		ActionContext.getContext().put("orgnizationList", orgnizationList);
		// 1。2复选下拉框中关联的属性 准备数据: roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}

	/**
	 *  添加
	 * @return
	 */
	public String add() {
		// 1.1 获取关联属性 设置部门
		Orgnization orgnization = orgnizationService.findById(orgnizationId);
		model.setOrg(orgnization);
		// 1。2 获取关联属性 设置岗位
		List<Role> roles = roleService.findByIds(roleIds);
		model.setRoles(new HashSet<Role>(roles));
		// 2.设置默认的密码： 对密码进行加密，此处采用md5 的加密方式；
		String passWord = DigestUtils.md5Hex("1234");
		model.setPassword(passWord);
		// 3.其他的属性：不需要在页面显示的数据 : 创建时间
		model.setCreateTime(new Date());
		// 4.将封装好的数据存入数据库
		User user = userService.findByLoginNameAndPassword(
				model.getLoginName(), model.getPassword());
		if(user==null){
			userService.addUser(model);
			return "toList";
		}else{
			addFieldError("add", "添加的用户已经存在");
			// 1.1下拉框中关联的属性 准备数据: orgnizationList;
			List<Orgnization> topList = orgnizationService.findTopList();
			List<Orgnization> orgnizationList = OrgnizationUtils
					.findChildren(topList);
			ActionContext.getContext().put("orgnizationList", orgnizationList);
			// 1。2复选下拉框中关联的属性 准备数据: roleList
			List<Role> roleList = roleService.findAll();
			ActionContext.getContext().put("roleList", roleList);
            return "saveUI";		
		}
	}

	/**
	 *  修改页面
	 * @return
	 */
	public String editUI() {
		// 1.1 准备数据: orgnizationList;
		List<Orgnization> topList = orgnizationService.findTopList();
		List<Orgnization> orgnizationList = OrgnizationUtils
				.findChildren(topList);
		ActionContext.getContext().put("orgnizationList", orgnizationList);
		// 1.2 准备数据: roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		// 2.准备回显的数据
		// 2.1 普通属性的数据
		User user = userService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		// System.out.println(user.getOrg().getName()+"**********");
		// 2.2 下拉框的默认选中
		if (user.getOrg() != null) {
			orgnizationId = user.getOrg().getId();
		}
		// 2.3复选下拉框的默认选中
		if (user.getRoles().size() > 0) {
			roleIds = new int[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}
		return "saveUI";
	}

	/**
	 *  修改
	 * @return
	 */
	public String edit() {
		// 1.得到user对象
		User user = userService.findById(model.getId());
		// 2.设置user基本属性
		user.setName(model.getName());
		user.setDescription(model.getDescription());
		user.setLoginName(model.getLoginName());
		user.setEmail(model.getEmail());
		user.setPhoneNum(model.getPhoneNum());
		// 3.(1)设置关联属性 ：所属部门
		user.setOrg(orgnizationService.findById(orgnizationId));
		// 3.(2)设置关联属性：所在岗位
		user.setRoles(new HashSet<Role>(roleService.findByIds(roleIds)));
		// 将修改后的内容更新到数据库
		userService.update(user);

		return "toList";
	}
    /**
     * 初始化密码
     * @return
     */
	public String initPassword() {
		// 1.获取use对象
		User user = userService.findById(model.getId());
		// 2.对密码进行加密，此处采用md5 的加密方式；
		String passWord = DigestUtils.md5Hex("1234");
		user.setPassword(passWord);
		// 3.将密码初始化到数据库
		userService.update(user);
		return "toList";
	}

	/**
	 *  登录页面
	 * @return
	 */
	public String loginUI() {

		return "loginUI";
	}

	/**
	 *  登录
	 * @return
	 */
	public String login() {
		User user = userService.findByLoginNameAndPassword(
				model.getLoginName(), DigestUtils.md5Hex(model.getPassword()));
		if(user==null){
			//用户为空就返回登陆界面
			addFieldError("login", "登录的用户不存在！");
			return "loginUI";
		}else{
			//正确就登陆用户  应为  用户  需要能在重定向中也能保存数据，则需要session范围
           ActionContext.getContext().getSession().put("user",user);			
			return "toIndex";
		}
	}
    /**
     * 注销
     * @return
     */
	public String logout(){
		ActionContext.getContext().getSession().remove("user");
	    return "logout";
	}
	// -------
	public void setOrgnizationId(int orgnizationId) {
		this.orgnizationId = orgnizationId;
	}

	public int getOrgnizationId() {
		return orgnizationId;
	}

	public void setRoleIds(int[] roleIds) {
		this.roleIds = roleIds;
	}

	public int[] getRoleIds() {
		return roleIds;
	}
}
