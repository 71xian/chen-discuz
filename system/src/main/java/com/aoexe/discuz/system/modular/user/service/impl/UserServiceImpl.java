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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.util.IpUtil;
import com.aoexe.discuz.core.util.RequestUtil;
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
	private IGroupUserService groupUserService;

	@Resource
	private IDzqGroupService groupService;

	@Override
	public String register(UserParam param) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", param.getUsername());
		User user = baseMapper.selectOne(wrapper);
		if (user != null) {
			throw new BaseException(ResponseEnum.USERNAME_HAD_EXIST);
		}
		user = new User();
		HttpServletRequest request = RequestUtil.getRequest();
		String ip = IpUtil.getIp(request);
		Integer port = request.getRemotePort();
		user.setRegisterIp(ip);
		user.setRegisterPort(port);
		user.setRegisterReason("用户密码注册");
		user.setUsername(param.getUsername());
		user.setNickname(param.getNickname());
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		baseMapper.insert(user);
		GroupUser groupUser = new GroupUser();
		groupUser.setGroupId(groupService.getDefaultGroup().getId());
		groupUser.setUserId(user.getId());
		groupUserService.save(groupUser);
		user.setPassword(null);
		return "null";
	}

	@Override
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
	public String refresh(String accessToken) {

		return "";
	}

	@Override
	public void deleteBatchIds(Long[] ids) {
		List<Long> list = Arrays.asList(ids);
		list.forEach(id -> {
			if (id == 1L) {
				throw new BaseException(ResponseEnum.NOT_DELETE_ADMIN);
			}
		});
		List<String> str = list.stream().map(s -> s.toString()).collect(Collectors.toList());
		String[] strlist = new String[list.size()];
		str.toArray(strlist);
		baseMapper.deleteBatchIds(list);
		groupUserService.deleteUsers(list);
	}

	@Override
	public User getUserByUsername(String username) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", username);
		return this.getOne(wrapper);
	}

}
