package org.dream.service.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.dream.common.entity.ShiroUser;
import org.dream.service.shiro.IShiroUserService;

public class ShiroUserRealm extends AuthorizingRealm {

	@Resource
	private IShiroUserService shiroUserService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = principals.getPrimaryPrincipal().toString();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(shiroUserService.findRoles(username));
		authorizationInfo.setStringPermissions(shiroUserService.findPermissions(username));
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		ShiroUser shiroUser = shiroUserService.findByUsername(username);
		if (shiroUser == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		if (Boolean.TRUE.equals(shiroUser.getLocked())) {
			throw new LockedAccountException(); // 帐号锁定
		}

		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(shiroUser.getUsername(), // 用户名
				shiroUser.getPassword(), // 密码
				ByteSource.Util.bytes(shiroUser.getCredentialsSalt()), // salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
