package com.aoexe.discuz.system.modular.group.service;

import com.aoexe.discuz.system.modular.group.model.entity.GroupPaidUser;
import com.aoexe.discuz.system.modular.group.model.result.GroupPaidResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-11
 */
public interface IGroupPaidUserService extends IService<GroupPaidUser> {

	IPage<GroupPaidResult> selectPage(Wrapper<GroupPaidResult> wrapper, Page<GroupPaidResult> pages, String include);
}
