package com.twilight.repository;

import com.twilight.model.SysUser;
import com.twilight.orm.base.BaseRepository;

public interface SysUserRepository extends BaseRepository<SysUser, Integer>{

	public SysUser findByUsername(String username);
}
