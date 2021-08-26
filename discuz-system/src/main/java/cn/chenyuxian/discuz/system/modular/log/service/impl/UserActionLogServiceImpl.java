package cn.chenyuxian.discuz.system.modular.log.service.impl;

import cn.chenyuxian.discuz.system.modular.log.entity.UserActionLog;
import cn.chenyuxian.discuz.system.modular.log.mapper.UserActionLogMapper;
import cn.chenyuxian.discuz.system.modular.log.service.IUserActionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
@Service
public class UserActionLogServiceImpl extends ServiceImpl<UserActionLogMapper, UserActionLog> implements IUserActionLogService {

}
