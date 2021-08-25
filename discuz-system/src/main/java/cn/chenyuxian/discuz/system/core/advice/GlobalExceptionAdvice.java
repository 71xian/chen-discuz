package cn.chenyuxian.discuz.system.core.advice;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.chenyuxian.discuz.core.base.exception.BaseException;
import cn.chenyuxian.discuz.core.base.response.BaseResponse;
import cn.chenyuxian.discuz.core.constant.ExpCode;

@RestControllerAdvice
public class GlobalExceptionAdvice {

	@ExceptionHandler(BaseException.class)
	public BaseResponse<String> dqExceptionHandler(BaseException e){
		return BaseResponse.fail(e.getCode(), e.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public BaseResponse<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
		ObjectError error = e.getBindingResult().getAllErrors().get(0);
		return BaseResponse.fail(ExpCode.INVALID_PARAMETER.getCode(),error.getDefaultMessage());
	}
}
