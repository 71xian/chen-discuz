package com.aoexe.discuz.system.modular.group.service.impl;

import static com.aoexe.discuz.core.constant.ResponseEnum.GROUPNAME_HAS_EXIST;
import static com.aoexe.discuz.core.constant.ResponseEnum.GROUP_HAS_USER;
import static com.aoexe.discuz.core.constant.ResponseEnum.GROUP_NOT_FOUND;
import static com.aoexe.discuz.core.constant.ResponseEnum.INVALID_PARAMETER;
import static com.aoexe.discuz.core.constant.ResponseEnum.NOT_SETTING_DEFAULT_GROUP;
import static com.aoexe.discuz.system.modular.group.consts.Constant.ADMIN;
import static com.aoexe.discuz.system.modular.group.consts.Constant.DEFAULT_GROUP;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.system.core.cache.GroupCache;
import com.aoexe.discuz.system.modular.group.entity.DzqGroup;
import com.aoexe.discuz.system.modular.group.entity.GroupPermission;
import com.aoexe.discuz.system.modular.group.mapper.DzqGroupMapper;
import com.aoexe.discuz.system.modular.group.service.IDzqGroupService;
import com.aoexe.discuz.system.modular.group.service.IGroupPermissionService;
import com.aoexe.discuz.system.modular.group.service.IGroupUserService;
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
public class DzqGroupServiceImpl extends ServiceImpl<DzqGroupMapper, DzqGroup> implements IDzqGroupService {

	@Resource
	private GroupCache cache;

	@Resource
	private IGroupPermissionService permissionService;

	@Resource
	private IGroupUserService groupUserService;

	@Override
	public DzqGroup getDefaultGroup() {
		DzqGroup group = cache.get(DEFAULT_GROUP);
		if (group == null) {
			QueryWrapper<DzqGroup> wrapper = new QueryWrapper<>();
			wrapper.eq("is_default", 1);
			group = baseMapper.selectOne(wrapper);
			if (group == null) {
				throw new BaseException(NOT_SETTING_DEFAULT_GROUP);
			}
			Set<String> permissionByGroupId = permissionService.getPermissionByGroupId(group.getId());
			group.setPermissions(permissionByGroupId);
			cache.put(DEFAULT_GROUP, group);
		}
		return group;
	}

	@Override
	public void setDefaultGroup(Long groupId) {
		DzqGroup newGroup = baseMapper.selectById(groupId);
		if (newGroup == null) {
			throw new BaseException(GROUP_NOT_FOUND);
		}
		DzqGroup oldGroup = cache.get(DEFAULT_GROUP);
		oldGroup.setIsDefault(0);
		newGroup.setIsDefault(1);
		newGroup.setPermissions(getPermissionByGroupId(groupId));
		cache.put(DEFAULT_GROUP, newGroup);
		baseMapper.updateById(newGroup);
		baseMapper.updateById(oldGroup);
	}

	@Override
	public Set<String> getPermissionByGroupId(Long groupId) {
		return readGroup(groupId).getPermissions();
	}

	@Override
	public Set<String> getPermissionByUserId(Long userId) {
		Long groupId = groupUserService.getGroupIdByUserId(userId);
		return getPermissionByGroupId(groupId);
	}

	@Override
	public boolean editPermission(Long groupId, Set<String> permission, boolean isAdmin) {
		DzqGroup group = baseMapper.selectById(groupId);
		// 如果不是管理员就进行相关的检查
		if (!isAdmin) {
			if (group == null || groupId == ADMIN) {
				throw new BaseException(INVALID_PARAMETER);
			}
		}
		group.setPermissions(permission);
		cache.put(String.valueOf(groupId), group);

		permissionService.removePermissionByGroupId(groupId);
		List<GroupPermission> gps = new ArrayList<>();
		permission.forEach(p -> {
			GroupPermission g = new GroupPermission();
			g.setGroupId(groupId);
			g.setPermission(p);
			gps.add(g);
		});
		return permissionService.saveBatch(gps);
	}

	@Override
	public boolean editPermission(Long groupId, Set<String> permission) {
		return editPermission(groupId, permission, false);
	}

	@Override
	public void createGroup(DzqGroup group) {
		QueryWrapper<DzqGroup> wrapper = new QueryWrapper<>();
		wrapper.eq("name", group.getName());
		if (baseMapper.selectOne(wrapper) != null) {
			throw new BaseException(GROUPNAME_HAS_EXIST);
		}
		baseMapper.insert(group);
		cache.put(String.valueOf(group.getId()), group);
	}

	@Override
	public DzqGroup readGroup(Long groupId) {
		DzqGroup group = cache.get(String.valueOf(groupId));
		if (group == null) {
			group = baseMapper.selectById(groupId);
			if (group == null) {
				throw new BaseException(GROUP_NOT_FOUND);
			}
			Set<String> permissionByGroupId = permissionService.getPermissionByGroupId(group.getId());
			group.setPermissions(permissionByGroupId);
			cache.put(String.valueOf(groupId), group);
		}
		return group;
	}

	@Override
	public void deleteGroup(Long groupId) {
		if (baseMapper.selectById(groupId) == null) {
			throw new BaseException(GROUP_NOT_FOUND);
		}
		if (!groupUserService.getUserIdsByGroupId(groupId).isEmpty()) {
			throw new BaseException(GROUP_HAS_USER);
		}
		cache.remove(String.valueOf(groupId));
		// 删除用户组
		baseMapper.deleteById(groupId);
		// 删除用户组关联的权限
		permissionService.removePermissionByGroupId(groupId);
	}

}
