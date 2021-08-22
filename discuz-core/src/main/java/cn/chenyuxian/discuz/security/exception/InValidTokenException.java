package cn.chenyuxian.discuz.security.exception;

import cn.chenyuxian.discuz.core.base.exception.DiscuzQException;
import cn.chenyuxian.discuz.core.enums.DiscuzQCode;

/**
 * token验证异常
 *
 * @author chenyuxian
 * @date 2021-08-22
 */
public class InValidTokenException extends DiscuzQException{

	private static final long serialVersionUID = 3874387684415234910L;
	
	public InValidTokenException(DiscuzQCode code) {
		super(code.getCode(), code.getMessage());
	}

}
