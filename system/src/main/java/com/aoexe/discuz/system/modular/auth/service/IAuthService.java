package com.aoexe.discuz.system.modular.auth.service;

import com.aoexe.discuz.system.modular.auth.param.LoginParam;
import com.aoexe.discuz.system.modular.auth.param.RegisterParam;
import com.aoexe.discuz.system.modular.auth.param.TokenParam;
import com.aoexe.discuz.system.modular.auth.result.AuthResult;

public interface IAuthService {

	AuthResult register(RegisterParam param);

	AuthResult login(LoginParam param);

	void logout();

	AuthResult refresToken(TokenParam param);

}
