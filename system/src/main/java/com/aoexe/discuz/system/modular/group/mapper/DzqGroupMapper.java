package com.aoexe.discuz.system.modular.group.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.aoexe.discuz.system.modular.group.entity.DzqGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-27
 */
public interface DzqGroupMapper extends BaseMapper<DzqGroup> {

	@Select("select * from dzq_group where ${column} = ${value}")
	DzqGroup findByColumn(@Param("column") String column, @Param("value") String value);

	@Select("select * from dzq_group where ${column} = #{value}")
	DzqGroup findByColumnStr(@Param("column") String column, @Param("value") String value);

	@Delete("delete from dzq_group where ${column} = #{value}")
	boolean removeByColumn(@Param("column") String column, @Param("value") Long value);

	@Update("update dzq_group set ${column} = $value where id = #{groupId}")
	void updateByGroupId(@Param("groupId") Long groupId, @Param("column") String column, @Param("value") String value);

}
