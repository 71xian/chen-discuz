package cn.chenyuxian.discuz.security.exception;

import cn.chenyuxian.discuz.core.base.exception.DiscuzQException;
import cn.chenyuxian.discuz.core.enums.DiscuzQCode;

/**
 * token过期异常
 *
 * @author chenyuxian
 * @date 2021-08-22
 */
public class TokenExpiredException extends DiscuzQException{

	private static final long serialVersionUID = -4800955115766638803L;

	public TokenExpiredException(DiscuzQCode code) {
		super(code.getCode(),code.getMessage());
	}

}
