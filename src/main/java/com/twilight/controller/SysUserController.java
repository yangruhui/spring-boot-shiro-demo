package com.twilight.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInfo")
public class SysUserController {
	/**
	 * 用户查询.
	 * @return
	 */
	@RequestMapping("/userList")
	//@RequiresPermissions("userInfo:view")//权限管理;
	public String userInfo(){
		System.out.println("userList()");
		try {
		    SecurityUtils.getSubject().checkPermission("userInfo:view");
		}catch(AuthorizationException e) {
			System.out.println(e.getMessage());
		}
		return "userInfo";
	}
	
	/**
	 * 用户添加;
	 * @return
	 */
	@RequestMapping("/userAdd")
	//@RequiresPermissions("userInfo:add")//权限管理;
	public String userInfoAdd(){
		System.out.println("userInfoAdd()");
		try {
		    SecurityUtils.getSubject().checkPermission("userInfo:add");
		}catch(AuthorizationException e) {
			System.out.println(e.getMessage());
		}
		return "userInfoAdd";
	}
	
	/**
	 * 用户删除;
	 * @return
	 */
	@RequestMapping("/userDel")
	//@RequiresPermissions("userInfo:del")//权限管理;
	public String userDel(){
		System.out.println("userDel()");
		try {
		    SecurityUtils.getSubject().checkPermission("userInfo:del");
		}catch(AuthorizationException e) {
			System.out.println(e.getMessage());
		}
		return "userInfoDel";
	}
}
