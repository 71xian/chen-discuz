package cn.chenyuxian.discuz.core.base.exception;

import cn.chenyuxian.discuz.core.constant.ExpCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 应用基类异常
 *
 * @author chenyuxian
 * @date 2021-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -952771024717915650L;

	private Integer code;

	private String message;
	
	public BaseException() {
		super();
	}

	public BaseException(String message) {
		super(message);
		this.message = message;
	}

	public BaseException(Integer code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public BaseException(ExpCode dzpCode) {
		super(dzpCode.getMessage());
		this.code = dzpCode.getCode();
		this.message = dzpCode.getMessage();
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

}
