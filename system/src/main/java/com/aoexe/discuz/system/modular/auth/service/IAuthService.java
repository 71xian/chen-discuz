package com.aoexe.discuz.system.modular.auth.service;

import com.aoexe.discuz.system.modular.auth.model.param.LoginParam;
import com.aoexe.discuz.system.modular.auth.model.param.RegisterParam;
import com.aoexe.discuz.system.modular.auth.model.param.TokenParam;
import com.aoexe.discuz.system.modular.auth.model.result.AuthResult;

public interface IAuthService {

	AuthResult register(RegisterParam param);

	AuthResult login(LoginParam param);

	void logout();

	AuthResult refresToken(TokenParam param);

}
