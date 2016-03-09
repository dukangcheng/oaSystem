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
	// ��ʵ����
	private String name;
	// �û�����ʱ��
	private Date createTime;
	// �û�ȡ��ʱ��
	private Date expireTime;

	// �Ա�
	private String gender;
	// ��ϵ�绰
	private String phoneNum;
	// �����ʼ�
	private String email;
	// ˵��
	private String description;
	//ÿ��Ա����Ӧ���쵼
	private User manager;
	private Set<Role> roles = new HashSet<Role>();
	
	private Orgnization org;
	public boolean hasPrivilegeByName(String name) {
		// System.out.println("----------->"+name+"<------------------");
		// System.out.println("----------->"+this.getName()+"<------------------");
		// ����Ȩ�������� "����Ȩ��" ֱ�ӷ���false
		// �ж��Ƿ�Ϊ��������Ա
		if (isAdmin()) {
			if (name.equals("����Ȩ��")) {
				return false;
			}
			return true;
		}

		// �ж���ͨ�û��Ƿ����ĳ��Ȩ��
		for (Role role : roles) {
			// System.out.println("��ɫ����----------->"+role.getName()+
			// "<------------------��ɫ��Ȩ��������"+role.getPrivileges().size());
			for (Privilege privilege : role.getPrivileges()) {
				// System.out.println("----------->"+privilege.getName()+"<------------------");
				if (privilege.getName().equals(name)) {
					// System.out.println("----------->"+privilege.getName().equals(name)+"<------------------");
					if (name.equals("����Ȩ��")) {
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}

	// ���� Ȩ��URL �ж��Ƿ����Privilege(Ȩ��)
	public boolean hasPrivilegeByPrivilegeUrl(String privUrl) {
		// �ж��Ƿ�Ϊ��������Ա
		if (isAdmin()) {
			return true;
		}
		if (privUrl.equals("roleAction_setPrivilege")) {
			return false;
		}
		// �ж�URL�Ƿ�����UI��β��ȥ��UI��׺���õ���Ӧ��Ȩ��(���磺addUI��add������ͬ��Ȩ��)��
		if (privUrl.endsWith("UI")) {
			privUrl = privUrl.substring(0, privUrl.length() - 2);
		}
		// ��application�����н����е�Ȩ��urlȫ�������뼯����
		List<String> allPrivilegeUrls = (List<String>) ActionContext
				.getContext().getApplication().get("allPrivilegeUrls");
		// System.out.println("********>"+privUrl+"<********** privelegeUrl");
		// System.out.println("********>"+allPrivilegeUrls.size()+"<**********");
		// ����ǲ���Ҫ���ƵĹ��ܣ���ֱ�ӷ���true
		if (!allPrivilegeUrls.contains(privUrl)) {
			return true;
		} else {
			// ������ǰ�û��Ľ�ɫ ��Ӧ��Ȩ�� ,�жϵ�ǰ�û��Ƿ�ӵ�д�Ȩ��
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
