package com.web.oa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

public class Role implements Serializable{
	private int id;
	private String name;
	private String description;
	private Set<User> users=new HashSet<User>();
    private Set<Privilege> privileges=new HashSet<Privilege>();
    public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
    public Set<Privilege> getPrivileges() {
		return privileges;
	}
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
