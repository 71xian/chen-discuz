package com.aoexe.discuz.system.modular.user.service;

import com.aoexe.discuz.system.modular.user.model.entity.DenyUser;
import com.aoexe.discuz.system.modular.user.model.result.DenyUserResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-11
 */
public interface IDenyUserService extends IService<DenyUser> {

	DenyUser denyUser(Long denyUserId);
	
	void removeDenyUser(Long denyUserId);
	
	IPage<DenyUserResult> selectPage(Page<DenyUserResult> pages);
}
