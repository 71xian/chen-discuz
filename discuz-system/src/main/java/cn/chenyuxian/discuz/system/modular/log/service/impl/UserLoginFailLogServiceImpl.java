package cn.chenyuxian.discuz.system.modular.log.service.impl;

import cn.chenyuxian.discuz.system.modular.log.entity.UserLoginFailLog;
import cn.chenyuxian.discuz.system.modular.log.mapper.UserLoginFailLogMapper;
import cn.chenyuxian.discuz.system.modular.log.service.IUserLoginFailLogService;
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
public class UserLoginFailLogServiceImpl extends ServiceImpl<UserLoginFailLogMapper, UserLoginFailLog> implements IUserLoginFailLogService {

}
