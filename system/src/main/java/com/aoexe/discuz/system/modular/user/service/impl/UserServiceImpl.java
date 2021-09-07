package com.aoexe.discuz.system.modular.user.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.context.session.SessionContext;
import com.aoexe.discuz.core.util.BCryptPasswordEncoder;
import com.aoexe.discuz.system.core.cache.GroupCache;
import com.aoexe.discuz.system.core.cache.TokenCache;
import com.aoexe.discuz.system.core.cache.UserCache;
import com.aoexe.discuz.system.modular.group.service.IDzqGroupService;
import com.aoexe.discuz.system.modular.group.service.IGroupPermissionService;
import com.aoexe.discuz.system.modular.group.service.IGroupUserService;
import com.aoexe.discuz.system.modular.user.entity.ExcelUser;
import com.aoexe.discuz.system.modular.user.entity.User;
import com.aoexe.discuz.system.modular.user.extra.StatusMap;
import com.aoexe.discuz.system.modular.user.extra.UserParam;
import com.aoexe.discuz.system.modular.user.extra.UserResult;
import com.aoexe.discuz.system.modular.user.mapper.UserMapper;
import com.aoexe.discuz.system.modular.user.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
	private BCryptPasswordEncoder encoder;

	@Resource
	private IGroupUserService groupUserService;

	@Resource
	private IDzqGroupService groupService;

	@Resource
	private IGroupPermissionService groupPermissionService;

	@Resource
	private GroupCache groupCache;

	@Resource
	private UserCache userCache;

	@Resource
	private TokenCache tokenCache;

	@Override
	public User getUserByUsername(String username) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username", username);
		return getOne(wrapper);
	}

	@Override
	public boolean removeByUserIds(List<Long> userIds) {
		if (userIds.contains(1L)) {
			throw new BaseException(ResponseEnum.UNAUTHORIZED);
		}
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.in("id", userIds);
		// 未完善
		return remove(wrapper) && groupUserService.removeByUserIds(userIds);
	}

	@Override
	public User updateAvatar(Long userId, String avatarUrl) {
		User user = getById(userId);
		user.setAvatar(avatarUrl);
		Date now = new Date();
		user.setAvatarAt(now);
		user.setUpdatedAt(now);
		updateById(user);
		return user;
	}

	@Override
	public UserResult updateUser(Long userId, UserParam param) {
		User user = getById(userId);
		if (Objects.isNull(user)) {
			throw new BaseException(ResponseEnum.NOT_FOUND_USER);
		}
		updateUsername(user, param);
		updateNickname(user, param);
		updatePassword(user, param);
		updatePayPassword(user, param);
		// 用户签名未加入敏感词校验
		if (Objects.nonNull(param.getSignature())) {
			user.setSignature(param.getSignature());
		}
		if (Objects.nonNull(param.getStatus())) {
			user.setStatus(param.getStatus());
		}
		if (Objects.nonNull(param.getMobile())) {
			user.setMobile(param.getMobile());
		}
		user.setUpdatedAt(new Date());
		updateById(user);
		updateUserCache(user);
		UserResult result = new UserResult();
		result.setUser(user);
		if (user.getUsernameBout() < 5) {
			result.setCanEditUsername(true);
		}
		return result;
	}

	@Override
	public UserResult viewUser(Long userId) {
		UserResult result = new UserResult();
		result.setUser(getById(userId));
		result.setCanDelete(false);
		result.setCanEdit(true);
		result.setCanEditUsername(true);
		return result;
	}

	@Override
	public List<ExcelUser> buildExcelUser(Long[] ids) {
		List<User> users = null;
		List<ExcelUser> excelUsers = new ArrayList<>();
		if (Objects.isNull(ids) || ids.length == 0) {
			users = list();
		} else {
			QueryWrapper<User> wrapper = new QueryWrapper<>();
			wrapper.in("id", Arrays.asList(ids));
			users = list(wrapper);
		}
		users.forEach(u -> {
			ExcelUser e = new ExcelUser();
			e.setUserId(u.getId());
			e.setUsername(u.getUsername());
			e.setStatus(StatusMap.get(u.getStatus()));
			e.setGroupName(groupService.getById(groupUserService.getGroupIdByUserId(u.getId())).getName());
			e.setRegisterAt(u.getCreatedAt());
			e.setRegisterIp(u.getRegisterIp());
			e.setRegisterPort(u.getRegisterPort());
			e.setLoginAt(u.getLoginAt());
			e.setLastLoginPort(u.getLastLoginPort());
			e.setLastLoginPort(u.getLastLoginPort());
			excelUsers.add(e);
		});
		return excelUsers;
	}

	/**
	 * 只适用于更新自己，不能更新其他用户
	 * 
	 * @author chenyuxian
	 * @date 2021-09-07 00:01:05
	 * @param user
	 */
	private void updateUserCache(User user) {
		userCache.set(user.getId().toString(), SessionContext.get(), user);
	}

	private void updatePassword(User user, UserParam param) {
		// 密码为空
		if (Objects.isNull(param.getPassword())) {
			return;
		}
		// 验证旧密码失败
		if (!encoder.matches(user.getPassword(), user.getPassword())) {
			throw new BaseException(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
		}

		// 新旧密码不得相同
		if (encoder.matches(user.getPassword(), param.getNewPassword())) {
			throw new BaseException(ResponseEnum.USER_UPDATE_ERROR);
		}
		user.setPassword(encoder.encode(param.getPassword()));
	}

	private void updateUsername(User user, UserParam param) {
		// 用户名为空
		if (Objects.isNull(param.getUsername())) {
			return;
		}
		// 用户名重复
		if (Objects.nonNull(getUserByUsername(param.getUsername()))) {
			throw new BaseException(ResponseEnum.USERNAME_HAD_EXIST);
		}
		user.setUsername(param.getUsername());
		user.setUsernameBout(user.getUsernameBout() + 1);
	}

	private void updateNickname(User user, UserParam param) {
		// 昵称为空
		if (Objects.isNull(param.getUsername())) {
			return;
		}
		user.setNickname(param.getNickname());
	}

	private void updatePayPassword(User user, UserParam param) {
		if (Objects.nonNull(param.getPayPassword())) {
			// 新建支付密码
			if (user.getPayPassword().isEmpty()) {
				user.setPayPassword(param.getPayPassword());
			} else {
				// 修改支付密码
				user.setPayPassword(param.getPayPassword());
			}
		}
	}

	@Override
	public Page<User> search(HttpServletRequest request) {
		Page<User> page = new Page<>(1, 10);
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		
		return null;
	}
}
