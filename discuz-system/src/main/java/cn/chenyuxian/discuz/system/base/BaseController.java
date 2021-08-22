package cn.chenyuxian.discuz.system.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.chenyuxian.discuz.system.modular.user.service.IUserService;

/**
 * Controller基类
 * @author chenyuxian
 * @date 2021-08-02
 */
@Component
public class BaseController {

	@Autowired
	private IUserService userService;
	
}
