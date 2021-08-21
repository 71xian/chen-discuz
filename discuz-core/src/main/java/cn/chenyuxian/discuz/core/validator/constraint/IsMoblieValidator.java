package cn.chenyuxian.discuz.core.validator.constraint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import cn.chenyuxian.discuz.core.validator.annotion.IsMobile;

/**
 * 手机号验证
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
public class IsMoblieValidator implements ConstraintValidator<IsMobile, String> {

	@Override
	public void initialize(IsMobile constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (!StringUtils.isEmpty(value)) {
			Pattern p = Pattern
					.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0,1,3,6-8])|(18[0-9])|(19[8,9])|(166))[0-9]{8}$");
			Matcher m = p.matcher(value);
			return m.matches();
		}
		return false;

	}

}
