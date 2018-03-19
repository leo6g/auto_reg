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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leo.bean.Menu;

public class VelocityTemplateUtil {
	private static final String DEF_ENCODING = "UTF-8";
	private static Properties p;
	protected static Logger logger = LoggerFactory.getLogger("VelocityTemplateUtil");

	public static void main(String[] args) {
		Map<String, Object> contextMap = new HashMap<String, Object>();
		List<Menu> menuList = new ArrayList<Menu>();
		menuList.add(new Menu("ersf/ewfsfd/sdf", 0, "menu1"));
		menuList.add(new Menu("ersf/ewfsfd/sdf", 1, "menu2"));
		menuList.add(new Menu("ersf/ewfsfd/sdf", 2, "menu3"));
		contextMap.put("blog_addr", "http://blog.csdn.net/wanghjbuf");
		contextMap.put("menuList", menuList);
		Properties p = System.getProperties();
		System.out.println(System.getProperties());
		VelocityTemplateUtil.renderHtml(contextMap, "vm/menu.vm", "E://1.html");
	}

	/**
	 * vm生成文件
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
			logger.error("velocity生成文件时 出错 信息{}", e.getMessage());
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
			p.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			p.setProperty(Velocity.ENCODING_DEFAULT, DEF_ENCODING);
			p.setProperty(Velocity.INPUT_ENCODING, DEF_ENCODING);
			p.setProperty(Velocity.OUTPUT_ENCODING, DEF_ENCODING);
		}
	}

	/**
	 * 初始化velocity context
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private static VelocityContext initVelocityContext(Map<String, Object> map) throws Exception {
		VelocityContext context = new VelocityContext();
		for (String key : map.keySet()) {
			context.put(key, map.get(key));
		}
		return context;
	}

	/**
	 * 获取Velocity模板
	 * 
	 * @param templateClassPath
	 * @return
	 * @throws Exception
	 */
	private static Template getVelocityTemplate(String templateClassPath) throws Exception {
		Template template = new Template();
		template = Velocity.getTemplate(templateClassPath);
		return template;
	}

	/**
	 * 获取Velocity写入流
	 * 
	 * @param outPutPath
	 * @return
	 * @throws Exception
	 */
	private static BufferedWriter getWriterStream(String outPutPath) throws Exception {
		FileOutputStream fos = new FileOutputStream(outPutPath);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, DEF_ENCODING));
		return writer;
	}
}
