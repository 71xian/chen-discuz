package cn.chenyuxian.discuz.system.modular.group.enums;

import lombok.Getter;

@Getter
public enum GroupType {

	ADMIN(1),
	
	VISTOR(7),
	
	LOGIN(10);
	
	private int value;

	private GroupType(int value) {
		this.value = value;
	}
	
}
