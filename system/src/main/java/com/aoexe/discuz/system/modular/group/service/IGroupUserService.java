package com.aoexe.discuz.system.modular.group.service;

import java.util.List;

import com.aoexe.discuz.system.modular.group.entity.GroupUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
public interface IGroupUserService extends IService<GroupUser> {

	Long getGroupIdByUserId(Long userId);
	
	List<Long> getUserIdsByGroupId(Long groupId);
	
	void deleteUsers(List<Long> ids);
	
}
