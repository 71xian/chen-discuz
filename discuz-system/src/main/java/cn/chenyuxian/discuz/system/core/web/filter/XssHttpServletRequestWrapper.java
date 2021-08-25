package cn.chenyuxian.discuz.system.core.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values == null) {
			return null;
		}
		String[] encodedValues = new String[name.length()];
		for(int i = 0; i < name.length(); i++) {
			encodedValues[i] = cleanXss(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if(value == null) {
			return null;
		}
		return cleanXss(value);
	}
	
	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if(value == null) {
			return null;
		}
		return cleanXss(value);
	}
	
	private String cleanXss(String value) {
		
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");

		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");

		value = value.replaceAll("'", "& #39;");

		value = value.replaceAll("eval\\((.*)\\)", "");

		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

		return value;
	}

}
