package com.aoexe.discuz.core.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解防止表单重复提交
 * 
 * @author ruoyi
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmit {
	
	/**
	 * 提交时间间隔(单位/秒)
	 *
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-20
	 */
	int lockTime() default 1000;
}
