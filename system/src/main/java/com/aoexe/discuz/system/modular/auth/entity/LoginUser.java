package com.aoexe.discuz.system.modular.auth.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 登录用户模型
 *
 * @author chenyuxian
 * @date 2021-08-30
 */
@Data
public class LoginUser implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String password;

	private String nickname;

	private String payPassword;

	private String signature;

	private String lastLoginIp;

	private Integer lastLoginPort;

	private String registerIp;

	private Integer registerPort;

	private String registerReason;

	private String rejectReason;

	private Integer usernameBout;

	private Integer threadCount;

	private Integer followCount;

	private Integer fansCount;

	private Integer likeCount;

	private Integer questionCount;

	private Integer status;

	private String avatar;

	private String background;

	private String identify;

	private LocalDateTime avatarAt;

	private LocalDateTime loginAt;

	private LocalDateTime joinedAt;

	private LocalDateTime expiredAt;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private Integer bindType;

	private Set<String> role;

	private Set<String> permissions;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		role.forEach(r -> authorities.add(new SimpleGrantedAuthority(r)));
		return authorities;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
