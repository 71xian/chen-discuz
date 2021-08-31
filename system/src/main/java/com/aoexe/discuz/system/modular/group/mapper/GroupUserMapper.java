package com.aoexe.discuz.system.modular.group.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.aoexe.discuz.system.modular.group.entity.GroupUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-27
 */
public interface GroupUserMapper extends BaseMapper<GroupUser> {

	@Select("select ${result} from group_user where ${column} = #{value} limit 1")
	Set<Long> findByColumn(@Param("result") String result, @Param("column") String column, @Param("value") Long value);

	@Delete("delete from group_user where ${column} = #{value}")
	boolean removeByColumn(@Param("column") String column, @Param("value") Long value);
	
	@Delete("delete from group_user where ${column} in ${value}")
	boolean removeByColumns(@Param("column") String column, @Param("value") String value);
	
}
