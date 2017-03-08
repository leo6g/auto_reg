package com.leo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionUtil {
	protected static Logger logger = LoggerFactory.getLogger("ActionUtil");
	public static String do1(String machineCode){
		String regCode = "";
		try {
			long start = System.currentTimeMillis();
			MyRobot.click("image/HardWareId1.png",256,13);
			MyRobot.selectAll(0);
			MyRobot.del(1,0,0);
			ClipboardUtil.setSysClipboardText(machineCode);
			MyRobot.paste(0);
//		MyRobot.click("image/Generate.png",108,182);
//		MyRobot.click("image/copy.png");
			MyRobot.move(0, 0);
			regCode=ClipboardUtil.getSysClipboardText();
			if(machineCode.equals(regCode)){
				//读取时复制注册码失败
			}
			long end = System.currentTimeMillis();
			logger.info(regCode+"用时："+(end-start)/1000+"s");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return regCode;
		
	}
}
