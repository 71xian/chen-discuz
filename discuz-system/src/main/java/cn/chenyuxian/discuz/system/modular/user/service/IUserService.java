package cn.chenyuxian.discuz.system.modular.user.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.chenyuxian.discuz.core.base.token.Token;
import cn.chenyuxian.discuz.system.modular.user.entity.User;
import cn.chenyuxian.discuz.system.modular.user.param.UserParam;

/**
 * <p>
 *  服务类
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
	 * @author chenyuxian
	 * @date 2021-08-26
	 */
	void register(UserParam param);
	
	/**
	 * 用户登录
	 *
	 * @param param
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-26
	 */
	Token login(UserParam param);
}
