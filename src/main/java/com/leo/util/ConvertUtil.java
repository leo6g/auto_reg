package com.leo.util;

import java.util.List;
import java.util.Map;

public class ConvertUtil {
	public static List<Map<String, String>> convertSqlMap2JavaMap(List<Map<String,Object>>list){
		List list1 =list;
		List<Map<String,String>> beans = (List<Map<String,String>>)list1;
		return beans;
	}
}
