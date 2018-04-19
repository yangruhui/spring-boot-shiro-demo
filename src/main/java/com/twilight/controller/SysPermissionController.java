package com.twilight.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilight.service.SysPermissionService;

@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController {

	@Resource
	private SysPermissionService sysPermissionService;
	
	/**
	 * 查询列表
	 * @return
	 */
	@RequestMapping("/list")
	public Map<String, Object> list(Integer pageIndex, Integer pageSize, String sortField, String sortOrder,
			String menuName){
		System.out.println("pageIndex="+pageIndex+",pageSize="+pageSize
				+",sortField="+sortField+",sortOrder="+sortOrder
				+",menuName="+menuName);
		return sysPermissionService.list(pageIndex+1, pageSize, sortField, sortOrder, menuName);
	}
	
	/**
	 * 添加权限资源
	 * @param data json格式的数据
	 * @return
	 */
	@RequestMapping("/save")
	public Map<String, Object> save(String data){
		System.out.println("data="+data);
		return sysPermissionService.save(data);
	}
	
	/**
	 * 查找一条记录
	 * @param id 主键id
	 * @return
	 */
	@RequestMapping("/findSysPermission")
	public Map<String, Object> findSysPermission(String id){
		System.out.println("id="+id);
		return sysPermissionService.findSysPermission(id);
	}
	
	@RequestMapping("/update")
	public Map<String, Object> update(String data){
		return sysPermissionService.update(data);
	}
	
	@RequestMapping("/delete")
	public Map<String, Object> delete(String ids){
		String[] idsArr = ids.split(","); 
		return sysPermissionService.delete(idsArr);
	}
}
