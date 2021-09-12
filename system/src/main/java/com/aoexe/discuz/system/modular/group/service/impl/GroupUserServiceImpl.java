package com.aoexe.discuz.system.modular.group.service.impl;

import com.aoexe.discuz.system.modular.group.model.entity.GroupUser;
import com.aoexe.discuz.system.modular.group.mapper.GroupUserMapper;
import com.aoexe.discuz.system.modular.group.service.IGroupUserService;
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
public class GroupUserServiceImpl extends ServiceImpl<GroupUserMapper, GroupUser> implements IGroupUserService {

	@Override
	public void removeByGroupId(Long groupId) {
		LambdaQueryWrapper<GroupUser> lambdaQuery = Wrappers.lambdaQuery(GroupUser.class);
		lambdaQuery.eq(GroupUser::getGroupId, groupId);
		this.remove(lambdaQuery);
	}

	@Override
	public List<GroupUser> getByUserId(Long userId) {
		LambdaQueryWrapper<GroupUser> lambdaQuery = Wrappers.lambdaQuery(GroupUser.class);
		lambdaQuery.eq(GroupUser::getUserId, userId);
		lambdaQuery.select(GroupUser::getGroupId);
		return this.list(lambdaQuery);
	}

}
