package cn.chenyuxian.discuz.system.modular.group.service;

import cn.chenyuxian.discuz.system.modular.group.entity.GroupPermission;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
public interface IGroupPermissionService extends IService<GroupPermission> {

	List<String> getPermissionByGroupId(Long groupId);

	Integer removePermissionByGroupId(Long groupId);
}
