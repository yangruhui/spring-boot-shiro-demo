package com.twilight.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twilight.model.SysUser;
import com.twilight.repository.SysUserRepository;

@Service
public class SysUserService {

	@Resource
	private SysUserRepository sysUserRepository;
	
	@Transactional(readOnly = true)
	public SysUser findByUsername(String username) {
		return sysUserRepository.findByUsername(username);
	}
	
	public SysUser login(String username, String password) {
		SysUser sysUser = null;
//		List<SysUser> list = sysUserRepository.
//				find("form SysUser where username like ? and password like ?", 
//						new String[] {username, password});
		List<SysUser> list = sysUserRepository.find("from SysUser where username like '" + username + "' and password like '" + password + "'");
		if(!list.isEmpty()) sysUser = list.get(0);
		return sysUser;
	}
}
