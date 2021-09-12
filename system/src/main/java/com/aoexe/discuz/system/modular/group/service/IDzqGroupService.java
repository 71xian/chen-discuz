package com.aoexe.discuz.system.modular.group.service;

import java.util.List;

import com.aoexe.discuz.system.modular.group.model.entity.DzqGroup;
import com.aoexe.discuz.system.modular.group.model.param.GroupParam;
import com.aoexe.discuz.system.modular.group.model.result.GroupResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-11
 */
public interface IDzqGroupService extends IService<DzqGroup> {

	DzqGroup create(GroupParam dto);

	void createGroupUser(Long groupId, Long userId);

	void createGroupUsers(Long groupId, List<Long> userIds);

	void remove(Long groupId);

	DzqGroup update(Long groupId, GroupParam dto);

	DzqGroup updateDefault(Long groupId);

	DzqGroup updateIcon(Long groupId, String icon);

	void editPermissions(Long groupId, List<String> permissions);

	GroupResult getGroupVO(Long groupId, boolean isInclude);
	
	List<String> getPermissionsByGroupId(Long groupId);

	List<String> getPermissionsByUserId(Long userId);

	List<String> getNamesByUserId(Long userId);
	
	IPage<GroupResult> selectPage(Wrapper<GroupResult> wrapper, Page<GroupResult> page, boolean isInclude);

}
