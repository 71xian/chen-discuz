package com.aoexe.discuz.system.core.advice;

import static com.aoexe.discuz.core.constant.ResponseEnum.INVALID_PARAMETER;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.base.response.BaseResponse;

/**
 * 全局异常拦截
 *
 * @author chenyuxian
 * @date 2021-08-29
 */
@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(BaseException.class)
	public BaseResponse dqExceptionHandler(BaseException e) {
		return BaseResponse.fail(e.getCode());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public BaseResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		ObjectError error = e.getBindingResult().getAllErrors().get(0);
		return BaseResponse.fail(INVALID_PARAMETER.getCode(), error.getDefaultMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public BaseResponse unknownException(Exception e) {
		return BaseResponse.fail(INVALID_PARAMETER.getCode(), e.getMessage());
	}
}
