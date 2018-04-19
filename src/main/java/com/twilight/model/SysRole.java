package com.twilight.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 系统角色实体类
 * @author twilight
 *
 */
@Entity
public class SysRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	private Integer id; // 主键
	private String role; // 角色标识 程序中判断使用,如"admin",这个是唯一的:
	private String description; // 角色描述,UI界面显示使用
	
	//角色 -- 权限关系： 多对多关系;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="SysRole_has_SysPermission",joinColumns={@JoinColumn(name="SysRole_id")},inverseJoinColumns={@JoinColumn(name="SysPermission_id")})
	@Cascade(value = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonIgnore
	private List<SysPermission> sysPermissions;
	
	// 用户 - 角色关系定义;
	@ManyToMany
	@JoinTable(name="SysUser_has_SysRole",joinColumns={@JoinColumn(name="SysRole_id")},inverseJoinColumns={@JoinColumn(name="SysUser_id")})
	@Cascade(value = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonIgnore
	private List<SysUser> sysUsers;// 一个角色对应多个用户
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<SysPermission> getSysPermissions() {
		return sysPermissions;
	}
	public void setSysPermissions(List<SysPermission> sysPermissions) {
		this.sysPermissions = sysPermissions;
	}
	public List<SysUser> getSysUsers() {
		return sysUsers;
	}
	public void setSysUsers(List<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}
}
