package com.aoexe.discuz.system.modular.group.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aoexe.discuz.system.core.cache.GroupCache;
import com.aoexe.discuz.system.modular.group.entity.GroupPermission;
import com.aoexe.discuz.system.modular.group.mapper.GroupPermissionMapper;
import com.aoexe.discuz.system.modular.group.service.IGroupPermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
@Service
public class GroupPermissionServiceImpl extends ServiceImpl<GroupPermissionMapper, GroupPermission> implements IGroupPermissionService {
	
	@Resource
	private GroupCache cache;
	
	@Override
	public List<String> getPermissionByGroupId(Long groupId) {
		QueryWrapper<GroupPermission> wrapper = new QueryWrapper<>();
		wrapper.eq("group_id", groupId);
		List<GroupPermission> list = baseMapper.selectList(wrapper);
		return list.stream().map(GroupPermission::getPermission).collect(Collectors.toList());
	}

	@Override
	public Integer removePermissionByGroupId(Long groupId) {
		QueryWrapper<GroupPermission> wrapper = new QueryWrapper<>();
		wrapper.eq("group_id", groupId);
		return baseMapper.delete(wrapper);
	}

}
