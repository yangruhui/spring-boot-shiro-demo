package com.twilight.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.twilight.repository.SysRoleRepository;

@Service
public class SysRoleService {

	@Resource
	private SysRoleRepository sysRoleRepository;
}
