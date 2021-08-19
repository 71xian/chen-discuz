package cn.chenyuxian.discuz.core.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.text.StringEscapeUtils;

/**
 * Xss处理
 *
 * @author chenyuxian
 * @date 2021-08-19
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

	public XssRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getQueryString() {
		return StringEscapeUtils.escapeHtml4(super.getQueryString());
	}

	@Override
	public String getParameter(String name) {
		return StringEscapeUtils.escapeHtml4(super.getParameter(name));
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		int length = values.length;
		if (values != null && length == 0) {
			return values;
		}
		String[] escapeValues = new String[length];
		for (int i = 0; i < length; i++) {
			escapeValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
		}
		return escapeValues;

	}

}
