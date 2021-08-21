package cn.chenyuxian.discuz.core.exception;

import cn.chenyuxian.discuz.core.base.exception.DiscuzQException;
import cn.chenyuxian.discuz.core.enums.DiscuzQCode;

/**
 * dao层异常
 *
 * @author chenyuxian
 * @date 2021-08-19
 */
public class DaoException extends DiscuzQException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3156215232297093170L;

	public DaoException(String message) {
        super(message);
    }

    public DaoException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public DaoException(DiscuzQCode apiCode) {
        super(apiCode);
    }
}
