package cn.chenyuxian.discuz.system.modular.setting.service.impl;

import cn.chenyuxian.discuz.system.modular.setting.entity.Settings;
import cn.chenyuxian.discuz.system.modular.setting.mapper.SettingsMapper;
import cn.chenyuxian.discuz.system.modular.setting.service.ISettingsService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-13
 */
@Service
public class SettingsServiceImpl extends ServiceImpl<SettingsMapper, Settings> implements ISettingsService {

	private String[] fillable = {
			"key","value","tag"
	};
	
	private String[] primaryKey = {
			"key","tag"
	};
	
	private String keyType = "String";
	
	private boolean incrementing = false;
	
	/**
	 * 开启云服务有关联动选项
	 */
	public static String[] linkage = {
			"qcloud_cms_image",
	        "qcloud_cms_text",
	        "qcloud_sms",
	        "qcloud_faceid",
	        "qcloud_cos",
	        "qcloud_vod",
	        "qcloud_captcha",
	};
	
	public static String[] checkEncrypt = {
			"app_id",
	        "app_secret",
	        "api_key",
	        "offiaccount_app_id",
	        "offiaccount_app_secret",
	        "miniprogram_app_id",
	        "miniprogram_app_secret",
	        "oplatform_app_id",
	        "oplatform_app_secret",
	        "qcloud_secret_id",
	        "qcloud_secret_key",
	        "qcloud_sms_app_id",
	        "qcloud_sms_app_key",
	        "qcloud_sms_template_id",
	        "qcloud_sms_sign",
	        "qcloud_captcha_app_id",
	        "qcloud_captcha_secret_key",
	        "qcloud_vod_url_key",
	        "offiaccount_server_config_token",
	        "uc_center_key",
	};
	
	private static String[] global_permission = {
			
	};
	
	@Override
	public String getValue(String key) {
		ResponseEntity<String> entity = new ResponseEntity<String>(key, null);
		return getOne(new QueryWrapper<Settings>()
				.eq("key", key)).getValue();
	}

}
