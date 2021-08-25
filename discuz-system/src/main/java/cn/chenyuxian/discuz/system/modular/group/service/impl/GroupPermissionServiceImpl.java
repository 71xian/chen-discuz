package cn.chenyuxian.discuz.system.modular.group.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.chenyuxian.discuz.system.core.cache.PermissionCache;
import cn.chenyuxian.discuz.system.modular.group.entity.GroupPermission;
import cn.chenyuxian.discuz.system.modular.group.mapper.GroupPermissionMapper;
import cn.chenyuxian.discuz.system.modular.group.service.IGroupPermissionService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-23
 */
@Service
public class GroupPermissionServiceImpl extends ServiceImpl<GroupPermissionMapper, GroupPermission> implements IGroupPermissionService {
	
	@Autowired
	private GroupPermissionMapper mapper; 
	
	@Autowired
	private PermissionCache cache;
	
	@Override
	public List<String> getPermissionByGroupId(Long groupId) {
		List<String> resources = cache.getPermissions(groupId);
		if(resources == null) {
			resources = new ArrayList<>();
			QueryWrapper<GroupPermission> wrapper = new QueryWrapper<>();
			List<GroupPermission> list = mapper.selectList(wrapper);
			resources.addAll(list.stream().map(GroupPermission::getPermission).collect(Collectors.toList()));
			cache.putPermissions(groupId, resources);
		}
		return resources;
	}


}
