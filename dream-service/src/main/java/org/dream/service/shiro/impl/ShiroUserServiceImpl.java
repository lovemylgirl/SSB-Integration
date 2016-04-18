package org.dream.service.shiro.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.dream.common.entity.ShiroPermission;
import org.dream.common.entity.ShiroRole;
import org.dream.common.entity.ShiroUser;
import org.dream.common.entity.ShiroUserRole;
import org.dream.dao.shiro.ShiroPermissionMapper;
import org.dream.dao.shiro.ShiroRoleMapper;
import org.dream.dao.shiro.ShiroUserMapper;
import org.dream.dao.shiro.ShiroUserRoleMapper;
import org.dream.service.shiro.IShiroUserService;
import org.dream.service.shiro.PasswordHelper;
import org.springframework.stereotype.Service;

@Service
public class ShiroUserServiceImpl implements IShiroUserService {

	@Resource
	private ShiroUserMapper shiroUserMapper;

	@Resource
	private ShiroUserRoleMapper shiroUserRoleMapper;

	@Resource
	private ShiroRoleMapper roleMapper;

	@Resource
	private ShiroPermissionMapper permissionMapper;

	@Resource
	private PasswordHelper passwordHelper;

	public void setPasswordHelper(PasswordHelper passwordHelper) {
		this.passwordHelper = passwordHelper;
	}

	@Override
	public ShiroUser createUser(ShiroUser user) {
		/** 加密密码 */
		passwordHelper.encryptPassword(user);
		shiroUserMapper.insertSelective(user);
		return user;
	}

	@Override
	public void changePassword(Long userId, String newPassword) {
		ShiroUser shiroUser = shiroUserMapper.selectByPrimaryKey(userId);
		shiroUser.setPassword(newPassword);
		passwordHelper.encryptPassword(shiroUser);
		shiroUserMapper.updateByPrimaryKeySelective(shiroUser);
	}

	@Override
	public void correlationRoles(Long userId, Long... roleIds) {
		List<ShiroUserRole> list = new ArrayList<>();
		ShiroUserRole role = null;
		for (Long l : roleIds) {
			role = new ShiroUserRole();
			role.setUserId(userId);
			role.setRoleId(l);
			list.add(role);
		}
		shiroUserRoleMapper.insertList(list);
	}

	@Override
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		ShiroUserRole role = null;
		for (Long l : roleIds) {
			role = new ShiroUserRole();
			role.setRoleId(l);
			role.setUserId(userId);
			shiroUserRoleMapper.delete(role);
		}
	}

	@Override
	public ShiroUser findByUsername(String username) {
		ShiroUser shiroUser = new ShiroUser();
		shiroUser.setUsername(username);
		List<ShiroUser> list = shiroUserMapper.select(shiroUser);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Set<String> findRoles(String username) {
		ShiroRole role = new ShiroRole();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("username", username);
		role.setParamMap(paramMap);
		List<ShiroRole> list = roleMapper.listRoles(role);
		Set<String> roles = new HashSet<>();
		for (ShiroRole sr : list) {
			roles.add(sr.getRole());
		}
		return roles;
	}

	@Override
	public Set<String> findPermissions(String username) {
		ShiroPermission permission = new ShiroPermission();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("username", username);
		permission.setParamMap(paramMap);
		List<ShiroPermission> list = permissionMapper.listPermissions(permission);
		Set<String> permissions = new HashSet<>();
		for (ShiroPermission sr : list) {
			permissions.add(sr.getPermission());
		}
		return permissions;
	}

}
