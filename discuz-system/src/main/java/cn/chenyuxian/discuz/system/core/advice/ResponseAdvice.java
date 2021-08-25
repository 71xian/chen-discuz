package cn.chenyuxian.discuz.system.core.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import cn.chenyuxian.discuz.core.base.exception.BaseException;
import cn.chenyuxian.discuz.core.base.response.BaseResponse;

/**
 * 将Controller层返回的对象转成Response对象 规范返回对象的格式
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
@RestControllerAdvice(basePackages = {"cn.chenyuxian.discuz.system.modular.*.controller"})
public class ResponseAdvice implements ResponseBodyAdvice<Object>{

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return !returnType.getParameterType().equals(BaseResponse.class);
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		if(returnType.getGenericParameterType().equals(String.class)) {
			try {
				return JSON.toJSONString(BaseResponse.ok(body));
			} catch (JSONException e) {
				throw new BaseException("返回string类型错误");
			}
		}
		return BaseResponse.ok(body);
	}

}
