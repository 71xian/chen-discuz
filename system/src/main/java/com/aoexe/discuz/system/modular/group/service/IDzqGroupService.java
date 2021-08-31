package com.aoexe.discuz.system.modular.group.service;

import java.util.Set;

import com.aoexe.discuz.system.modular.group.entity.DzqGroup;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
public interface IDzqGroupService extends IService<DzqGroup> {

	DzqGroup getDefaultGroup();

	void setDefaultGroup(Long groupId);

	Set<String> getPermissionByUserId(Long userId);
	
	void resetPermissions(Long groupId, Set<String> permissions);

	void create(String name);

	DzqGroup select(Long groupId);
	
	boolean remove(Long groupId);

	boolean removes(Long[] groupIds);

}
