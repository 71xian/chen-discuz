package cn.chenyuxian.discuz.core.enums;

public enum Switch {

	/**
	 * 关闭
	 */
	OFF(0),
	
	/**
	 * 开启
	 */
	ON(1);

	private Integer value;

	private Switch(Integer value) {
		this.value = value;
	}

}
