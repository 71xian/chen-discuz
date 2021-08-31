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
	
	boolean insertUser(UserParam param);
	
	boolean removeByUserIds(Long[] userIds);
	
}
