package cn.chenyuxian.discuz.security.exception;

import cn.chenyuxian.discuz.core.base.exception.DiscuzQException;
import cn.chenyuxian.discuz.core.enums.DiscuzQCode;

public class UsernamePasswordException extends DiscuzQException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7707257574020887461L;

	public UsernamePasswordException(DiscuzQCode code) {
		super(code.getCode(), code.getMessage());
	}
}
