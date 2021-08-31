package com.aoexe.discuz.system.modular.group.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.aoexe.discuz.system.modular.group.entity.GroupPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-27
 */
public interface GroupPermissionMapper extends BaseMapper<GroupPermission> {

	@Select("select permission from group_permission where ${column} = #{value}")
	Set<String> findByColumn(@Param("column") String column, @Param("value") Long value);
	
	@Select("select permission from group_permission where ${column} in ${value}")
	Set<String> findByColumns(@Param("column") String column, @Param("value") String value);
	
	@Delete("delete from group_permission where ${column} = #{value}")
	boolean removeByColumn(@Param("column") String column, @Param("value") Long value);
	
	@Delete("delete from group_permission where ${column} in ${value}")
	boolean removeByColumns(@Param("column") String column, @Param("value") String value);
	
	@Insert("insert into group_permission values ${value}")
	void insertByGroupId(Long groupId, @Param("value") String value);
}
