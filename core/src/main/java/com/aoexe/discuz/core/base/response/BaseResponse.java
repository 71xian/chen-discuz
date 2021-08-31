package com.aoexe.discuz.core.base.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.aoexe.discuz.core.constant.ResponseEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 响应类，统一返回结果形式
 *
 * @author chenyuxian
 * @date 2021-08-15
 */
@Data
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer code;

	private String message;

	private Object data;

	private String requestId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime requestAt;

	public BaseResponse(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.requestId = UUID.randomUUID().toString();
		this.requestAt = LocalDateTime.now();
	}

	public BaseResponse(Integer code, String message) {
		this(code, message, new Object[0]);
	}

	public static BaseResponse ok(Object data) {
		return new BaseResponse(0, "接口调用成功", data);
	}

	public static BaseResponse fail(ResponseEnum code) {
		return new BaseResponse(code.getCode(), code.getMessage());
	}

	public static BaseResponse fail(Integer code, String message) {
		return new BaseResponse(code, message);
	}

}
