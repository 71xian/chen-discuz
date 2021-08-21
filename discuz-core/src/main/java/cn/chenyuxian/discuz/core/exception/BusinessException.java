package cn.chenyuxian.discuz.core.exception;

import cn.chenyuxian.discuz.core.base.exception.DiscuzQException;
import cn.chenyuxian.discuz.core.enums.DiscuzQCode;

/**
 * 业务方法异常
 *
 * @author chenyuxian
 * @date 2021-08-19
 */
public class BusinessException extends DiscuzQException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3959085651178442844L;

	public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public BusinessException(DiscuzQCode apiCode) {
        super(apiCode);
    }
	
}
