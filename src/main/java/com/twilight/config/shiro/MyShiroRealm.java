package com.twilight.config.shiro;

import javax.annotation.Resource;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.twilight.model.SysPermission;
import com.twilight.model.SysRole;
import com.twilight.model.SysUser;
import com.twilight.service.SysUserService;

/**
 * 身份校验核心类;
 * 
 * 在认证、授权内部实现机制中都有提到，最终处理都将交给Real进行处理。
 * 因为在Shiro中，最终是通过Realm来获取应用程序中的用户、角色及权限信息的。
 * 通常情况下，在Realm中会直接从我们的数据源中获取Shiro需要的验证信息。可以说，Realm是专用于安全框架的DAO. 
 * 
 * @author twilight
 *
 */
public class MyShiroRealm extends AuthorizingRealm{

	@Resource
	private SysUserService sysUserService;
	
	@Override
	public boolean supports(AuthenticationToken token) {
		// TODO Auto-generated method stub
		return token instanceof ShiroToken;
	}
	
	/**
	 * 用户身份验证
	 * 1、检查提交的进行认证的令牌信息
	 * 2、根据令牌信息从数据源(通常为数据库)中获取用户信息
	 * 3、对用户信息进行匹配验证
	 * 4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例
	 * 5、验证失败则抛出AuthenticationException异常信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
		ShiroToken token = (ShiroToken) authcToken;
		//获取用户的输入的账号.
	    String username = (String)token.getPrincipal();
	    String password = (String) token.getCredentials();
	    System.out.println("username=" + username + ", password=" + password);
	    //通过username从数据库中查找 User对象，如果找到，没找到.
	  	//实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
	    SysUser sysUser = sysUserService.login(username, password);
	    System.out.println("----->>sysUser="+sysUser);
		if(sysUser == null){
			throw new AccountException("帐号或密码不正确！");
		}else if(2 == sysUser.getState()) {
			/**
	         * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
	         */
			throw new DisabledAccountException("帐号已经禁止登录！");
		}
		//账号判断;
		//加密方式;
		//交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
//		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//				sysUser, //用户名
//				sysUser.getPassword(), //密码
//                ByteSource.Util.bytes(sysUser.getCredentialsSalt()),//salt=username+salt
//                getName()  //realm name
//        );
	    //明文: 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
      SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
    		  sysUser, //用户名
    		  sysUser.getPassword(), //密码
             getName()  //realm name
      );
	    return authenticationInfo;
	}
	
	/**
	 * 链接权限的实现
	 * 当访问到页面的时候，链接配置了相应的权限或者shiro标签才会执行此方法否则不会执行，
	 * 所以如果只是简单的身份认证没有权限的控制的话，那么这个方法可以不进行实现，直接返回null即可。
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
		for(SysRole role:sysUser.getSysRoles()) {
			authorizationInfo.addRole(role.getRole());
			for(SysPermission p:role.getSysPermissions()) {
				authorizationInfo.addStringPermission(p.getPermission());
			}
		}
		//系统管理员admin才能访问以下链接权限
		if(sysUser.getUsername().equals("admin")) {
			authorizationInfo.addStringPermission("sysPermission:list");
			authorizationInfo.addStringPermission("sysPermission:save");
			authorizationInfo.addStringPermission("sysPermission:findSysPermission");
			authorizationInfo.addStringPermission("sysPermission:update");
			authorizationInfo.addStringPermission("sysPermission:delete");
		}
		return authorizationInfo;
	}


}
