package com.aoexe.discuz.system.modular.group.service;

import java.util.Set;

import com.aoexe.discuz.system.modular.group.entity.GroupPermission;
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

	Set<String> getPermissionByGroupId(Long groupId);

	Integer removePermissionByGroupId(Long groupId);
}
