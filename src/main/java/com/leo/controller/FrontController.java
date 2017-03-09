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
public class FrontController extends BaseController{
	protected static Logger logger = LoggerFactory.getLogger("FrontController");
	@RequestMapping("/reg_code")
	public ModelAndView go_reg_code(ModelAndView mv){
		mv.setViewName("front/pc/reg_code");
		return mv;
	}
}
