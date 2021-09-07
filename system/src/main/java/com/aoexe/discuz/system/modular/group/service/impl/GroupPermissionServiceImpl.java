package com.aoexe.discuz.system.modular.group.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aoexe.discuz.system.modular.group.entity.GroupPermission;
import com.aoexe.discuz.system.modular.group.mapper.GroupPermissionMapper;
import com.aoexe.discuz.system.modular.group.service.IGroupPermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GroupPermissionServiceImpl extends ServiceImpl<GroupPermissionMapper, GroupPermission>
		implements IGroupPermissionService {

	@Override
	public Set<String> getPermissionsByGroupId(Long groupId) {
		QueryWrapper<GroupPermission> wrapper = new QueryWrapper<>();
		wrapper.eq("group_id", groupId);
		return list(wrapper).stream().map(GroupPermission::getPermission).collect(Collectors.toSet());
	}

	@Override
	public Set<String> getPermissionsByGroupIds(Long[] groupIds) {
		QueryWrapper<GroupPermission> wrapper = new QueryWrapper<>();
		wrapper.in("group_id", Arrays.asList(groupIds));
		return list(wrapper).stream().map(GroupPermission::getPermission).collect(Collectors.toSet());
	}

	@Override
	public boolean removeByGroupId(Long groupId) {
		QueryWrapper<GroupPermission> wrapper = new QueryWrapper<>();
		wrapper.eq("group_id", groupId);
		return remove(wrapper);
	}

	@Override
	public boolean removeByGroupIds(Long[] groupIds) {
		QueryWrapper<GroupPermission> wrapper = new QueryWrapper<>();
		wrapper.in("group_id", Arrays.asList(groupIds));
		return remove(wrapper);
	}

	@Override
	public boolean insertByGroupId(Long groupId, Set<String> permissions) {
		List<GroupPermission> groupPermissions = new ArrayList<>(permissions.size());
		permissions.forEach(p -> {
			GroupPermission g = new GroupPermission();
			g.setGroupId(groupId);
			g.setPermission(p);
			groupPermissions.add(g);
		});
		return saveBatch(groupPermissions);
	}

}
