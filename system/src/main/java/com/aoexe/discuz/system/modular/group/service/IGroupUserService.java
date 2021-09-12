package com.aoexe.discuz.system.modular.group.service;

import java.util.List;

import com.aoexe.discuz.system.modular.group.model.entity.GroupUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-11
 */
public interface IGroupUserService extends IService<GroupUser> {

	void removeByGroupId(Long groupId);
	
	List<GroupUser> getByUserId(Long userId);
}
