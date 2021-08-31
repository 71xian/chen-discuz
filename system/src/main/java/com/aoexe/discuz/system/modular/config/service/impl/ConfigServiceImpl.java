package com.aoexe.discuz.system.modular.config.service.impl;

import com.aoexe.discuz.system.modular.config.entity.Config;
import com.aoexe.discuz.system.modular.config.mapper.ConfigMapper;
import com.aoexe.discuz.system.modular.config.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

	@Resource
	private ConfigMapper mapper;
	
	@Override
	public List<Config> getConfigByTag(String tag) {
		return mapper.findByColumnStr("config_tag", tag);
	}

	@Override
	public Set<String> getTags() {
		return mapper.findOnColumn("config_tag");
	}

}
