package cn.chenyuxian.discuz.system.modular.auth.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.chenyuxian.discuz.security.auth.SecurityUser;
import cn.chenyuxian.discuz.security.auth.UsernamePasswordService;
import cn.chenyuxian.discuz.system.modular.user.entity.User;
import cn.chenyuxian.discuz.system.modular.user.service.IUserService;

@Service
@Transactional(rollbackFor = Exception.class)
public class AuthSerivceImpl implements AuthService, UsernamePasswordService{

	@Resource
	private IUserService userService;
	
	@Override
	public SecurityUser loadUserByUsername(String username) {
		return null;
	}

	@Override
	public User login(String username) {
		
		return null;
	}

}
