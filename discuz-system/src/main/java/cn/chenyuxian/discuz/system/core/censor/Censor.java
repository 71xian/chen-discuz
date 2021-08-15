package cn.chenyuxian.discuz.system.core.censor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.chenyuxian.discuz.system.modular.setting.entity.Settings;
import cn.chenyuxian.discuz.system.modular.setting.service.ISettingsService;

public class Censor {

	public String[] allowTypes = {
			"ugc",
			"username",
			"signature",
			"dialog",
			"nickname"
	};
	
	public boolean isMod = false;
	
	public List<String> wordReplace;
	
	public List<String> wordMod;
	
	public List<String> wordBanned;
	
	@Autowired
	public ISettingsService settingsService;
	
	public String checkText(String content, String type) {
		if(content.isEmpty()) {
			return content;
		}
		
		if(this.settingsService.getValue("censor").equals("1")) {
			content = 
		}
	}
	
	
}
