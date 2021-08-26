package cn.chenyuxian.discuz.core.base.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.chenyuxian.discuz.core.constant.ErrorCode;
import lombok.Data;

/**
 * 响应类，统一返回结果形式
 *
 * @author chenyuxian
 * @date 2021-08-15
 */
@Data
public class BaseResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Integer code;

	private final String message;

	private final T data;

	private String requestId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime requestAt;

	public BaseResponse(ErrorCode response) {
		this(response.getCode(), response.getMessage());
	}

	public BaseResponse(Integer code, String message) {
		this(code, message, null);
	}

	public BaseResponse(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.requestId = UUID.randomUUID().toString().toUpperCase();
		this.requestAt = LocalDateTime.now();
	}

	public static BaseResponse<String> ok() {
		return new BaseResponse<>(0, "接口调用成功");
	}

	public static <T> BaseResponse<T> ok(T data) {
		return new BaseResponse<>(0, "接口调用成功", data);
	}

	public static BaseResponse<String> fail(ErrorCode code) {
		return new BaseResponse<>(code);
	}

	public static BaseResponse<String> fail(Integer code, String message) {
		return new BaseResponse<>(code, message);
	}

}
