package cn.chenyuxian.discuz.system.modular.auth.service;

import cn.chenyuxian.discuz.system.modular.user.entity.User;

public interface AuthService {

	User login(String username);
}
