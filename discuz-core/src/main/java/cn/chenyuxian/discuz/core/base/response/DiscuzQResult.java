package cn.chenyuxian.discuz.core.base.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.chenyuxian.discuz.core.enums.DiscuzQCode;
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

	private String requestId;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime requestAt;

	public DiscuzQResult(DiscuzQCode response) {
		this(response.getCode(), response.getMessage());
	}

	public DiscuzQResult(Integer code, String message) {
		this(code, message, null);
	}
	
	public DiscuzQResult(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.requestId = UUID.randomUUID().toString();
		this.requestAt = LocalDateTime.now();
	}

}
