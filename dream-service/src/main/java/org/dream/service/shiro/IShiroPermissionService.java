package org.dream.service.shiro;

import org.dream.common.entity.ShiroPermission;

public interface IShiroPermissionService {

	ShiroPermission createPermission(ShiroPermission permission);

	void deletePermission(Long permissionId);
}
