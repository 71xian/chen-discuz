package com.aoexe.discuz.system.modular.config.service.impl;

import org.springframework.stereotype.Service;

import com.aoexe.discuz.system.modular.config.entity.Config;
import com.aoexe.discuz.system.modular.config.mapper.ConfigMapper;
import com.aoexe.discuz.system.modular.config.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-27
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {
	
}
