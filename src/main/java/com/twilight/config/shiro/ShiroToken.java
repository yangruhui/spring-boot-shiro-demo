package com.twilight.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * http://z77z.oschina.io/2017/02/13/SpringBoot+shiro%E6%95%B4%E5%90%88%E5%AD%A6%E4%B9%A0%E4%B9%8B%E7%99%BB%E5%BD%95%E8%AE%A4%E8%AF%81%E5%92%8C%E6%9D%83%E9%99%90%E6%8E%A7%E5%88%B6/
 * @author admin
 *
 */
public class ShiroToken implements AuthenticationToken {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username; //账户
	private String password; //密码
	
	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return getUsername();
	}
	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return getPassword();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
