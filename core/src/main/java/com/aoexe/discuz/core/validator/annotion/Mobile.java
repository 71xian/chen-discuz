package com.aoexe.discuz.core.validator.annotion;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.aoexe.discuz.core.validator.constraint.IsMoblieValidator;

/**
 * 是否是手机号
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = IsMoblieValidator.class)
public @interface Mobile {

	String regexp() default "";
	
	String message() default "手机格式不正确";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
