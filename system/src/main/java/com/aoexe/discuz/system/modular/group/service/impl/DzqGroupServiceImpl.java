package com.aoexe.discuz.system.modular.group.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.util.RedisUtil;
import com.aoexe.discuz.system.modular.config.service.IConfigService;
import com.aoexe.discuz.system.modular.group.mapper.DzqGroupMapper;
import com.aoexe.discuz.system.modular.group.model.entity.DzqGroup;
import com.aoexe.discuz.system.modular.group.model.entity.GroupPermission;
import com.aoexe.discuz.system.modular.group.model.entity.GroupUser;
import com.aoexe.discuz.system.modular.group.model.param.GroupParam;
import com.aoexe.discuz.system.modular.group.model.result.GroupResult;
import com.aoexe.discuz.system.modular.group.service.IDzqGroupService;
import com.aoexe.discuz.system.modular.group.service.IGroupPermissionService;
import com.aoexe.discuz.system.modular.group.service.IGroupUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DzqGroupServiceImpl extends ServiceImpl<DzqGroupMapper, DzqGroup> implements IDzqGroupService {

	private static final String GROUP_PREFIX = "GROUP:";

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private IConfigService configService;

	@Autowired
	private IGroupUserService groupUserService;

	@Autowired
	private IGroupPermissionService groupPermissionService;

	@Override
	public DzqGroup create(GroupParam dto) {
		if(lambdaQuery().eq(DzqGroup::getName, dto.getName()).one() != null) {
			throw new BaseException(ResponseEnum.GROUP_HAS_EXIST);			
		}
		DzqGroup group = new DzqGroup();
		BeanUtils.copyProperties(dto, group);
		this.save(group);
		return group;
	}

	@Override
	public void createGroupUser(Long groupId, Long userId) {
		GroupUser groupUser = new GroupUser();
		groupUser.setGroupId(groupId);
		groupUser.setUserId(userId);
		groupUserService.saveOrUpdate(groupUser);
	}

	@Override
	public void createGroupUsers(Long groupId, List<Long> userIds) {
		List<GroupUser> groupUsers = new ArrayList<>(userIds.size());
		userIds.forEach(userId -> {
			GroupUser groupUser = new GroupUser();
			groupUser.setGroupId(groupId);
			groupUser.setUserId(userId);
			groupUsers.add(groupUser);
		});
		groupUserService.saveOrUpdateBatch(groupUsers);
	}

	@Override
	public void remove(Long groupId) {
		if (groupId == 1L || groupId == 6L || groupId == 10L) {
			throw new BaseException(ResponseEnum.UNAUTHORIZED);
		}
		this.removeById(groupId);
		groupUserService.removeByGroupId(groupId);
		groupPermissionService.removeByGroupId(groupId);
	}

	@Override
	public DzqGroup update(Long groupId, GroupParam dto) {
		DzqGroup group = this.getById(groupId);
		BeanUtils.copyProperties(dto, group);
		this.updateById(group);
		return group;
	}

	@Override
	public DzqGroup updateDefault(Long groupId) {
		DzqGroup group = this.getById(groupId);
		DzqGroup defaultGroup = this.getById(Long.valueOf(configService.getValueByKey("group_id")));
		group.setIsDefault(1);
		defaultGroup.setIsDefault(0);
		this.updateById(defaultGroup);
		this.updateById(group);
		configService.updateByKey("group_id", groupId.toString());
		return group;
	}

	@Override
	public DzqGroup updateIcon(Long groupId, String icon) {
		DzqGroup group = this.getById(groupId);
		group.setIcon(icon);
		this.updateById(group);
		return group;
	}

	@Override
	public void editPermissions(Long groupId, List<String> permissions) {
		System.out.println(permissions.size());
		if(permissions.isEmpty()) {
			return;
		}
		String key = GROUP_PREFIX + groupId.toString();
		redisUtil.remove(key);
		redisUtil.leftPush(key, permissions);
		groupPermissionService.removeByGroupId(groupId);
		List<GroupPermission> groupPermissions = new ArrayList<>(permissions.size());
		permissions.forEach(p -> {
			GroupPermission groupPermission = new GroupPermission();
			groupPermission.setGroupId(groupId);
			groupPermission.setPermission(p);
			groupPermissions.add(groupPermission);
		});
		groupPermissionService.saveBatch(groupPermissions);
	}

	@Override
	public GroupResult getGroupVO(Long groupId, boolean isInclude) {
		GroupResult vo = new GroupResult();
		BeanUtils.copyProperties(this.getById(groupId), vo);
		if (isInclude) {
			vo.setPermissions(groupPermissionService.getByGroupId(groupId));
		}
		return vo;
	}

	@Override
	public List<String> getPermissionsByGroupId(Long groupId) {
		String key = GROUP_PREFIX + groupId.toString();
		List<String> permissions = redisUtil.leftAll(key);
		if (Objects.isNull(permissions)) {
			List<GroupPermission> groupPermissions = groupPermissionService.getByGroupId(groupId);
			permissions = groupPermissions.stream().map(GroupPermission::getPermission).collect(Collectors.toList());
			redisUtil.leftPush(key, permissions);
		}
		return permissions;
	}

	@Override
	public List<String> getPermissionsByUserId(Long userId) {
		List<GroupUser> groupUsers = groupUserService.getByUserId(userId);
		List<String> permissions = new ArrayList<>();
		groupUsers.forEach(g -> {
			Long groupId = g.getGroupId();
			permissions.addAll(getPermissionsByGroupId(groupId));
		});
		return permissions;
	}

	@Override
	public List<String> getNamesByUserId(Long userId) {
		List<GroupUser> groupUsers = groupUserService.getByUserId(userId);
		List<String> names = new ArrayList<>(groupUsers.size());
		LambdaQueryWrapper<DzqGroup> lambdaQuery = Wrappers.lambdaQuery(DzqGroup.class);
		lambdaQuery.select(DzqGroup::getName);
		groupUsers.forEach(g -> {
			lambdaQuery.eq(DzqGroup::getId, g.getGroupId());
			names.add(this.getOne(lambdaQuery).getName());
		});
		return names;
	}

	@Override
	public IPage<GroupResult> selectPage(Wrapper<GroupResult> wrapper, Page<GroupResult> page, boolean isInclude) {
		IPage<GroupResult> pages = baseMapper.selectPage(page, wrapper);
		if (isInclude) {
			pages.getRecords().forEach(vo -> vo.setPermissions(groupPermissionService.getByGroupId(vo.getId())));
		}
		return pages;
	}

}
