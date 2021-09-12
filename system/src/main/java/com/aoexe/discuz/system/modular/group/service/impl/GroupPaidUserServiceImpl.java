package com.aoexe.discuz.system.modular.group.service.impl;

import com.aoexe.discuz.system.modular.group.model.entity.GroupPaidUser;
import com.aoexe.discuz.system.modular.group.model.result.GroupPaidResult;
import com.aoexe.discuz.system.modular.group.mapper.GroupPaidUserMapper;
import com.aoexe.discuz.system.modular.group.service.IGroupPaidUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-11
 */
@Service
public class GroupPaidUserServiceImpl extends ServiceImpl<GroupPaidUserMapper, GroupPaidUser> implements IGroupPaidUserService {

	@Override
	public IPage<GroupPaidResult> selectPage(Wrapper<GroupPaidResult> wrapper, Page<GroupPaidResult> pages,
			String include) {
		// TODO Auto-generated method stub
		return null;
	}

}
