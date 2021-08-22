package cn.chenyuxian.discuz.security.exception;

import cn.chenyuxian.discuz.core.base.exception.DiscuzQException;
import cn.chenyuxian.discuz.core.enums.DiscuzQCode;

/**
 * 没有携带token异常
 *
 * @author chenyuxian
 * @date 2021-08-22
 */
public class EmptyTokenException extends DiscuzQException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3286173403036072943L;

	public EmptyTokenException(DiscuzQCode code) {
		super(code.getCode(), code.getMessage());
	}
}
