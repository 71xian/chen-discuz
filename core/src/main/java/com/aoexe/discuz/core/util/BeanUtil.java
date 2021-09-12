package com.aoexe.discuz.core.util;

import java.lang.reflect.Field;
import java.util.Objects;

public class BeanUtil {

	public static void copyProperties(Object from, Object to) {
		Class<?> toClazz = to.getClass();
		Field[] fields = from.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				Object obj = field.get(from);
				if (Objects.nonNull(obj)) {
					Field toField = toClazz.getDeclaredField(field.getName());
					toField.setAccessible(true);
					toField.set(to, obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
