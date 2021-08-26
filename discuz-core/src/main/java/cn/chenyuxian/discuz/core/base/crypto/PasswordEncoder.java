package cn.chenyuxian.discuz.core.base.crypto;

public interface PasswordEncoder {

	String encode(CharSequence rawPassword);
	
	boolean matches(CharSequence rawPassword, String encodedPassword);
}
