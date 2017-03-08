package com.leo.util;

import java.util.HashSet;
import java.util.Set;

public class Constants {
	public static Set<String> assetsUrl = new HashSet<String>();
	
	static{
		assetsUrl.add("/assets");
		assetsUrl.add("/data");
		assetsUrl.add("/files");
		assetsUrl.add("/images");
		assetsUrl.add("/plugins");
		assetsUrl.add("/resources");
		assetsUrl.add("/login");
	}
	

}
