package cn.chenyuxian.discuz.core.base.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.chenyuxian.discuz.core.common.response.DiscuzQCode;
import lombok.Data;

/**
 * 响应类，统一返回结果形式
 *
 * @author chenyuxian
 * @date 2021-08-15
 */
@Data
public class DiscuzQResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Integer code;

	private final String message;

	private final T data;

	private Long requestId;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime requestAt;

	public static <T> DiscuzQResult<T> ok() {
		return ok(DiscuzQCode.SUCCESS, null);
	}

	public static <T> DiscuzQResult<T> ok(DiscuzQCode code, T data) {
		return new DiscuzQResult<T>(code, data);
	}

	public static <T> DiscuzQResult<T> fail(DiscuzQCode code) {
		return fail(code.getCode(), code.getMessage());
	}

	public static <T> DiscuzQResult<T> fail(Integer code, String message) {
		return new DiscuzQResult<T>(code, message, null);
	}

	public static <T> DiscuzQResult<T> response(DiscuzQCode code) {
		return new DiscuzQResult<T>(code, null);
	}

	private DiscuzQResult(DiscuzQCode response, T data) {
		this(response.getCode(), response.getMessage(), data);
	}

	private DiscuzQResult(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.requestId = Thread.currentThread().getId();
		this.requestAt = LocalDateTime.now();
	}

}
