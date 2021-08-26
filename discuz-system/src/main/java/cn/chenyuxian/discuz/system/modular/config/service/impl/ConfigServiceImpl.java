package cn.chenyuxian.discuz.system.modular.config.service.impl;

import cn.chenyuxian.discuz.system.modular.config.entity.Config;
import cn.chenyuxian.discuz.system.modular.config.mapper.ConfigMapper;
import cn.chenyuxian.discuz.system.modular.config.service.IConfigService;
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
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
