package org.dream.dao.shiro;

import java.util.List;

import org.dream.common.entity.ShiroPermission;

import tk.mybatis.mapper.common.Mapper;

public interface ShiroPermissionMapper extends Mapper<ShiroPermission> {
	
	List<ShiroPermission> listPermissions(ShiroPermission permission);

}
