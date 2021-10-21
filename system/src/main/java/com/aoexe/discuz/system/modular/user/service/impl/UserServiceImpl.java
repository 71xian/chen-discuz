package com.aoexe.discuz.system.modular.user.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.context.session.SessionContext;
import com.aoexe.discuz.core.util.BCryptPasswordEncoder;
import com.aoexe.discuz.core.util.BeanUtil;
import com.aoexe.discuz.system.core.cache.UserCache;
import com.aoexe.discuz.system.modular.group.service.IGroupsService;
import com.aoexe.discuz.system.modular.setting.service.ISettingsService;
import com.aoexe.discuz.system.modular.user.mapper.UserMapper;
import com.aoexe.discuz.system.modular.user.model.entity.User;
import com.aoexe.discuz.system.modular.user.model.enums.StatusMap;
import com.aoexe.discuz.system.modular.user.model.param.UserParam;
import com.aoexe.discuz.system.modular.user.model.result.ExcelUser;
import com.aoexe.discuz.system.modular.user.model.result.UserResult;
import com.aoexe.discuz.system.modular.user.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private IGroupsService groupService;

	@Autowired
	private ISettingsService settingsService;

	@Autowired
	private UserCache userCache;

	@Override
	public int removeByUserIds(Collection<Long> ids) {
		LambdaQueryChainWrapper<User> lambdaQuery = this.lambdaQuery().in(User::getId, ids);
		this.remove(lambdaQuery);
		return baseMapper.removeByIds(ids);
	}

	@Override
	public User updateAvatar(Long userId, String avatarUrl) {
		User user = baseMapper.selectById(userId);
		user.setAvatar(avatarUrl);
		baseMapper.updateById(user);
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery(User.class);
		lambdaQuery.eq(User::getUsername, username);
		return this.getOne(lambdaQuery);
	}

	@Override
	public UserResult updateUser(Long userId, UserParam param) {
		User user = baseMapper.selectById(userId);
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
		user.setUpdatedAt(LocalDateTime.now());
		updateById(user);
		updateUserCache(user);
		UserResult result = buildResult(user);
		if (user.getUsernameBout() < settingsService.getUsernameChanges()) {
		}
		return result;
	}

	@Override
	public UserResult buildResult(User user) {
		UserResult result = new UserResult();
		BeanUtil.copyProperties(user, result);
		return result;
	}

	@Override
	public List<ExcelUser> buildExcelUser(Collection<Long> ids) {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery(User.class);
		lambdaQuery.in(User::getId, ids);
		List<User> users = this.list(lambdaQuery);
		List<ExcelUser> excelUsers = new ArrayList<>();
		users.forEach(u -> {
			ExcelUser e = new ExcelUser();
			e.setUserId(u.getId());
			e.setUsername(u.getUsername());
			e.setStatus(StatusMap.get(u.getStatus()));
			e.setGroupName(String.join(",", groupService.getNamesByUserId(u.getId())));
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

	@Override
	public IPage<User> search(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 只适用于更新自己，不能更新其他用户
	 * 
	 * @author chenyuxian
	 * @date 2021-09-07 00:01:05
	 * @param user
	 */
	private void updateUserCache(User user) {
		userCache.hset(user.getId().toString(), SessionContext.get(), user);
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

}
