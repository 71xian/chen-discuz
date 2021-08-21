package cn.chenyuxian.discuz.core.base.response;

import java.util.Map;

import cn.chenyuxian.discuz.core.enums.DiscuzQCode;
import lombok.Data;

/**
 * 异常响应结果类
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
public class FailDiscuzQResult extends DiscuzQResult<Void>{

	private static final long serialVersionUID = 1L;
	
	public Map<String, Object> extra;

	public FailDiscuzQResult(Integer code, String message) {
		super(code, message);
	}
	
	public static FailDiscuzQResult fail(Integer code, String message) {
		return new FailDiscuzQResult(code, message);
	}
	
	public static FailDiscuzQResult fail(DiscuzQCode code) {
		return new FailDiscuzQResult(code.getCode(), code.getMessage());
	}

	public Map<String, Object> getExtra() {
		return extra;
	}

	public void setExtra(Map<String, Object> extra) {
		this.extra = extra;
	}
	
	

}
