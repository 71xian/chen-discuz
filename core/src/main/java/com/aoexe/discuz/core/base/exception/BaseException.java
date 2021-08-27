package com.aoexe.discuz.core.base.exception;

import com.aoexe.discuz.core.constant.ErrorCode;

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

	private ErrorCode error;
	
	public BaseException(ErrorCode error) {
		super(error.getMessage());
		this.error = error;
	}

}
