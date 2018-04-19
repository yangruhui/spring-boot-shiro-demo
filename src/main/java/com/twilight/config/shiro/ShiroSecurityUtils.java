package com.twilight.config.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;

import com.twilight.model.SysUser;
import com.twilight.utils.Constant;

/**
 * shiro工具类
 * @author admin
 *
 */
public class ShiroSecurityUtils {

	/**
	 * 获取当前用户
	 * @return
	 */
	public static SysUser getUserAccount() {
		return (SysUser)SecurityUtils.getSubject().getPrincipal();
	}
	
	/**
	 * 权限验证
	 * @param permission
	 * @return
	 */
	public static boolean checkPermission(String permission) {
		try {
		    SecurityUtils.getSubject().checkPermission(permission);
		    return true;
		}catch(AuthorizationException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * 无访问权限
	 * @return
	 */
	public static Map<String, Object> getPermissionMsg(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(Constant.ERRNO, 403);
		resultMap.put(Constant.ERRMSG, "does not have permission");
		return resultMap;
	}
}
