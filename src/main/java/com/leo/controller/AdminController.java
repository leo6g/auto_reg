package com.leo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.frame.bean.InputObject;
import com.ai.frame.bean.OutputObject;
import com.ai.frame.util.BeanUtil;
import com.leo.form.SYSUserForm;
import com.leo.util.ActionUtil;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{
	protected static Logger logger = LoggerFactory.getLogger("MainController");
	@RequestMapping("")
	public ModelAndView index(HttpServletRequest request,ModelAndView mv){
		String tab = request.getParameter("tab");
		mv.setViewName("admin/"+tab);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value="login.do")
	public OutputObject checkUser(@ModelAttribute("sYSUserForm") SYSUserForm sYSUserForm,HttpServletRequest request,HttpServletResponse response
			,BindingResult result){
		if (result.hasErrors()) {
			returnValidatorAjaxResult(result);
		}
		OutputObject out = null;
		InputObject in = new InputObject();
		Map<String,String> param = BeanUtil.convertBean2Map(sYSUserForm);
		in.setService("SYSUserServiceImpl");
		in.setMethod("checkUser");
		in.setParams(param);
		out = getOutputObject(in);
		if(out.getBean()==null){
			out.setReturnCode("0");
			out.setReturnMessage("登陆失败！请检查输入信息");
		}else{
			out.setReturnCode("1");
			getSession().setAttribute("userName", out.getBean().get("name"));
			getSession().setAttribute("station", out.getBean().get("station"));
			getSession().setAttribute("password", out.getBean().get("password"));
		}
		return out;
	}
	@ResponseBody
	@RequestMapping(value="login/changePass")
	public OutputObject changePass(HttpServletRequest request,HttpServletResponse response){
		OutputObject out = new OutputObject();
		InputObject in = new InputObject();
		Map<String,String> param =new HashMap<String,String>();
		String oldPass = request.getParameter("oldPass");
		String newPass = request.getParameter("newPass");
		if(!getSession().getAttribute("password").equals(oldPass)){
			out.setReturnCode("0");
			out.setReturnMessage("原密码错误");
			return out;
		}
		param.put("password", newPass);
		param.put("station", (String)getSession().getAttribute("station"));
		in.setService("SYSUserServiceImpl");
		in.setMethod("updateSYSUser");
		in.setParams(param);
		out = getOutputObject(in);
		if("1".equals(out.getReturnCode())){
			getSession().setAttribute("password",newPass);
		}
		return out;
	}
	
}
