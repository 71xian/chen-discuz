package cn.chenyuxian.discuz.core.common;

import java.util.Date;

import lombok.Data;

@Data
public class Payload<T> {

	private String id;
	
	private T userInfo;
	
	private Date expiration;
}
