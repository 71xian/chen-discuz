package com.aoexe.discuz.system.modular.config.mapper;

import org.apache.ibatis.annotations.Param;

import com.aoexe.discuz.system.modular.config.model.entity.Config;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-10
 */
public interface ConfigMapper extends BaseMapper<Config> {

	void updateByKey(@Param("key") String key, @Param("value") String value);
	
	Config selectByKey(String key);
}
