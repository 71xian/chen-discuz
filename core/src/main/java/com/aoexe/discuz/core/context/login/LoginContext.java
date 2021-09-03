package com.aoexe.discuz.core.context.login;

public class LoginContext {

	private static final ThreadLocal<LoginUser> CONTEXT_HOLDER = new ThreadLocal<>();
	
	public static void set(LoginUser loginUser) {
		CONTEXT_HOLDER.set(loginUser);
	}
	
	public static LoginUser get() {
		return CONTEXT_HOLDER.get();
	}
	
	public static void clear() {
		CONTEXT_HOLDER.remove();
	}
	
}
