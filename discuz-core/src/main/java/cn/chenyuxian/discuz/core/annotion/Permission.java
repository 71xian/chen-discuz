package cn.chenyuxian.discuz.core.annotion;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 权限校验
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Permission {

	String role() default "";
	
	Logical logical() default Logical.OR;
	
	public enum Logical{
		AND,OR;
	}
}
