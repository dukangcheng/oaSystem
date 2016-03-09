package com.web.oa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
public class Orgnization implements Serializable {
    private int id;
    private String name;
    private String sn;
    private String description;
    private Orgnization parent;
    private Set<Orgnization> children=new HashSet<Orgnization>();
    private Set<User> users=new HashSet<User>();
    
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
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
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Orgnization getParent() {
		return parent;
	}
	public void setParent(Orgnization parent) {
		this.parent = parent;
	}
	public Set<Orgnization> getChildren() {
		return children;
	}
	public void setChildren(Set<Orgnization> children) {
		this.children = children;
	}
    
    
}
