package com.aoexe.discuz.system.modular.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.aoexe.discuz.system.modular.user.entity.ExcelUser;
import com.aoexe.discuz.system.modular.user.entity.User;
import com.aoexe.discuz.system.modular.user.extra.UserParam;
import com.aoexe.discuz.system.modular.user.extra.UserResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
public interface IUserService extends IService<User> {

	User getUserByUsername(String username);

	boolean removeByUserIds(List<Long> userIds);

	User updateAvatar(Long userId, String avatarUrl);
	
	UserResult updateUser(Long userId, UserParam param);
	
	UserResult viewUser(Long userId);
	
	List<ExcelUser> buildExcelUser(Long[] ids);
	
	Page<User> search(HttpServletRequest request);
}
