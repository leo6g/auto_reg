package com.leo.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.frame.bean.OutputObject;
import com.ai.frame.util.BeanUtil;
import com.leo.form.SYSUserForm;

/**
 * <h2></br>
 * 
 * @descript 
 * @author leo
 * @date 2017-03-09 13:26
 * @since JDK 1.7
 *
 */
@Controller
@RequestMapping("/")
public class SYSUserController extends BaseController{
	
	
	
	/**
	 * 跳转到系统用户列表页面
	 * @param request
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView list( ModelAndView mv) {
		mv.setViewName("weixin/suser-list");
		return mv;
	}
	/**
	 * 分页查询系统用户列表
	 * @param SYSUserForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getList")
	public OutputObject getList(@ModelAttribute("sYSUserForm") SYSUserForm sYSUserForm,
			BindingResult result,  Model model, ModelMap mm) {
		if (result.hasErrors()) {
			returnValidatorAjaxResult(result);
		}
		OutputObject outputObject = null;
		Map<String, String> map = BeanUtil.convertBean2Map(sYSUserForm);
		outputObject = getOutputObject(map, "sYSUserService", "getList");
		return outputObject;
	}
	/**
	 *根据ID查询系统用户
	 * @param SYSUserForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getById")
	public OutputObject getById(@ModelAttribute("SYSUserForm") SYSUserForm sYSUserForm,BindingResult result, Model model,ModelMap mm) {
		OutputObject outputObject = null;
		if (result.hasErrors()) {
			returnValidatorAjaxResult(result);
		}
		Map<String, String> map = BeanUtil.convertBean2Map(sYSUserForm);	
		outputObject = getOutputObject(map,"sYSUserService","getById");
		return outputObject;
	}
	/**
	 * 查看所有系统用户
	 * @param "SYSUserForm"
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAll")
	public OutputObject getAll(@ModelAttribute("SYSUserForm") SYSUserForm sYSUserForm,BindingResult result, Model model,ModelMap mm) {
		OutputObject outputObject = null;
		if (result.hasErrors()) {
			returnValidatorAjaxResult(result);
		}
		Map<String, String> map = BeanUtil.convertBean2Map(sYSUserForm);	
		outputObject = getOutputObject(map,"sYSUserService","getAll");
		return outputObject;
	}
	/**
	 * 添加系统用户
	 * @param SYSUserForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "insertSYSUser")
	public OutputObject insertSYSUser(@ModelAttribute("SYSUserForm") @Valid SYSUserForm sYSUserForm,BindingResult result, Model model,ModelMap mm) {
		if (result.hasErrors()) {
				return returnValidatorAjaxResult(result);
			}
			OutputObject outputObject = null;
			Map<String, String> map = BeanUtil.convertBean2Map(sYSUserForm);
			outputObject = getOutputObject(map, "sYSUserService", "insertSYSUser");
			if(outputObject.getReturnCode().equals("0")){
				outputObject.setReturnMessage("系统用户添加成功!");
			}
			return outputObject;
	}
	/**
	 * 编辑系统用户
	 * @param SYSUserForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateSYSUser")
	public OutputObject updateSYSUser(@ModelAttribute("SYSUserForm") @Valid SYSUserForm sYSUserForm,BindingResult result, Model model,ModelMap mm) {
		if (result.hasErrors()) {
			return returnValidatorAjaxResult(result);
		}
		OutputObject outputObject = null;
		Map<String, String> map = BeanUtil.convertBean2Map(sYSUserForm);
		outputObject = getOutputObject(map, "sYSUserService", "updateSYSUser");
		if(outputObject.getReturnCode().equals("0")){
			outputObject.setReturnMessage("系统用户编辑成功!");
		}
		return outputObject;
	}
	/**
	 * 去往添加页面
	 * @return
	 */
	@RequestMapping(value = "add")
	public ModelAndView add() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("weixin/add-suser");
		return mav;
	}
	/**
	 * 去往更新页面
	 * @return
	 */
	@RequestMapping(value = "edit")
	public ModelAndView edit(Model model) {
		ModelAndView mav=new ModelAndView();
		OutputObject outputObject = null;
		Map<String, String> map = new HashMap<String, String>();
		HttpServletRequest request = getRequest();
		map.put("id", request.getParameter("id"));
		outputObject = getOutputObject(map,"sYSUserService","getById");
		model.addAttribute("output", outputObject);
		mav.setViewName("weixin/edit-suser");
		return mav;
	}
	/**
	 * 删除系统用户
	 * @param departForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteSYSUser")
	public OutputObject deleteSYSUser(@ModelAttribute("SYSUserForm") SYSUserForm sYSUserForm,
			BindingResult result,  Model model, ModelMap mm) {
		if (result.hasErrors()) {
			return returnValidatorAjaxResult(result);
		}
		OutputObject outputObject = null;
		Map<String, String> map = BeanUtil.convertBean2Map(sYSUserForm);
		outputObject = getOutputObject(map, "sYSUserService", "deleteSYSUser");
		if(outputObject.getReturnCode().equals("0")){
			outputObject.setReturnMessage("系统用户删除成功!");
		}
		return outputObject;
	}
	/**
	 * 逻辑删除系统用户
	 * @param departForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "logicDeleteSYSUser")
	public OutputObject logicDeleteSYSUser(@ModelAttribute("SYSUserForm") SYSUserForm sYSUserForm,
			BindingResult result,  Model model, ModelMap mm) {
		if (result.hasErrors()) {
			return returnValidatorAjaxResult(result);
		}
		OutputObject outputObject = null;
		Map<String, String> map = BeanUtil.convertBean2Map(sYSUserForm);
		outputObject = getOutputObject(map, "sYSUserService", "logicDeleteSYSUser");
		if(outputObject.getReturnCode().equals("0")){
			outputObject.setReturnMessage("逻辑删除成功!");
		}
		return outputObject;
	}
	
}
