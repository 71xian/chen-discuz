package com.aoexe.discuz.system.modular.user.service.impl;

import static com.aoexe.discuz.core.constant.ResponseEnum.USER_BAN;
import static com.aoexe.discuz.core.constant.ResponseEnum.USER_IN_REVIEW;
import static com.aoexe.discuz.core.constant.ResponseEnum.USER_NEED_SIGNIN_FIELDS;
import static com.aoexe.discuz.core.constant.ResponseEnum.VALIDATE_IGNORE;
import static com.aoexe.discuz.core.constant.ResponseEnum.VALIDATE_REJECT;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.BAN;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.IGNORE;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.MOD;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.NEED_FIELDS;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.REFUSE;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.util.IpUtil;
import com.aoexe.discuz.core.util.RequestUtil;
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
	private PasswordEncoder encoder;

	@Resource
	private UserMapper mapper;

	@Resource
	private IGroupUserService groupUserService;

	@Resource
	private IDzqGroupService groupService;

	public String login(UserParam param) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", param.getUsername());
		User user = baseMapper.selectOne(wrapper);
		if (user != null) {
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
		}
		user.setPassword(null);
		return "null";
	}

	@Override
	public User getUserByUsername(String username) {
		return mapper.findByColumnStr("username", username);
	}

	@Override
	public boolean insertUser(UserParam param) {
		HttpServletRequest request = RequestUtil.getRequest();
		param.setRegisterIp(IpUtil.getIp(request));
		param.setRegisterPort(request.getRemotePort());
		param.setRegisterReason("用户密码注册");
		param.setCreatedAt(LocalDateTime.now());
		param.setUpdatedAt(LocalDateTime.now());
		param.setPassword(encoder.encode(param.getPassword()));
		mapper.insertUser(param);
		return true;
	}

	@Override
	public boolean removeByUserIds(Long[] userIds) {
		for (Long id : userIds) {
			if (id == 1L)
				throw new BaseException(ResponseEnum.UNAUTHORIZED);
		}
		mapper.removeByColumns("id", arrayToStr(userIds));
		return groupUserService.removeByUserIds(userIds);
	}

	private String arrayToStr(Long[] values) {
		if (values.length == 0) {
			log.error(">>>传入的数组长度为0");
			throw new BaseException(ResponseEnum.DB_ERROR);
		}
		Set<Long> set = new HashSet<>();
		Collections.addAll(set, values);
		String str = set.stream().map(s -> s.toString()).collect(Collectors.joining(","));
		return "(" + str + ")";
	}

}
