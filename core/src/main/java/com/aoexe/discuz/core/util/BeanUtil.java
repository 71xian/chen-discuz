package com.aoexe.discuz.core.util;

import java.lang.reflect.Field;

/**
 * DTO，PO，VO之间的转化
 *
 * @author chenyuxian
 * @date 2021-08-30
 */
public class BeanUtil {

	public static <T> T trans(Object origin, T source) {
		for (Field field : origin.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Field field2 = null;
			try {
				field2 = source.getClass().getDeclaredField(field.getName());
				if (field2 == null) {
					continue;
				}
				field2.setAccessible(true);
				field2.set(source, field.get(origin));
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
		return source;
	}
}
