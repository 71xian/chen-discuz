package com.aoexe.discuz.system.modular.group.service.impl;

import com.aoexe.discuz.system.modular.group.model.entity.GroupPermission;
import com.aoexe.discuz.system.modular.group.mapper.GroupPermissionMapper;
import com.aoexe.discuz.system.modular.group.service.IGroupPermissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-11
 */
@Service
public class GroupPermissionServiceImpl extends ServiceImpl<GroupPermissionMapper, GroupPermission> implements IGroupPermissionService {

	@Override
	public void removeByGroupId(Long groupId) {
		LambdaQueryWrapper<GroupPermission> lambdaQuery = Wrappers.lambdaQuery(GroupPermission.class);
		lambdaQuery.eq(GroupPermission::getGroupId, groupId);
		this.remove(lambdaQuery);
	}

	@Override
	public List<GroupPermission> getByGroupId(Long groupId) {
		LambdaQueryWrapper<GroupPermission> lambdaQuery = Wrappers.lambdaQuery(GroupPermission.class);
		lambdaQuery.eq(GroupPermission::getGroupId, groupId);
		lambdaQuery.select(GroupPermission::getPermission);
		return this.list(lambdaQuery);
	}

}
