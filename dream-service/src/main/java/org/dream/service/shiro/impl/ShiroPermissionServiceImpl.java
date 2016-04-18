package org.dream.service.shiro.impl;

import javax.annotation.Resource;

import org.dream.common.entity.ShiroPermission;
import org.dream.common.entity.ShiroRolePermssion;
import org.dream.dao.shiro.ShiroPermissionMapper;
import org.dream.dao.shiro.ShiroRolePermssionMapper;
import org.dream.service.shiro.IShiroPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShiroPermissionServiceImpl implements IShiroPermissionService {

	@Resource
	private ShiroPermissionMapper shiroPermissionMapper;

	@Resource
	private ShiroRolePermssionMapper shiroRolePermssionMapper;

	@Override
	public ShiroPermission createPermission(ShiroPermission permission) {
		shiroPermissionMapper.insertSelective(permission);
		return permission;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void deletePermission(Long permissionId) {

		shiroPermissionMapper.deleteByPrimaryKey(permissionId);

		/** 把与permission关联的相关表的数据删掉 */
		ShiroRolePermssion rolePermssion = new ShiroRolePermssion();
		rolePermssion.setPermissionId(permissionId);
		shiroRolePermssionMapper.delete(rolePermssion);
	}

}
