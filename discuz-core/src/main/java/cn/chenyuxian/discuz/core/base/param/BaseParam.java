package cn.chenyuxian.discuz.core.base.param;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseParam implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public @interface add {}
	
	public @interface edit {}
	
	
}
