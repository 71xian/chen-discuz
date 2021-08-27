package com.aoexe.discuz.core.annotion;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 权限注解
 *
 * @author chenyuxian
 * @date 2021-08-27
 */
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Permission {

}
