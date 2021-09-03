package com.aoexe.discuz.system.modular.group.service.impl;

import java.util.HashSet;
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
	private DzqGroupMapper mapper;

	@Resource
	private IGroupPermissionService groupPermissionService;

	@Resource
	private IGroupUserService groupUserService;

	@Override
	public DzqGroup getDefaultGroup() {
		DzqGroup group = cache.get(Constant.DEFAULT_GROUP);
		if (group == null) {
			group = mapper.findByColumn("is_default", "1");
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
		Long oldGroupId = cache.get(Constant.DEFAULT_GROUP).getId();
		mapper.updateByGroupId(oldGroupId, "is_default", "0");
		mapper.updateByGroupId(groupId, "is_default", "1");
		cache.put(Constant.DEFAULT_GROUP, newGroup);
	}

	@Override
	public Set<String> getPermissionsByUserId(Long userId) {
		Set<Long> groupIds = groupUserService.getGroupIdsByUserId(userId);
		Set<String> permissions = new HashSet<>();
		groupIds.forEach(g -> {
			permissions.addAll(getPermissionsByGroupId(g));
		});
		return permissions;
	}

	@Override
	public void resetPermissions(Long groupId, Set<String> permissions) {
		DzqGroup group = mapper.findByColumn("id", groupId.toString());
		if (group == null) {
			throw new BaseException(ResponseEnum.RESOURCE_NOT_FOUND);
		}
		group.setPermissions(permissions);
		cache.put(groupId.toString(), group);
		groupPermissionService.removeByGroupId(groupId);
		// 此处有漏洞，待修改
		groupPermissionService.insertByGroupId(groupId, permissions);
	}

	@Override
	public void create(String name) {
		DzqGroup group = mapper.findByColumnStr("name", name);
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
			group = mapper.findByColumn("id", groupId.toString());
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
		boolean result = mapper.removeByColumn("id", groupId);
		if (result)
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
		if(group == null) {
			group = getGroupById(groupId);
			System.out.println(group);
			if(group == null) {
				throw new BaseException(ResponseEnum.RESOURCE_NOT_FOUND);
			}
			group.setPermissions(getPermissionsByGroupId(groupId));
			cache.put(groupId.toString(), group);
		}
		return group.getPermissions();
	}

}
