package com.aoexe.discuz.system.modular.auth.model.result;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AuthResult implements Serializable {

	private Long uid;

	private String avatarUrl;

	private Integer userStatus;

	private boolean isMissNickname;

	private Date issuedAt;

	private String tokenType;

	private String accessToken;

	private String refreshToken;
}
