package com.leo.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.leo.bean.Menu;

public class VelocityTemplateUtil {
	private static final String DEF_ENCODING = "UTF-8";
	private static Properties p;

	public static void main(String[] args) {
		Map<String, Object> contextMap = new HashMap<String, Object>();
		List<Menu> menuList = new ArrayList<Menu>();
		menuList.add(new Menu("ersf/ewfsfd/sdf", 0, "menu1"));
		menuList.add(new Menu("ersf/ewfsfd/sdf", 1, "menu2"));
		menuList.add(new Menu("ersf/ewfsfd/sdf", 2, "menu3"));
		contextMap.put("blog_addr", "http://blog.csdn.net/wanghjbuf");
		contextMap.put("menuList", menuList);
		VelocityTemplateUtil.renderHtml(contextMap, "vm/menu.vm", "E://1.html");
	}

	/**
	 * vm生成文件
	 * 
	 * @param contextMap
	 * @param templatePath
	 * @param outPutPath
	 * @return
	 */
	public static boolean renderHtml(Map<String, Object> contextMap, String templateClassPath, String outPutPath) {
		boolean flag = false;
		try {
			initVelocityProperties();
			Velocity.init(p);
			Template template = getVelocityTemplate(templateClassPath);
			VelocityContext context = initVelocityContext(contextMap);
			BufferedWriter writer = getWriterStream(outPutPath);
			template.merge(context, writer);
			writer.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 初始化VelocityProperties
	 * 
	 */
	public static void initVelocityProperties() throws Exception {
		if (p == null) {
			p = new Properties();
			try {
				p.put("file.resource.loader.class",
						"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
				p.setProperty(Velocity.ENCODING_DEFAULT, DEF_ENCODING);
				p.setProperty(Velocity.INPUT_ENCODING, DEF_ENCODING);
				p.setProperty(Velocity.OUTPUT_ENCODING, DEF_ENCODING);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 初始化VelocityContext
	 * 
	 * @param String
	 *            author_name List<String> blog_list
	 */
	private static VelocityContext initVelocityContext(Map<String, Object> map) throws Exception {
		VelocityContext context = new VelocityContext();
		try {
			for (String key : map.keySet()) {
				context.put(key, map.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return context;
	}

	/**
	 * 获取Velocity模板
	 * 
	 */
	private static Template getVelocityTemplate(String templateClassPath) throws Exception {
		Template template = new Template();
		try {
			template = Velocity.getTemplate(templateClassPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return template;
	}

	/**
	 * 获取Velocity写入流
	 * 
	 * @param String
	 *            writerStreamSource
	 */
	private static BufferedWriter getWriterStream(String outPutPath) throws Exception {
		try {
			FileOutputStream fos = new FileOutputStream(outPutPath);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, DEF_ENCODING));
			return writer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
