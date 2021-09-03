package com.aoexe.discuz.system.modular.user.service;

import com.aoexe.discuz.system.modular.user.entity.User;
import com.aoexe.discuz.system.modular.user.param.UserParam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
public interface IUserService extends IService<User> {

	User getUserByUsername(String username);
	
	void removeByUserIds(Long[] userIds);
	
	String register(UserParam param);
	
	String login(UserParam param);
	
	void logout();
	
	User updateAvatar(User user, String avatarUrl);
	
	User deleteAvatar(User user);
	
	User editUser(Long userId, UserParam param);
	
}
