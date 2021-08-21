package cn.chenyuxian.discuz.core.validator.annotion;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cn.chenyuxian.discuz.core.enums.PasswordStrategy;
import cn.chenyuxian.discuz.core.validator.constraint.PasswordValidator;

/**
 * 密码强度校验
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordValid {
	
	PasswordStrategy strategy() default PasswordStrategy.MEDIUM;
	
	String message() default "密码强度过低";
	
	Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
