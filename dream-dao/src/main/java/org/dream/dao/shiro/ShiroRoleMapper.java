package org.dream.dao.shiro;

import java.util.List;

import org.dream.common.entity.ShiroRole;

import tk.mybatis.mapper.common.Mapper;

public interface ShiroRoleMapper extends Mapper<ShiroRole> {
	List<ShiroRole> listRoles(ShiroRole role);
}
