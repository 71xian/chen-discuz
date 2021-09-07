package com.aoexe.discuz.core.context.session;

import java.util.Objects;
import java.util.UUID;

public class SessionContext {

	private static final ThreadLocal<String> SESSIONID = new ThreadLocal<>();

	public static void set(String sessionid) {
		SESSIONID.set(sessionid);
	}

	public static String get() {
		if (Objects.isNull(SESSIONID.get())) {
			SessionContext.set(UUID.randomUUID().toString());
		}
		return SESSIONID.get();
	}

	public static void remove() {
		SESSIONID.remove();
	}
}
