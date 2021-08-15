package cn.chenyuxian.discuz.core.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 结果包装的注解，给响应结果做包装
 * @author chenyuxian
 * @date 2021-08-04
 */
@Inherited
@Retention(RUNTIME)
@Target(METHOD)
public @interface Wrapper {

}
