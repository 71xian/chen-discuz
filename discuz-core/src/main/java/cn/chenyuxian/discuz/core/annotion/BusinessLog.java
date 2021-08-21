package cn.chenyuxian.discuz.core.annotion;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 业务注解
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface BusinessLog {

	/**
	 * 业务名称
	 *
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-20
	 */
	String value() default "";
	
	/**
	 * 是否将当前日志记录到数据库中
	 *
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-20
	 */
	boolean save() default true;
}
