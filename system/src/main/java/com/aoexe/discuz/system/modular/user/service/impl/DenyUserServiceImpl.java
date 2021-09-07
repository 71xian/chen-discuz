package com.aoexe.discuz.system.modular.user.service.impl;

import com.aoexe.discuz.core.util.RequestUtil;
import com.aoexe.discuz.system.core.util.TokenUtil;
import com.aoexe.discuz.system.modular.user.entity.DenyUser;
import com.aoexe.discuz.system.modular.user.mapper.DenyUserMapper;
import com.aoexe.discuz.system.modular.user.service.IDenyUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.jsonwebtoken.Claims;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-30
 */
@Service
public class DenyUserServiceImpl extends ServiceImpl<DenyUserMapper, DenyUser> implements IDenyUserService {

	@Resource
	private TokenUtil tokenUtil;
	
	@Override
	public DenyUser denyUser(Long userId) {
		String accessToken = tokenUtil.getAccessToken(RequestUtil.getRequest());
		Claims claims = tokenUtil.getClaims(accessToken);
		DenyUser denyUser = new DenyUser();
		denyUser.setCreatedAt(new Date());
		denyUser.setUserId(Long.valueOf(claims.getSubject()));
		denyUser.setDenyUserId(userId);
		return denyUser;
	}

	@Override
	public void removeDenyUser(Long userId) {
		QueryWrapper<DenyUser> wrapper = new QueryWrapper<>();
		String accessToken = tokenUtil.getAccessToken(RequestUtil.getRequest());
		Claims claims = tokenUtil.getClaims(accessToken);
		wrapper.eq("user_id", Long.valueOf(claims.getSubject()));
		wrapper.eq("deny_user_id", userId);
		remove(wrapper);
	}

	@Override
	public Page<DenyUser> denyUserList(Long userId) {
		QueryWrapper<DenyUser> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId);
		Page<DenyUser> page = new Page<>();
		Page<DenyUser> data = this.page(page, wrapper);
		System.out.println(data.isHitCount());
		return null;
	}

}
