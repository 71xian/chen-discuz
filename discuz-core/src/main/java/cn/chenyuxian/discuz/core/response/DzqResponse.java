package cn.chenyuxian.discuz.core.response;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 响应类，统一返回结果形式
 *
 * @author chenyuxian
 * @date 2021-08-15
 */
@Data
public class DzqResponse<T> {

	private final Integer code;

	private final String message;

	private final T data;
	
	private Long requestId;
	
	private LocalDateTime requestAt;
	
	public DzqResponse(ResponseCode response) {
		this(response.getCode(), response.getMessage(), null);
	}

	public DzqResponse(ResponseCode response, T data) {
		this(response.getCode(), response.getMessage(), data);
	}
	
	public static <T> DzqResponse<T> ok(T data){
		return new DzqResponse<T>(ResponseCode.SUCCESS, data);
	}
	
	public static <T> DzqResponse<T> fail(ResponseCode response){
		return new DzqResponse<>(response);
	}
	
	private DzqResponse(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.requestId = Thread.currentThread().getId();
		this.requestAt = LocalDateTime.now();
	}
	
}
