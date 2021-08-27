package com.aoexe.discuz.system.modular.user.service;

import com.aoexe.discuz.core.base.token.Token;
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

	/**
	 * 用户注册
	 *
	 * @param param
	 * @return   登陆令牌
	 * @author chenyuxian
	 * @date 2021-08-27
	 */
	Token register(UserParam param);

	/**
	 * 用户登录
	 *
	 * @param param
	 * @return token令牌
	 * @author chenyuxian
	 * @date 2021-08-27
	 */
	Token login(UserParam param);
	
	/**
	 * 刷新token
	 *
	 * @param accessToken
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-27
	 */
	Token refresh(String accessToken);
	
	/**
	 * 批量删除用户
	 *
	 * @param ids
	 * @author chenyuxian
	 * @date 2021-08-27
	 */
	void deleteBatchUser(Long[] ids);
}
