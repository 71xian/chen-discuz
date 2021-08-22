package cn.chenyuxian.discuz.core.base.exception;

import cn.chenyuxian.discuz.core.enums.DiscuzQCode;
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
public class DiscuzQException extends RuntimeException {

	private static final long serialVersionUID = -952771024717915650L;

	private Integer code;

	private String message;

	public DiscuzQException() {
		super();
	}

	public DiscuzQException(String message) {
		super(message);
		this.message = message;
	}

	public DiscuzQException(Integer code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public DiscuzQException(DiscuzQCode dzpCode) {
		super(dzpCode.getMessage());
		this.code = dzpCode.getCode();
		this.message = dzpCode.getMessage();
	}

	public DiscuzQException(String message, Throwable cause) {
		super(message, cause);
	}

	public DiscuzQException(Throwable cause) {
		super(cause);
	}

}
