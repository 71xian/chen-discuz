package com.aoexe.discuz.system.modular.config.service;

import java.util.List;
import java.util.Set;

import com.aoexe.discuz.system.modular.config.entity.Config;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-27
 */
public interface IConfigService extends IService<Config> {

	List<Config> getConfigByTag(String tag);
	
	Set<String> getTags();
}
