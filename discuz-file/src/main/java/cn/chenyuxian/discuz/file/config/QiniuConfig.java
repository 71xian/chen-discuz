package cn.chenyuxian.discuz.file.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qiniu.util.Auth;

@Configuration
public class QiniuConfig {

	private String ACCESS_KEY = "y5S-dm40nFe9fW39tIVRkryWtm3K9ydlLVGx11Cw";
	
	private String SECRET_KEY = "Wl3oneugB-3qA_qgorSK1SB2zUAPDXHY3s8qUiUy";
	
	@Bean
	public Auth auth() {
		return Auth.create(ACCESS_KEY, SECRET_KEY);
	}

	
}
