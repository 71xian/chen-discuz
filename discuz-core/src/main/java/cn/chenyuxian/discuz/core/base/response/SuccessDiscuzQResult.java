package cn.chenyuxian.discuz.core.base.response;

import cn.chenyuxian.discuz.core.enums.DiscuzQCode;

/**
 * 成功响应类
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
public class SuccessDiscuzQResult<T> extends DiscuzQResult<T>{

	private static final long serialVersionUID = 1L;

	public SuccessDiscuzQResult(Integer code, String message, T data) {
		super(code, message, data);
	}
	
	public SuccessDiscuzQResult(Integer code, String message) {
		super(code, message);
	}
	
	public static SuccessDiscuzQResult<Void> ok() {
		return ok(null);
	}

	public static <T> SuccessDiscuzQResult<T> ok(T data) {
		return new SuccessDiscuzQResult<T>(DiscuzQCode.SUCCESS.getCode(), DiscuzQCode.SUCCESS.getMessage(), data);
	}

}
