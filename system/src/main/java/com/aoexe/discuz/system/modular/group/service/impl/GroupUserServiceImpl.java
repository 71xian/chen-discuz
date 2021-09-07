package com.aoexe.discuz.system.modular.group.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aoexe.discuz.system.modular.group.entity.GroupUser;
import com.aoexe.discuz.system.modular.group.mapper.GroupUserMapper;
import com.aoexe.discuz.system.modular.group.service.IGroupUserService;
import com.aoexe.discuz.system.modular.group.vo.GroupUserResult;
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
public class GroupUserServiceImpl extends ServiceImpl<GroupUserMapper, GroupUser> implements IGroupUserService {
	
	@Override
	public Long getGroupIdByUserId(Long userId) {
		QueryWrapper<GroupUser> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId);
		return getOne(wrapper).getGroupId();
	}

	@Override
	public Set<Long> getUserIdsByGroupId(Long groupId) {
		QueryWrapper<GroupUser> wrapper = new QueryWrapper<>();
		wrapper.eq("group_id", groupId);
		return list(wrapper).stream().map(GroupUser::getGroupId).collect(Collectors.toSet());
	}

	@Override
	public boolean removeByGroupId(Long groupId) {
		QueryWrapper<GroupUser> wrapper = new QueryWrapper<>();
		wrapper.eq("group_id", groupId);
		return remove(wrapper);
	}

	@Override
	public boolean removeByUserId(Long userId) {
		QueryWrapper<GroupUser> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId);
		return remove(wrapper);
	}

	@Override
	public boolean removeByUserIds(List<Long> userIds) {
		QueryWrapper<GroupUser> wrapper = new QueryWrapper<>();
		wrapper.in("user_id", userIds);
		return remove(wrapper);
	}

	@Override
	public boolean removeByGroupIds(List<Long> groupIds) {
		QueryWrapper<GroupUser> wrapper = new QueryWrapper<>();
		wrapper.in("group_id", groupIds);
		return remove(wrapper);
	}

	@Override
	public List<GroupUserResult> updateGroup(List<GroupUser> groupUsers) {
		List<GroupUserResult> results = new ArrayList<>();
		groupUsers.forEach(g -> {
			GroupUserResult result = new GroupUserResult();
			result.setGroupId(g.getGroupId());
			result.setUserId(g.getUserId());
			if(!saveOrUpdate(g)) {
				result.setSucceed(false);
				result.setError("没有权限");
			}else {
				result.setSucceed(true);
			}
			results.add(result);
		});
		return results;
	}
}
