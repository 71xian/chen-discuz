package com.aoexe.discuz.core.base.exception;

import com.aoexe.discuz.core.constant.ResponseEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基类异常
 *
 * @author chenyuxian
 * @date 2021-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -952771024717915650L;

	private ResponseEnum code;
	
	public BaseException(ResponseEnum code) {
		super(code.getMessage());
		this.code = code;
	}

}
