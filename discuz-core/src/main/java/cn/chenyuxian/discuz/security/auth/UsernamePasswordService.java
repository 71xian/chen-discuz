package cn.chenyuxian.discuz.security.auth;

public interface UsernamePasswordService {

	SecurityUser loadUserByUsername(String username);
}
