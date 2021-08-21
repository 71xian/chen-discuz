package cn.chenyuxian.discuz.system.modular.user.service.impl;

import cn.chenyuxian.discuz.system.modular.user.entity.User;
import cn.chenyuxian.discuz.system.modular.user.mapper.UserMapper;
import cn.chenyuxian.discuz.system.modular.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
