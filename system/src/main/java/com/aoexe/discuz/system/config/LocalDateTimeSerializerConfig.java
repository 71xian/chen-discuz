package com.aoexe.discuz.system.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

/**
 * 后台传前台
 *
 * @author chenyuxian
 * @date 2021-09-12 13:42:05
 */
@Configuration
public class LocalDateTimeSerializerConfig {

	@Value("${spring.jackson.date-format: yyyy-MM-dd HH:mm:ss}")
	private String pattern;

	@Bean
	public LocalDateTimeSerializer localDateTimeSerializer() {
		return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
	}

	@Bean
	public LocalDateTimeDeserializer localDateTimeDeserializer() {
		return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern));
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		return builder -> {
			builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
			builder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer());
			builder.simpleDateFormat(pattern);
		};
	}

	/**
	 * 前台传后台
	 * 
	 * @author chenyuxian
	 * @date 2021-09-12 13:42:29
	 * @return
	 */
	@Bean
	public Converter<String, LocalDateTime> localDateTimeConverter() {
		return new Converter<String, LocalDateTime>() {
			@Override
			public LocalDateTime convert(String value) {
				DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
				LocalDateTime dateTime = null;
				try {
					switch (value.length()) {
					case 10:
						value += "00:00:00";
						break;
					case 13:
						value += ":00:00";
						break;
					case 16:
						value += ":00";
						break;
					}
					dateTime = LocalDateTime.parse(value, df);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return dateTime;
			}

			@Override
			public JavaType getInputType(TypeFactory typeFactory) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public JavaType getOutputType(TypeFactory typeFactory) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
}
