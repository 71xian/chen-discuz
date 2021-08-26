package cn.chenyuxian.discuz.system.modular.group.service;

import cn.chenyuxian.discuz.system.modular.group.entity.GroupUser;

import java.util.List;

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
	
}
