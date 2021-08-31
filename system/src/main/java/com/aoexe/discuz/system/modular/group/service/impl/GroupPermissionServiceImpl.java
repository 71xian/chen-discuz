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
import com.aoexe.discuz.system.modular.group.entity.GroupPermission;
import com.aoexe.discuz.system.modular.group.mapper.GroupPermissionMapper;
import com.aoexe.discuz.system.modular.group.service.IGroupPermissionService;
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

	@Resource
	private GroupPermissionMapper mapper;

	@Override
	public Set<String> getPermissionsByGroupId(Long groupId) {
		return mapper.findByColumn("group_id", groupId);
	}

	@Override
	public Set<String> getPermissionsByGroupIds(Long[] groupIds) {
		return mapper.findByColumns("group_id", arrayToStr(groupIds));
	}

	@Override
	public boolean removeByGroupId(Long groupId) {
		return mapper.removeByColumn("group_id", groupId);
	}

	@Override
	public boolean removeByGroupIds(Long[] groupIds) {
		return mapper.removeByColumns("group_id", arrayToStr(groupIds));
	}

	private String arrayToStr(Long[] values) {
		if (values.length == 0) {
			throw new BaseException(ResponseEnum.DB_ERROR);
		}
		Set<Long> set = new HashSet<>();
		Collections.addAll(set, values);
		String sql = set.stream().map(s -> s.toString()).collect(Collectors.joining(","));
		return "(" + sql + ")";
	}

	@Override
	public void insertByGroupId(Long groupId, Set<String> permissions) {
		String sql = permissions.stream().map(p -> "(" + groupId.toString() + ",'" + p + "')")
				.collect(Collectors.joining(","));
		mapper.insertByGroupId(groupId, sql);
	}

}
