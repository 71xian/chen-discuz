package com.aoexe.discuz.system.modular.user.entity;

import java.util.Set;

import com.aoexe.discuz.core.context.login.LoginUser;
import com.aoexe.discuz.core.util.SpringUtil;
import com.aoexe.discuz.system.core.cache.GroupCache;

/**
 * 游客
 *
 * @author chenyuxian
 * @date 2021-09-02
 */
public class AnonymousUser implements LoginUser{

	@Override
	public Long getId() {
		return -1L;
	}

	@Override
	public String getUsername() {
		return "tourist";
	}

	@Override
	public Set<String> getPermissions() {
		GroupCache bean = SpringUtil.getBean(GroupCache.class);
		return bean.getTourist().getPermissions();
	}

}
