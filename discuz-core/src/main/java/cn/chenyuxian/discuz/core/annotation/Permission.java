package cn.chenyuxian.discuz.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解，用于检查权限
 * @author chenyuxian
 * @date 2021-08-04
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Permission {

	/**
	 * 标识是否需要角色才可以访问，默认为空
	 */
	String[] role() default {};
	
	
}
