package com.aoexe.discuz.system.core.security.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Filter {
	
	boolean filter(HttpServletRequest request, HttpServletResponse response);
	
}
