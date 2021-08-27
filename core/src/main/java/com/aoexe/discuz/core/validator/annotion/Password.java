package com.aoexe.discuz.core.validator.annotion;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.aoexe.discuz.core.validator.constraint.PasswordValidator;

import lombok.Getter;

/**
 * 密码强度校验
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
	
	Strategy strategy() default Strategy.MEDIUM;
	
	String message() default "密码强度过低";
	
	Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    @Getter
    public enum Strategy {

    	LOW(""),
    	
    	/**
    	 * 至少8个字符，至少1个大写字母，1个小写字母和1个数字
    	 */
    	MEDIUM("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,32}$"),
    	
    	/**
    	 * 至少8个字符，至少1个大写字母，1个小写字母，1个数字和1个特殊字符
    	 */
    	STRONG("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,32}");
    	
    	private final String value;

    	private Strategy(String value) {
    		this.value = value;
    	}
    	
    }
}
