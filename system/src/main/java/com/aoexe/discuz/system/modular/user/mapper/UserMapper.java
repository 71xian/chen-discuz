package com.aoexe.discuz.system.modular.user.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.aoexe.discuz.system.modular.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
public interface UserMapper extends BaseMapper<User> {

	@Select("select * from user where ${column} = ${value}")
	User findByColumn(@Param("column") String column, @Param("value") String value);

	@Select("select * from user where ${column} = #{value}")
	User findByColumnStr(@Param("column") String column, @Param("value") String value);

	@Delete("delete from user where ${column} = #{value}")
	boolean removeByColumn(@Param("column") String column, @Param("value") Long value);

	@Delete("delete from user where ${column} in ${value}")
	boolean removeByColumns(@Param("column") String column, @Param("value") String value);

	@Update("update user set ${column} = ${value} where id = #{id}")
	boolean updateByColumn(@Param("column") String column, @Param("value") String value, @Param("id") Long id);

}
