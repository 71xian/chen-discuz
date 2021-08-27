package com.aoexe.discuz.system.modular.user.service.impl;

import static com.aoexe.discuz.core.constant.ErrorCode.USERNAME_OR_PASSWORD_ERROR;
import static com.aoexe.discuz.core.constant.ErrorCode.USER_BAN;
import static com.aoexe.discuz.core.constant.ErrorCode.USER_IN_REVIEW;
import static com.aoexe.discuz.core.constant.ErrorCode.USER_NEED_SIGNIN_FIELDS;
import static com.aoexe.discuz.core.constant.ErrorCode.VALIDATE_IGNORE;
import static com.aoexe.discuz.core.constant.ErrorCode.VALIDATE_REJECT;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.BAN;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.IGNORE;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.MOD;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.NEED_FIELDS;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.REFUSE;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aoexe.discuz.core.base.crypto.PasswordEncoder;
import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.base.token.Token;
import com.aoexe.discuz.core.constant.ErrorCode;
import com.aoexe.discuz.core.context.security.SecurityContextHolder;
import com.aoexe.discuz.core.context.security.SecurityContextImpl;
import com.aoexe.discuz.core.util.IpUtil;
import com.aoexe.discuz.core.util.RequestUtil;
import com.aoexe.discuz.core.util.TokenUtil;
import com.aoexe.discuz.system.modular.group.entity.GroupUser;
import com.aoexe.discuz.system.modular.group.service.IDzqGroupService;
import com.aoexe.discuz.system.modular.group.service.IGroupUserService;
import com.aoexe.discuz.system.modular.user.entity.User;
import com.aoexe.discuz.system.modular.user.mapper.UserMapper;
import com.aoexe.discuz.system.modular.user.param.UserParam;
import com.aoexe.discuz.system.modular.user.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Resource
	private PasswordEncoder passwordEncoder;

	@Resource
	private IGroupUserService groupUserService;

	@Resource
	private IDzqGroupService groupService;

	@Override
	public Token register(UserParam param) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", param.getUsername());
		User user = baseMapper.selectOne(wrapper);
		if (user != null) {
			throw new BaseException(ErrorCode.USERNAME_HAD_EXIST);
		}
		user = new User();
		HttpServletRequest request = RequestUtil.getRequest();
		String ip = IpUtil.getIp(request);
		Integer port = request.getRemotePort();
		user.setRegisterIp(ip);
		user.setRegisterPort(port);
		user.setRegisterReason("用户密码注册");
		user.setUsername(param.getUsername());
		user.setPassword(passwordEncoder.encode(param.getPassword()));
		user.setNickname(param.getNickname());
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		baseMapper.insert(user);
		GroupUser groupUser = new GroupUser();
		groupUser.setGroupId(groupService.getDefaultGroup().getId());
		groupUser.setUserId(user.getId());
		groupUserService.save(groupUser);
		user.setRole(groupService.getDefaultGroup().getName());
		SecurityContextHolder.setContext(new SecurityContextImpl(user));
		Token token = TokenUtil.generateToken(user.getUsername());
		
		return token;
	}

	@Override
	public Token login(UserParam param) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", param.getUsername());
		User user = baseMapper.selectOne(wrapper);
		if (user != null && passwordEncoder.matches(param.getPassword(), user.getPassword())) {
			HttpServletRequest request = RequestUtil.getRequest();
			String ip = IpUtil.getIp(request);
			Integer port = request.getRemotePort();
			user.setLoginAt(LocalDateTime.now());
			user.setLastLoginIp(ip);
			user.setLastLoginPort(port);
			baseMapper.updateById(user);
			switch (user.getStatus()) {
			case BAN:
				throw new BaseException(USER_BAN);
			case MOD:
				throw new BaseException(USER_IN_REVIEW);
			case REFUSE:
				throw new BaseException(VALIDATE_REJECT);
			case IGNORE:
				throw new BaseException(VALIDATE_IGNORE);
			case NEED_FIELDS:
				throw new BaseException(USER_NEED_SIGNIN_FIELDS);
			}
			List<String> permissions = groupService.getPermissionByUserId(user.getId());
			user.setPermissions(permissions);
			user.setPassword(null);
			SecurityContextHolder.setContext(new SecurityContextImpl(user));
			return TokenUtil.generateToken(user.getUsername());
		} else {
			throw new BaseException(USERNAME_OR_PASSWORD_ERROR);
		}
	}

	@Override
	public Token refresh(String accessToken) {
		
		return null;
	}

	@Override
	public void deleteBatchUser(Long[] ids) {
		
	}

}
