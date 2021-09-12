package com.aoexe.discuz.system.modular.config.service;

import com.aoexe.discuz.system.modular.config.model.entity.Config;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-10
 */
public interface IConfigService extends IService<Config> {

	void updateByKey(String key, String value);
	
	String getValueByKey(String key);
}
