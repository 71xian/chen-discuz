package com.aoexe.discuz.system.modular.user.service;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.aoexe.discuz.system.modular.user.model.entity.User;
import com.aoexe.discuz.system.modular.user.model.param.UserParam;
import com.aoexe.discuz.system.modular.user.model.result.ExcelUser;
import com.aoexe.discuz.system.modular.user.model.result.UserResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-10
 */
public interface IUserService extends IService<User> {

	int removeByUserIds(Collection<Long> ids);

	User updateAvatar(Long userId, String avatarUrl);

	User getUserByUsername(String username);

	UserResult updateUser(Long userId, UserParam param);

	UserResult buildResult(User user);

	List<ExcelUser> buildExcelUser(Collection<Long> ids);

	IPage<User> search(HttpServletRequest request);
	
}
