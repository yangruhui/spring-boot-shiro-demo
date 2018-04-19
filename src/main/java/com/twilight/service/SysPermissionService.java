package com.twilight.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.twilight.config.shiro.ShiroSecurityUtils;
import com.twilight.model.SysPermission;
import com.twilight.orm.base.page.PageResultSet;
import com.twilight.repository.SysPermissionRepository;

@Service
public class SysPermissionService {

	@Resource
	private SysPermissionRepository sysPermissionRepository;
	
	/**
	 * 查询列表
	 * @param pageIndex 页码
	 * @param pageSize 页数
	 * @param sortField 排序字段
	 * @param sortOrder asc或desc
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> list(Integer pageIndex, Integer pageSize, String sortField, String sortOrder,
			String menuName){
		//权限验证
		if(!ShiroSecurityUtils.
				checkPermission("sysPermission:list")) return ShiroSecurityUtils.getPermissionMsg();
		Map<String, Object> map = new HashMap<>();
		StringBuffer hql = new StringBuffer(); //hql语句
		hql.append("from SysPermission").append(" where 1=1");
		if(null != menuName && !menuName.equals("")) {
			hql.append(" and menuName").append(" like '%").append(menuName).append("%'");
		}
		if(null!=sortOrder && !sortOrder.equals("")) {
		    hql.append(" order by ").append(sortField).append(" ").append(sortOrder);
		}
		System.out.println("hql:" + hql);
		PageResultSet<SysPermission> pageResultSet = sysPermissionRepository.
				find(hql.toString(), pageSize, pageIndex);
		map.put("total", pageResultSet.getPageInfo().getTotalRow());
		map.put("data", pageResultSet.getList());
		return map;
	}
	
	/**
	 * 添加数据
	 * @param data json格式的数据
	 * @return
	 */
	@Transactional(readOnly = false)
    public Map<String, Object> save(String data){
		//权限验证
		if(!ShiroSecurityUtils.
				checkPermission("sysPermission:save")) return ShiroSecurityUtils.getPermissionMsg();
    	Map<String, Object> map = new HashMap<>();
    	SysPermission sysPermission = new SysPermission();
    	JSONObject json = JSONObject.parseObject(data);
    	sysPermission.setMenuName(json.getString("menuName"));
    	sysPermission.setParentId(Integer.parseInt(json.getString("parentId")));
    	sysPermission.setPermission(json.getString("permission"));
    	sysPermission.setResourceType(json.getString("resourceType"));
    	sysPermission.setResourceUrl(json.getString("resourceUrl"));
    	sysPermission.setMenuIcon(json.getString("menuIcon"));
    	sysPermission.setMenuLink(json.getString("menuLink"));
    	sysPermission.setMenuSort(Integer.parseInt(json.getString("menuSort")));
    	sysPermissionRepository.save(sysPermission);
    	map.put("success", true);
    	map.put("msg", "添加成功");
    	return map;
	}
	
	/**
	 * 查找一条记录
	 * @param id 主键id
	 * @return
	 */
	@Transactional(readOnly = true)
    public Map<String, Object> findSysPermission(String id){
		//权限验证
		if(!ShiroSecurityUtils.
				checkPermission("sysPermission:findSysPermission")) return ShiroSecurityUtils.getPermissionMsg();
    	Map<String, Object> map = new HashMap<>();
    	SysPermission sysPermission = sysPermissionRepository.findOne(Integer.parseInt(id));
    	map.put("success", true);
    	map.put("data", sysPermission);
    	return map;
	}
    
    /**
     * 修改
     * @param data
     * @return
     */
    @Transactional(readOnly = false)
    public Map<String, Object> update(String data){
    	//权限验证
		if(!ShiroSecurityUtils.
				checkPermission("sysPermission:update")) return ShiroSecurityUtils.getPermissionMsg();
    	Map<String, Object> map = new HashMap<>();
    	JSONObject json = JSONObject.parseObject(data);
    	SysPermission sysPermission = sysPermissionRepository.findById(Integer.parseInt(json.getString("id")));
    	sysPermission.setMenuName(json.getString("menuName"));
    	sysPermission.setParentId(Integer.parseInt(json.getString("parentId")));
    	sysPermission.setPermission(json.getString("permission"));
    	sysPermission.setResourceType(json.getString("resourceType"));
    	sysPermission.setResourceUrl(json.getString("resourceUrl"));
    	sysPermission.setMenuIcon(json.getString("menuIcon"));
    	sysPermission.setMenuLink(json.getString("menuLink"));
    	sysPermission.setMenuSort(Integer.parseInt(json.getString("menuSort")));
    	sysPermissionRepository.save(sysPermission);
    	map.put("success", true);
    	map.put("msg", "修改成功");
    	return map;
    }
    
    /**
     * 删除
     * @param ids
     * @return
     */
    @Transactional(readOnly = false)
    public Map<String, Object> delete(String[] ids){
    	//权限验证
		if(!ShiroSecurityUtils.
				checkPermission("sysPermission:delete")) return ShiroSecurityUtils.getPermissionMsg();
    	Map<String, Object> map = new HashMap<>();
    	for(String id:ids) {
    		if(null == id || id.trim().equals("")) continue;
    		sysPermissionRepository.delete(Integer.parseInt(id));
    	}
    	map.put("success", true);
    	map.put("msg", "删除成功");
    	return map;
	}
}
