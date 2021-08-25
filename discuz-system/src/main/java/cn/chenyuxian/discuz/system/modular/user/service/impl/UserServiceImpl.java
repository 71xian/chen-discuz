package cn.chenyuxian.discuz.system.modular.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.chenyuxian.discuz.system.modular.group.entity.Group;
import cn.chenyuxian.discuz.system.modular.user.entity.User;
import cn.chenyuxian.discuz.system.modular.user.mapper.UserMapper;
import cn.chenyuxian.discuz.system.modular.user.service.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserByUsername(String username) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", username);
		return userMapper.selectOne(wrapper);
	}

	@Override
	public Group getGroupByUserId(Long userId) {
		return baseMapper.selectGroupByUserId(userId);
	}

}
