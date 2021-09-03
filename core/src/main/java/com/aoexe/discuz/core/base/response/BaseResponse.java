package com.aoexe.discuz.core.base.response;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.alibaba.fastjson.annotation.JSONField;
import com.aoexe.discuz.core.constant.ResponseEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应类，统一返回结果形式
 *
 * @author chenyuxian
 * @date 2021-08-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer code;

	private String message;

	private T data;

	private String requestId;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date requestAt;

	public BaseResponse(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.requestId = UUID.randomUUID().toString();
		this.requestAt = new Date();
	}

	public static <T> BaseResponse<T> ok(T data){
		return new BaseResponse<>(0, "接口调用成功", data);
	}
	
	public static BaseResponse<Object[]> fail(Integer code, String message){
		return new BaseResponse<>(code, message, new Object[0]);
	}
	
	public static BaseResponse<Object[]> fail(ResponseEnum enums){
		return fail(enums.getCode(), enums.getMessage());
	}
}
