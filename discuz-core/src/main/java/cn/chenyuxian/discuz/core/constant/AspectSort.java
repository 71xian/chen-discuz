package cn.chenyuxian.discuz.core.constant;

/**
 * 定义AOP执行顺序，因为AOP执行顺序的不同可能会导致不同的结果
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
public interface AspectSort {

	/**
	 * 业务日志排序
	 */
	int BUSINESS_LOG = 100;
	
	/**
	 * 权限校验排序
	 */
	int PERMISSION = -100;

}
