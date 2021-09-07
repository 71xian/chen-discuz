package com.aoexe.discuz.system.modular.group.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.system.core.cache.GroupCache;
import com.aoexe.discuz.system.modular.group.consts.Constant;
import com.aoexe.discuz.system.modular.group.entity.DzqGroup;
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
	private IGroupPermissionService groupPermissionService;

	@Resource
	private IGroupUserService groupUserService;

	@Override
	public DzqGroup getDefaultGroup() {
		DzqGroup group = cache.get(Constant.DEFAULT_GROUP);
		if (group == null) {
			QueryWrapper<DzqGroup> wrapper = new QueryWrapper<>();
			wrapper.eq("is_default", 1);
			group = getOne(wrapper);
			Set<String> permissions = groupPermissionService.getPermissionsByGroupId(group.getId());
			group.setPermissions(permissions);
			cache.put(Constant.DEFAULT_GROUP, group);
		}
		return group;
	}

	@Override
	public void setDefaultGroup(Long groupId) {
		if (groupId == 1L) {
			throw new BaseException(ResponseEnum.UNAUTHORIZED);
		}
		DzqGroup newGroup = cache.get(groupId.toString());
		if (newGroup == null) {
			throw new BaseException(ResponseEnum.RESOURCE_NOT_FOUND);
		}
		newGroup.setPermissions(groupPermissionService.getPermissionsByGroupId(groupId));
		DzqGroup oldGroup = cache.get(Constant.DEFAULT_GROUP);
		oldGroup.setIsDefault(0);
		newGroup.setIsDefault(1);
		updateById(oldGroup);
		updateById(newGroup);
		cache.put(Constant.DEFAULT_GROUP, newGroup);
	}
	
	@Override
	public Set<String> getPermissionsByUserId(Long userId) {
		Long groupId = groupUserService.getGroupIdByUserId(userId);
		return getPermissionsByGroupId(groupId);
	}

	@Override
	public void resetPermissions(Long groupId, Set<String> permissions) {
		DzqGroup group = getById(groupId);
		if (group == null) {
			throw new BaseException(ResponseEnum.RESOURCE_NOT_FOUND);
		}
		group.setPermissions(permissions);
		cache.put(groupId.toString(), group);
		groupPermissionService.removeByGroupId(groupId);
		groupPermissionService.insertByGroupId(groupId, permissions);
	}

	@Override
	public void create(String name) {
		QueryWrapper<DzqGroup> wrapper = new QueryWrapper<>();
		wrapper.eq("name", name);
		DzqGroup group = getOne(wrapper);
		if (group != null) {
			throw new BaseException(ResponseEnum.RESOURCE_EXIST);
		}
		group = new DzqGroup();
		group.setName(name);
		this.save(group);
		cache.put(group.getId().toString(), group);
	}

	@Override
	public DzqGroup getGroupById(Long groupId) {
		DzqGroup group = cache.get(groupId.toString());
		if (group == null) {
			group = getById(groupId);
			if (group == null) {
				throw new BaseException(ResponseEnum.RESOURCE_NOT_FOUND);
			}
			group.setPermissions(groupPermissionService.getPermissionsByGroupId(groupId));
			cache.put(groupId.toString(), group);
		}
		return group;
	}

	@Override
	public boolean remove(Long groupId) {
		if (groupId == 1L) {
			throw new BaseException(ResponseEnum.UNAUTHORIZED);
		}
		if (!groupUserService.getUserIdsByGroupId(groupId).isEmpty()) {
			throw new BaseException(ResponseEnum.GROUP_HAS_USER);
		}
		groupPermissionService.removeByGroupId(groupId);
		boolean result = removeById(groupId);
		cache.remove(groupId.toString());
		return result;
	}

	@Override
	public boolean removes(Long[] groupIds) {
		for (Long id : groupIds) {
			boolean result = remove(id);
			if (!result)
				return false;
		}
		return true;
	}

	@Override
	public Set<String> getPermissionsByGroupId(Long groupId) {
		DzqGroup group = cache.get(groupId.toString());
		if (group == null) {
			group = getGroupById(groupId);
			if (group == null) {
				throw new BaseException(ResponseEnum.RESOURCE_NOT_FOUND);
			}
			group.setPermissions(getPermissionsByGroupId(groupId));
			cache.put(groupId.toString(), group);
		}
		return group.getPermissions();
	}

}
