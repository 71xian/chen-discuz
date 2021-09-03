package com.aoexe.discuz.system.core.advice;

import static com.aoexe.discuz.core.constant.ResponseEnum.INVALID_PARAMETER;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.base.response.BaseResponse;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.context.login.LoginContext;

/**
 * 全局异常拦截
 *
 * @author chenyuxian
 * @date 2021-08-29
 */
@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(BaseException.class)
	public BaseResponse<Object[]> dqExceptionHandler(BaseException e) {
		LoginContext.clear();
		return BaseResponse.fail(e.getCode(), e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public BaseResponse<Object[]> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		ObjectError error = e.getBindingResult().getAllErrors().get(0);
		return BaseResponse.fail(INVALID_PARAMETER.getCode(), error.getDefaultMessage());
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public BaseResponse<Object[]> notFound(NoHandlerFoundException e){
		return BaseResponse.fail(ResponseEnum.RESOURCE_NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public BaseResponse<Object[]> unknownException(Exception e) {
		LoginContext.clear();
		return BaseResponse.fail(ResponseEnum.INTERNAL_ERROR.getCode(), e.getMessage());
	}
}
