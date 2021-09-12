package com.aoexe.discuz.system.modular.group.mapper;

import org.apache.ibatis.annotations.Param;

import com.aoexe.discuz.system.modular.group.model.entity.DzqGroup;
import com.aoexe.discuz.system.modular.group.model.result.GroupResult;
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
public interface DzqGroupMapper extends BaseMapper<DzqGroup> {

	IPage<GroupResult> selectPage(Page<GroupResult> page, @Param(Constants.WRAPPER) Wrapper<GroupResult> wrapper);
}
