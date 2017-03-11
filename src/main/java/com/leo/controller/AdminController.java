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

import com.leo.form.SYSUserForm;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;
import com.lfc.core.util.BeanUtil;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{
	protected static Logger logger = LoggerFactory.getLogger("MainController");
	@RequestMapping("")
	public ModelAndView index(HttpServletRequest request,ModelAndView mv){
		mv.addObject("adminUser", getSession().getAttribute("adminUser"));
		String tab = request.getParameter("tab");
		mv.setViewName("admin/"+tab);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value="login.do")
	public OutputObject login(@ModelAttribute("sYSUserForm") SYSUserForm sYSUserForm,HttpServletRequest request,HttpServletResponse response
			,BindingResult result){
		if (result.hasErrors()) {
			returnValidatorAjaxResult(result);
		}
		OutputObject out = null;
		InputObject in = new InputObject();
		Map<String,Object> param = BeanUtil.convertBean2Map(sYSUserForm);
		in.setService("sYSUserService");
		in.setMethod("checkUser");
		in.setParams(param);
		out = getOutputObject(in);
		if(out.getBean()==null){
			out.setReturnCode("0");
			out.setReturnMessage("登陆失败！请检查输入信息");
		}else{
			out.setReturnCode("1");
			getSession().setAttribute("adminUser", out.getBean().get("name"));
			getSession().setAttribute("type", out.getBean().get("type"));
			getSession().setAttribute("gender", out.getBean().get("gender"));
			getSession().setAttribute("authority", out.getBean().get("authority"));
			getSession().setAttribute("status", out.getBean().get("status"));
		}
		return out;
	}
	@ResponseBody
	@RequestMapping(value="loginOut.do")
	public OutputObject loginOut(){
		OutputObject out = new OutputObject();
		try {
			getSession().removeAttribute("adminUser");
			getSession().removeAttribute("type");
			getSession().removeAttribute("gender");
			getSession().removeAttribute("authority");
			getSession().removeAttribute("status");
			out.setReturnCode("1");
		} catch (Exception e) {
			out.setReturnCode("0");
		}
		return out;
	}
	@ResponseBody
	@RequestMapping(value="login/changePass")
	public OutputObject changePass(HttpServletRequest request,HttpServletResponse response){
		OutputObject out = new OutputObject();
		InputObject in = new InputObject();
		Map<String,Object> param =new HashMap<String,Object>();
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
