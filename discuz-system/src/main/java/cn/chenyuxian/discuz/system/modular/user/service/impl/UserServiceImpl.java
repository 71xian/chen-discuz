package cn.chenyuxian.discuz.system.modular.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

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
	
	@Override
	public User getUserByUsername(String username) {
		return baseMapper.selectUserByUsername(username);
	}

}
