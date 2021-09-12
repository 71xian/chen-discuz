package com.aoexe.discuz.system.modular.group.service;

import java.util.List;

import com.aoexe.discuz.system.modular.group.model.entity.GroupPermission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-11
 */
public interface IGroupPermissionService extends IService<GroupPermission> {

	void removeByGroupId(Long groupId);
	
	List<GroupPermission> getByGroupId(Long groupId);
}
