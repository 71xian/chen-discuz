package cn.chenyuxian.discuz.core.exception;

import java.util.List;

import org.springframework.http.HttpHeaders;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int statusCode;

	private List<HttpHeaders> headers;

	public HttpException(int statusCode, List<HttpHeaders> headers, String message, Throwable cause) {
		super(message, cause);
		this.statusCode = statusCode;
		this.headers = headers;
	}

}
