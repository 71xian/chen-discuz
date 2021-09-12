package com.aoexe.discuz.system.modular.config.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aoexe.discuz.core.util.RedisUtil;
import com.aoexe.discuz.system.modular.config.mapper.ConfigMapper;
import com.aoexe.discuz.system.modular.config.model.entity.Config;
import com.aoexe.discuz.system.modular.config.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public void updateByKey(String key, String value) {
		baseMapper.updateByKey(key, value);
		redisUtil.set(key, value);
	}

	@Override
	public String getValueByKey(String key) {
		String value = redisUtil.get(key);
		if (value == null) {
			Config config = baseMapper.selectByKey(key);
			redisUtil.set(key, config.getConfigValue());
		}
		return value;
	}

}
