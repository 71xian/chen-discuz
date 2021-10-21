package com.aoexe.discuz.system.modular.setting.service;

import com.aoexe.discuz.system.modular.setting.model.entity.Settings;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-10-01
 */
public interface ISettingsService extends IService<Settings> {

	void set(String key, String value);

	String get(String key);
	
	Long getDefaultGroupId();
	
	void setDefaultGroupId(Long id);
	
	String getSecret();
	
	Integer getUsernameChanges();
	
	String getMinIOSecret();
	
	String getMinIOAccessKey();
}
