package cn.chenyuxian.discuz.system.modular.group.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.chenyuxian.discuz.system.core.cache.MappingCache;
import cn.chenyuxian.discuz.system.modular.group.entity.Group;
import cn.chenyuxian.discuz.system.modular.group.mapper.GroupMapper;
import cn.chenyuxian.discuz.system.modular.group.service.IGroupService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {

	@Autowired
	private GroupMapper groupMapper;

	@Autowired
	private MappingCache mappingCache;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getDefaultGroup() {
		if(mappingCache.get("DEFAULT_GROUP_") == null) {
			QueryWrapper<Group> wrapper = new QueryWrapper<>();
			String json = JSON.toJSONString(groupMapper.selectOne(wrapper));
			Map<String, Object> map = new HashMap<>();
			mappingCache.put("DEFAULT_GROUP_", JSON.parseObject(json, map.getClass()));
		}
		return mappingCache.get("DEFAULT_GROUP_");
	}

}
