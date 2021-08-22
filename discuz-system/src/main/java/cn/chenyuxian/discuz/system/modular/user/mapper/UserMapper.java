package cn.chenyuxian.discuz.system.modular.user.mapper;

import cn.chenyuxian.discuz.system.modular.user.entity.User;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-21
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

	User selectUserByUsername(String username);
}
