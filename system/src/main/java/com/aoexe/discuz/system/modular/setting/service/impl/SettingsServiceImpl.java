package com.aoexe.discuz.system.modular.setting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.util.RedisUtil;
import com.aoexe.discuz.system.modular.setting.mapper.SettingsMapper;
import com.aoexe.discuz.system.modular.setting.model.entity.Settings;
import com.aoexe.discuz.system.modular.setting.service.ISettingsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-10-01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SettingsServiceImpl extends ServiceImpl<SettingsMapper, Settings> implements ISettingsService {

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public void set(String key, String value) {
		Settings settings = new Settings();
		settings.setKey(key);
		settings.setValue(value);
		settings.setTag("default");
		this.saveOrUpdate(settings);
		redisUtil.set(key, value);
	}

	@Override
	public String get(String key) {
		String value = redisUtil.get(key);
		if (value == null) {
			LambdaQueryWrapper<Settings> lambdaQuery = Wrappers.lambdaQuery(Settings.class);
			lambdaQuery.eq(Settings::getKey, key);
			Settings one = this.getOne(lambdaQuery);
			if (one != null) {
				redisUtil.set(key, one.getValue());
			} else {
				throw new BaseException(ResponseEnum.RESOURCE_NOT_FOUND);
			}
		}
		return value;
	}

	@Override
	public Long getDefaultGroupId() {
		return Long.valueOf(this.get("group_id"));
	}

	@Override
	public void setDefaultGroupId(Long id) {
		this.set("group_id", id.toString());
	}

	@Override
	public String getSecret() {
		return this.get("site_secret");
	}

	@Override
	public Integer getUsernameChanges() {
		return Integer.valueOf(this.get("site_bound"));
	}

	@Override
	public String getMinIOSecret() {
		return this.get("minio_access_secret");
	}

	@Override
	public String getMinIOAccessKey() {
		return this.get("minio_access_key");
	}

}
