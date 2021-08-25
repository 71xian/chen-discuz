package cn.chenyuxian.discuz.system.core.config;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectionException;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义字段填充器，自动填充创建修改相关字段
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
@Slf4j
public class DateTimeMetaObject implements MetaObjectHandler {

	private static final String CREATED_AT = "createdAt";

	private static final String UPDATED_AT = "updatedAt";

	@Override
	public void insertFill(MetaObject metaObject) {
		try {
			setFieldValByName(CREATED_AT, LocalDateTime.now(), metaObject);
		}catch (ReflectionException e) {
			log.warn(">>> 插入未找到相关字段，不做处理");
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		try {
			setFieldValByName(UPDATED_AT, LocalDateTime.now(), metaObject);
		}catch (ReflectionException e) {
			log.warn(">>> 更新未找到相关字段，不做处理");
		}
	}

}
