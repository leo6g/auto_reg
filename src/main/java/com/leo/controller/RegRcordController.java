package com.leo.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.leo.form.RegRcordForm;
import com.leo.util.ActionUtil;

/**
 * <h2></br>
 * 
 * @descript 
 * @author leo
 * @date 2017-03-09 17:20
 * @since JDK 1.7
 *
 */
@Controller
@RequestMapping("")
public class RegRcordController extends BaseController{
	
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
	
	/**
	 * 跳转到注册流水信息列表页面
	 * @param request
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView list( ModelAndView mv) {
		mv.setViewName("weixin/grcord-list");
		return mv;
	}
	/**
	 * 分页查询注册流水信息列表
	 * @param RegRcordForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getList")
	public OutputObject getList(@ModelAttribute("regRcordForm") RegRcordForm regRcordForm,
			BindingResult result,  Model model, ModelMap mm) {
		if (result.hasErrors()) {
			returnValidatorAjaxResult(result);
		}
		OutputObject outputObject = null;
		Map<String, String> map = BeanUtil.convertBean2Map(regRcordForm);
		outputObject = getOutputObject(map, "regRcordService", "getList");
		return outputObject;
	}
	/**
	 *根据ID查询注册流水信息
	 * @param RegRcordForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getById")
	public OutputObject getById(@ModelAttribute("RegRcordForm") RegRcordForm regRcordForm,BindingResult result, Model model,ModelMap mm) {
		OutputObject outputObject = null;
		if (result.hasErrors()) {
			returnValidatorAjaxResult(result);
		}
		Map<String, String> map = BeanUtil.convertBean2Map(regRcordForm);	
		outputObject = getOutputObject(map,"regRcordService","getById");
		return outputObject;
	}
	/**
	 * 查看所有注册流水信息
	 * @param "RegRcordForm"
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAll")
	public OutputObject getAll(@ModelAttribute("RegRcordForm") RegRcordForm regRcordForm,BindingResult result, Model model,ModelMap mm) {
		OutputObject outputObject = null;
		if (result.hasErrors()) {
			returnValidatorAjaxResult(result);
		}
		Map<String, String> map = BeanUtil.convertBean2Map(regRcordForm);	
		outputObject = getOutputObject(map,"regRcordService","getAll");
		return outputObject;
	}
	/**
	 * 添加注册流水信息
	 * @param RegRcordForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "insertRegRcord")
	public OutputObject insertRegRcord(@ModelAttribute("RegRcordForm") @Valid RegRcordForm regRcordForm,BindingResult result, Model model,ModelMap mm) {
		if (result.hasErrors()) {
				return returnValidatorAjaxResult(result);
			}
			OutputObject outputObject = null;
			Map<String, String> map = BeanUtil.convertBean2Map(regRcordForm);
			outputObject = getOutputObject(map, "regRcordService", "insertRegRcord");
			if(outputObject.getReturnCode().equals("0")){
				outputObject.setReturnMessage("注册流水信息添加成功!");
			}
			return outputObject;
	}
	/**
	 * 编辑注册流水信息
	 * @param RegRcordForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateRegRcord")
	public OutputObject updateRegRcord(@ModelAttribute("RegRcordForm") @Valid RegRcordForm regRcordForm,BindingResult result, Model model,ModelMap mm) {
		if (result.hasErrors()) {
			return returnValidatorAjaxResult(result);
		}
		OutputObject outputObject = null;
		Map<String, String> map = BeanUtil.convertBean2Map(regRcordForm);
		outputObject = getOutputObject(map, "regRcordService", "updateRegRcord");
		if(outputObject.getReturnCode().equals("0")){
			outputObject.setReturnMessage("注册流水信息编辑成功!");
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
		mav.setViewName("weixin/add-grcord");
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
		outputObject = getOutputObject(map,"regRcordService","getById");
		model.addAttribute("output", outputObject);
		mav.setViewName("weixin/edit-grcord");
		return mav;
	}
	/**
	 * 删除注册流水信息
	 * @param departForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteRegRcord")
	public OutputObject deleteRegRcord(@ModelAttribute("RegRcordForm") RegRcordForm regRcordForm,
			BindingResult result,  Model model, ModelMap mm) {
		if (result.hasErrors()) {
			return returnValidatorAjaxResult(result);
		}
		OutputObject outputObject = null;
		Map<String, String> map = BeanUtil.convertBean2Map(regRcordForm);
		outputObject = getOutputObject(map, "regRcordService", "deleteRegRcord");
		if(outputObject.getReturnCode().equals("0")){
			outputObject.setReturnMessage("注册流水信息删除成功!");
		}
		return outputObject;
	}
	/**
	 * 逻辑删除注册流水信息
	 * @param departForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "logicDeleteRegRcord")
	public OutputObject logicDeleteRegRcord(@ModelAttribute("RegRcordForm") RegRcordForm regRcordForm,
			BindingResult result,  Model model, ModelMap mm) {
		if (result.hasErrors()) {
			return returnValidatorAjaxResult(result);
		}
		OutputObject outputObject = null;
		Map<String, String> map = BeanUtil.convertBean2Map(regRcordForm);
		outputObject = getOutputObject(map, "regRcordService", "logicDeleteRegRcord");
		if(outputObject.getReturnCode().equals("0")){
			outputObject.setReturnMessage("逻辑删除成功!");
		}
		return outputObject;
	}
	
}
