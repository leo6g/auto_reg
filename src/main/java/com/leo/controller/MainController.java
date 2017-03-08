package com.leo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.frame.bean.OutputObject;

import robot.test.ClipboardUtil;
import robot.test.MyRobot;

@Controller
@RequestMapping("/font")
public class MainController extends BaseController{
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response,ModelAndView mv){
		mv.setViewName("reg_code");
		return mv;
	}
	@ResponseBody
	@RequestMapping("/regMachine")
	public OutputObject regMachine(HttpServletRequest request,HttpServletResponse response){
		OutputObject out = new OutputObject();
		String machineCode = request.getParameter("machineCode");
		long start = System.currentTimeMillis();
		MyRobot.click("D:\\HardWareId1.png",256,13);
		MyRobot.selectAll(0);
		MyRobot.del(1,0,0);
		ClipboardUtil.setSysClipboardText(machineCode);
		MyRobot.paste(0);
		MyRobot.click("D:\\Generate.png",108,182);
		MyRobot.click("D:\\copy.png");
		MyRobot.move(0, 0);
		String aText =ClipboardUtil.getSysClipboardText();
		out.setReturnCode("1");
		out.setReturnMessage(aText);
		long end = System.currentTimeMillis();
		System.out.println(aText+"用时："+(end-start)/1000+"s");
		return out;
	}
}
