package com.aoexe.discuz.system.modular.user.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.aoexe.discuz.core.context.login.LoginContext;
import com.aoexe.discuz.system.modular.user.mapper.DenyUserMapper;
import com.aoexe.discuz.system.modular.user.model.entity.DenyUser;
import com.aoexe.discuz.system.modular.user.model.result.DenyUserResult;
import com.aoexe.discuz.system.modular.user.service.IDenyUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-11
 */
@Service
public class DenyUserServiceImpl extends ServiceImpl<DenyUserMapper, DenyUser> implements IDenyUserService {
	
	@Override
	public DenyUser denyUser(Long denyUserId) {
		DenyUser denyUser = new DenyUser();
		denyUser.setUserId(LoginContext.get().getId());
		denyUser.setDenyUserId(denyUserId);
		denyUser.setCreatedAt(LocalDateTime.now());
		this.save(denyUser);
		return denyUser;
	}

	@Override
	public void removeDenyUser(Long denyUserId) {
		LambdaQueryChainWrapper<DenyUser> wrapper = this.lambdaQuery()
				.eq(DenyUser::getUserId, LoginContext.get().getId())
				.eq(DenyUser::getDenyUserId, denyUserId);
		this.remove(wrapper);
	}

	@Override
	public IPage<DenyUserResult> selectPage(Page<DenyUserResult> pages) {
		QueryWrapper<DenyUserResult> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", LoginContext.get().getId());
		return baseMapper.selectPage(pages, wrapper);
	}

}
