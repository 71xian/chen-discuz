package com.aoexe.discuz.system.modular.user.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Param;

import com.aoexe.discuz.system.modular.user.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-10
 */
public interface UserMapper extends BaseMapper<User> {

	int removeByIds(@Param("ids") Collection<Long> ids);
}
