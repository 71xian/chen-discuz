package com.aoexe.discuz.system.modular.user.mapper;

import org.apache.ibatis.annotations.Param;

import com.aoexe.discuz.system.modular.user.model.entity.DenyUser;
import com.aoexe.discuz.system.modular.user.model.result.DenyUserResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-11
 */
public interface DenyUserMapper extends BaseMapper<DenyUser> {

	IPage<DenyUserResult> selectPage(Page<DenyUserResult> page, @Param(Constants.WRAPPER) Wrapper<DenyUserResult> wrapper);
}
