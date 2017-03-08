package com.leo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.frame.bean.OutputObject;
import com.leo.util.ActionUtil;
import com.leo.util.ClipboardUtil;
import com.leo.util.MyRobot;

@Controller
@RequestMapping("/front")
public class MainController extends BaseController{
	protected static Logger logger = LoggerFactory.getLogger("MainController");
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response,ModelAndView mv){
		mv.setViewName("reg_code");
		logger.info("跳转");
		return mv;
	}
	@ResponseBody
	@RequestMapping("/regMachine")
	public OutputObject regMachine(HttpServletRequest request,HttpServletResponse response){
		OutputObject out = new OutputObject();
		String machineCode = request.getParameter("machineCode");
		String regCode = ActionUtil.do1(machineCode);
		out.setReturnCode("1");
		out.setReturnMessage(regCode);
		return out;
	}
}
