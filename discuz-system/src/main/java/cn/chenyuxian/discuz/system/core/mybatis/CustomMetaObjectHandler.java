package cn.chenyuxian.discuz.system.core.mybatis;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectionException;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义sql字段填充器，自动填充创建修改相关字段
 * @author chenyuxian
 * @date 2021-08-05
 */
@Slf4j
public class CustomMetaObjectHandler implements MetaObjectHandler{

	private static final String CREATE_AT = "createAt";
	
	private static final String UPDATE_AT = "updateAt";
	
	@Override
	public void insertFill(MetaObject metaObject) {
		try {
			setFieldValByName(CREATE_AT, LocalDateTime.now(), metaObject);
		} catch (ReflectionException e) {
			log.warn(">>> CustomMetaObjectHandler处理过程中无相关字段，不做处理");
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		try {
			setFieldValByName(UPDATE_AT, LocalDateTime.now(), metaObject);
		} catch (ReflectionException e) {
			log.warn(">>> CustomMetaObjectHandler处理过程中无相关字段，不做处理");
		}
	}

}
