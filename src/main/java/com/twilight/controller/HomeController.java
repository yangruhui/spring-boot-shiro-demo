package com.twilight.controller;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilight.config.shiro.ShiroToken;
import com.twilight.utils.Constant;

@Controller
public class HomeController {
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping(value="login")
	public String login() {
		return "templates/login.html";
	}
	
	/**
	 * 跳转到主页面
	 * @return
	 */
	@RequestMapping(value="index")
	public String index() {
		return "templates/index.html";
	}
	
	/**
	 * 没访问权限
	 * @return
	 */
	@RequestMapping(value="403")
	public String authorization() {
		return "templates/403.html";
	}
	
	/**
	 *  ajax登录请求
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="ajaxLogin",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(String username, String password) {
		System.out.println("HomeController.login() post");
		System.out.println("username=" + username + ", password=" + password);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			ShiroToken token = new ShiroToken();
			token.setUsername(username);
			token.setPassword(password);
			SecurityUtils.getSubject().login(token);
			resultMap.put(Constant.ERRNO, 200);
			resultMap.put(Constant.ERRMSG, "登录成功");
		}catch(AuthenticationException e) {
			System.out.println(e.getMessage());
			resultMap.put(Constant.ERRNO, 500);
			resultMap.put(Constant.ERRMSG, e.getMessage());
		}
		
		return resultMap;
	}
	
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping(value="logout",method =RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> logout(){
		System.out.println("HomeController.logout() post");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			SecurityUtils.getSubject().logout();
			resultMap.put(Constant.ERRNO, 200);
			resultMap.put(Constant.ERRMSG, "退出成功");
		}catch (Exception e) {
			System.err.println(e.getMessage());
			resultMap.put(Constant.ERRNO, 500);
			resultMap.put(Constant.ERRMSG, e.getMessage());
		}
		return resultMap;
	}
}
