package com.aoexe.discuz.system.core.cache;

import java.util.HashSet;
import java.util.Set;

public class ResourceCache {

	private static final Set<String> API_CACHE = new HashSet<>();
	
	public static Set<String> me(){
		return API_CACHE;
	}
	
}
