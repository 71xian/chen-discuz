package com.aoexe.discuz.system.modular.config.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.aoexe.discuz.system.modular.config.entity.Config;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-27
 */
public interface ConfigMapper extends BaseMapper<Config> {

	@Select("select * from config where ${column} = #{value}")
	List<Config> findByColumnStr(@Param("column") String column, @Param("value") String value);
	
	@Select("select ${column} from config")
	Set<String> findOnColumn(@Param("column") String column);
}
