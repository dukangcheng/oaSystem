package com.web.oa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Privilege implements Serializable {
	private int id;
	private String name;
	private String url;
	private Set<Role> roles = new HashSet<Role>();
	private String description;
	private String icon; // 图标，顶级菜单用的
	private Privilege parent;  //上级权限
	private Set<Privilege> children=new HashSet<Privilege>();  //下级权限
    public Privilege(){}
    public Privilege(String name,String url,String icon,Privilege parent){
    	this.name=name;
    	this.url=url;
        this.icon=icon;
        this.parent=parent;
    }
    
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Privilege getParent() {
		return parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public Set<Privilege> getChildren() {
		return children;
	}

	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}

}
