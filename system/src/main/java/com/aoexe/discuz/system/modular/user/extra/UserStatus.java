package com.aoexe.discuz.system.modular.user.extra;

public interface UserStatus {

	/**
	 * 正常
	 */
	int NORMAL = 0;

	/**
	 * 禁用
	 */
	int BAN = 1;

	/**
	 * 审核中
	 */
	int MOD = 2;

	/**
	 * 审核不通过
	 */
	int REFUSE = 3;

	/**
	 * 审核忽略
	 */
	int IGNORE = 4;

	/**
	 * 待填写注册扩展信息
	 */
	int NEED_FIELDS = 10;
	
	
}
