package cn.chenyuxian.discuz.system.modular.log.service.impl;

import cn.chenyuxian.discuz.system.modular.log.entity.OperationLog;
import cn.chenyuxian.discuz.system.modular.log.mapper.OperationLogMapper;
import cn.chenyuxian.discuz.system.modular.log.service.IOperationLogService;
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
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

}
