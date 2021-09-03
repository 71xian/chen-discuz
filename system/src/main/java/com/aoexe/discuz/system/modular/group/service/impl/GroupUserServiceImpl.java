package com.aoexe.discuz.system.modular.group.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.system.modular.group.entity.GroupUser;
import com.aoexe.discuz.system.modular.group.mapper.GroupUserMapper;
import com.aoexe.discuz.system.modular.group.service.IGroupUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class GroupUserServiceImpl extends ServiceImpl<GroupUserMapper, GroupUser> implements IGroupUserService {

	@Resource
	private GroupUserMapper mapper;

	@Override
	public Set<Long> getGroupIdsByUserId(Long userId) {
		return mapper.findByColumn("group_id", "user_id", userId);
	}

	@Override
	public Set<Long> getUserIdsByGroupId(Long groupId) {
		return mapper.findByColumn("user_id", "group_id", groupId);
	}

	@Override
	public boolean removeByGroupId(Long groupId) {
		return mapper.removeByColumn("group_id", groupId);
	}

	@Override
	public boolean removeByUserId(Long userId) {
		return mapper.removeByColumn("user_id", userId);
	}

	@Override
	public boolean removeByUserIds(Long[] userIds) {
		return mapper.removeByColumns("user_id", arrayToStr(userIds));
	}

	@Override
	public boolean removeByGroupIds(Long[] groupIds) {
		return mapper.removeByColumns("group_id", arrayToStr(groupIds));
	}
	
	private String arrayToStr(Long[] values){
		if(values.length == 0) {
			log.error(">>>传入的数组长度为0");
			throw new BaseException(ResponseEnum.DB_ERROR);
		}
		Set<Long> set = new HashSet<>();
		Collections.addAll(set, values);
		String str = set.stream().map(s -> s.toString()).collect(Collectors.joining(","));
		return "(" + str + ")";
	}

}
