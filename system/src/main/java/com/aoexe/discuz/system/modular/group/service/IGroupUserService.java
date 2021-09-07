package com.aoexe.discuz.system.modular.group.service;

import java.util.List;
import java.util.Set;

import com.aoexe.discuz.system.modular.group.entity.GroupUser;
import com.aoexe.discuz.system.modular.group.vo.GroupUserResult;
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

	Set<Long> getUserIdsByGroupId(Long groupId);
	
	Long getGroupIdByUserId(Long userId);
	
	boolean removeByGroupId(Long groupId);
	
	boolean removeByUserId(Long userId);
	
	boolean removeByUserIds(List<Long> userIds);
	
	boolean removeByGroupIds(List<Long> groupIds);
	
	List<GroupUserResult> updateGroup(List<GroupUser> groupUsers);
}
