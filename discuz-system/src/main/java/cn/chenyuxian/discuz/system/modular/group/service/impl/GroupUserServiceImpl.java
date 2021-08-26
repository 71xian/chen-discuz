package cn.chenyuxian.discuz.system.modular.group.service.impl;

import cn.chenyuxian.discuz.core.base.exception.BaseException;
import cn.chenyuxian.discuz.core.constant.ErrorCode;
import cn.chenyuxian.discuz.system.modular.group.entity.GroupUser;
import cn.chenyuxian.discuz.system.modular.group.mapper.GroupUserMapper;
import cn.chenyuxian.discuz.system.modular.group.service.IGroupUserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
@Service
public class GroupUserServiceImpl extends ServiceImpl<GroupUserMapper, GroupUser> implements IGroupUserService {

	@Override
	public Long getGroupIdByUserId(Long userId) {
		QueryWrapper<GroupUser> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId);
		GroupUser user = baseMapper.selectOne(wrapper);
		if(user == null) {
			throw new BaseException(ErrorCode.NOT_FOUND_USER);
		}
		return user.getGroupId();
	}

	@Override
	public List<Long> getUserIdsByGroupId(Long groupId) {
		QueryWrapper<GroupUser> wrapper = new QueryWrapper<>();
		wrapper.eq("group_id", groupId);
		List<GroupUser> groupUsers = baseMapper.selectList(wrapper);
		return groupUsers.stream().map(GroupUser::getUserId).collect(Collectors.toList());
	}
}
