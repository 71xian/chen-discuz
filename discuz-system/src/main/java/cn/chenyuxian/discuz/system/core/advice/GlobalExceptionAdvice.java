package cn.chenyuxian.discuz.system.core.advice;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.chenyuxian.discuz.core.base.exception.BaseException;
import cn.chenyuxian.discuz.core.base.response.BaseResponse;
import cn.chenyuxian.discuz.core.constant.ErrorCode;

@RestControllerAdvice
public class GlobalExceptionAdvice {

	@ExceptionHandler(BaseException.class)
	public BaseResponse<String> dqExceptionHandler(BaseException e){
		return BaseResponse.fail(e.getError());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public BaseResponse<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
		ObjectError error = e.getBindingResult().getAllErrors().get(0);
		return BaseResponse.fail(ErrorCode.INVALID_PARAMETER.getCode(),error.getDefaultMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public BaseResponse<String> unknownException(Exception e){
		return BaseResponse.fail(ErrorCode.INTERNAL_ERROR.getCode(), e.getMessage());
	}
}
