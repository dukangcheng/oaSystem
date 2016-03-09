package com.web.oa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import com.opensymphony.xwork2.ActionContext;

@Entity
public class User implements Serializable {
	private int id;
	private String loginName;
	private String password;
	// 真实姓名
	private String name;
	// 用户创建时间
	private Date createTime;
	// 用户取消时间
	private Date expireTime;

	// 性别
	private String gender;
	// 联系电话
	private String phoneNum;
	// 电子邮件
	private String email;
	// 说明
	private String description;
	//每个员工对应的领导
	private User manager;
	private Set<Role> roles = new HashSet<Role>();
	
	private Orgnization org;
	public boolean hasPrivilegeByName(String name) {
		// System.out.println("----------->"+name+"<------------------");
		// System.out.println("----------->"+this.getName()+"<------------------");
		// 顶级权限名称是 "设置权限" 直接返回false
		// 判断是否为超级管理员
		if (isAdmin()) {
			if (name.equals("设置权限")) {
				return false;
			}
			return true;
		}

		// 判断普通用户是否具有某种权限
		for (Role role : roles) {
			// System.out.println("角色名称----------->"+role.getName()+
			// "<------------------角色的权限数量："+role.getPrivileges().size());
			for (Privilege privilege : role.getPrivileges()) {
				// System.out.println("----------->"+privilege.getName()+"<------------------");
				if (privilege.getName().equals(name)) {
					// System.out.println("----------->"+privilege.getName().equals(name)+"<------------------");
					if (name.equals("设置权限")) {
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}

	// 根据 权限URL 判断是否存在Privilege(权限)
	public boolean hasPrivilegeByPrivilegeUrl(String privUrl) {
		// 判断是否为超级管理员
		if (isAdmin()) {
			return true;
		}
		if (privUrl.equals("roleAction_setPrivilege")) {
			return false;
		}
		// 判断URL是否是以UI结尾，去掉UI后缀，得到相应的权限(比如：addUI和add具有相同的权限)；
		if (privUrl.endsWith("UI")) {
			privUrl = privUrl.substring(0, privUrl.length() - 2);
		}
		// 从application容器中将所有的权限url全部都放入集合中
		List<String> allPrivilegeUrls = (List<String>) ActionContext
				.getContext().getApplication().get("allPrivilegeUrls");
		// System.out.println("********>"+privUrl+"<********** privelegeUrl");
		// System.out.println("********>"+allPrivilegeUrls.size()+"<**********");
		// 如果是不需要控制的功能，则直接返回true
		if (!allPrivilegeUrls.contains(privUrl)) {
			return true;
		} else {
			// 便利当前用户的角色 对应的权限 ,判断当前用户是否拥有此权限
			for (Role role : roles) {
				for (Privilege privilege : role.getPrivileges()) {
					if (privUrl.equals(privilege.getUrl())) {
						return true;
					}
				}
			}
			return false;
		}
	}
 
	public void setManager(User manager) {
		this.manager = manager;
	}

	public User getManager() {
		return manager;
	}

	public boolean isAdmin() {
		// TODO Auto-generated method stub
		return "admin".equals(loginName);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Orgnization getOrg() {
		return org;
	}

	public void setOrg(Orgnization org) {
		this.org = org;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
