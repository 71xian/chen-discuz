package cn.chenyuxian.discuz.system.modular.user.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cn.chenyuxian.discuz.core.base.param.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserParam extends BaseParam{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(groups = {edit.class})
	private Long id;
	
	@NotBlank(groups = {edit.class,add.class})
	private String username;
	
	@NotBlank(groups = {edit.class,add.class})
	private String password;
	
	@NotBlank
	private String nickName;
	
	private String payPassword;
	
	private String mobile;
	
	private String signature;
	
}
