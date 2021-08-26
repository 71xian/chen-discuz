package cn.chenyuxian.discuz.system.modular.group.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.chenyuxian.discuz.system.modular.group.entity.DzqGroup;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
public interface IDzqGroupService extends IService<DzqGroup> {

	/**
	 * 获取默认用户组
	 *
	 * @return  默认用户组
	 * @author chenyuxian
	 * @date 2021-08-26
	 */
	DzqGroup getDefaultGroup();
	
	/**
	 * 设置默认用户组
	 *
	 * @author chenyuxian
	 * @date 2021-08-26
	 */
	void setDefaultGroup(Long groupId);
	
	/**
	 * 根据用户组id查询用户组权限
	 *
	 * @param groupId  用户组id
	 * @return         用户组权限集合
	 * @author chenyuxian
	 * @date 2021-08-26
	 */
	List<String> getPermissionByGroupId(Long groupId);
	
	/**
	 * 根据用户id查询用户组权限
	 *
	 * @param userId   用户id
	 * @return         当前用户所属用户组权限集合
	 * @author chenyuxian
	 * @date 2021-08-26
	 */
	List<String> getPermissionByUserId(Long userId);
	
	/**
	 * 管理员进行权限初始化
	 *
	 * @param groupId
	 * @param permission
	 * @param isAdmin
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-26
	 */
	boolean editPermission(Long groupId, List<String> permission, boolean isAdmin);
	
	/**
	 * 非管理员进行普通的编辑
	 *
	 * @param groupId
	 * @param permission
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-26
	 */
	boolean editPermission(Long groupId, List<String> permission);
	
	void createGroup(DzqGroup group);
	
	DzqGroup readGroup(Long groupId);
	
	void deleteGroup(Long groupId);
	
}
