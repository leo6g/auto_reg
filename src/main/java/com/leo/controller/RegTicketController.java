package com.leo.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.leo.form.RegTicketForm;
import com.leo.util.UUIDGenerator;
import com.lfc.core.bean.OutputObject;
import com.lfc.core.util.BeanUtil;

/**
 * <h2></br>
 * 
 * @descript 
 * @author leo
 * @date 2017-03-10 20:41
 * @since JDK 1.7
 *
 */
@Controller
@RequestMapping("/reg_ticket")
public class RegTicketController extends BaseController{
	/**
	 * 分页查询券码列表
	 * @param RegTicketForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getList")
	public List<Map<String, Object>> getList(@ModelAttribute("regTicketForm") RegTicketForm regTicketForm,
			BindingResult result,  Model model, ModelMap mm) {
		if (result.hasErrors()) {
			returnValidatorAjaxResult(result);
		}
		OutputObject outputObject = null;
		Map<String, Object> map = BeanUtil.convertBean2Map(regTicketForm);
		outputObject = getOutputObject(map, "regTicketService", "getList");
		return outputObject.getBeans();
	}
	/**
	 *根据ID查询券码
	 * @param RegTicketForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getById")
	public OutputObject getById(@ModelAttribute("RegTicketForm") RegTicketForm regTicketForm,BindingResult result, Model model,ModelMap mm) {
		OutputObject outputObject = null;
		if (result.hasErrors()) {
			returnValidatorAjaxResult(result);
		}
		Map<String, Object> map = BeanUtil.convertBean2Map(regTicketForm);	
		outputObject = getOutputObject(map,"regTicketService","getById");
		return outputObject;
	}
	/**
	 * 查看所有券码
	 * @param "RegTicketForm"
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAll")
	public OutputObject getAll(@ModelAttribute("RegTicketForm") RegTicketForm regTicketForm,BindingResult result, Model model,ModelMap mm) {
		OutputObject outputObject = null;
		if (result.hasErrors()) {
			returnValidatorAjaxResult(result);
		}
		Map<String, Object> map = BeanUtil.convertBean2Map(regTicketForm);	
		outputObject = getOutputObject(map,"regTicketService","getAll");
		return outputObject;
	}
	/**
	 * 添加券码
	 * @param RegTicketForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "insertRegTicket")
	public OutputObject insertRegTicket(@ModelAttribute("RegTicketForm") @Valid RegTicketForm regTicketForm,HttpServletRequest request) {
		OutputObject outputObject = null;
			String howMany = request.getParameter("howMany");
			Map<String, Object> map = BeanUtil.convertBean2Map(regTicketForm);
			map.put("createTime", new Date());
			map.put("available", "1");
			map.put("createUser", (String)getSession().getAttribute("adminUser"));
			for(int i =0;i<Integer.parseInt(howMany);i++){
				map.put("id", UUIDGenerator.getJavaUUID());
				map.put("ticketCode", UUIDGenerator.getTicket());
				outputObject = getOutputObject(map, "regTicketService", "insertRegTicket");
			}
			if(outputObject.getReturnCode().equals("0")){
				outputObject.setReturnMessage("券码添加成功!");
			}
			return outputObject;
	}
	/**
	 * 编辑券码
	 * @param RegTicketForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateRegTicket")
	public OutputObject updateRegTicket(@ModelAttribute("RegTicketForm") @Valid RegTicketForm regTicketForm) {
		String abc = regTicketForm.getAvailable();
		OutputObject outputObject = null;
		Map<String, Object> map = BeanUtil.convertBean2Map(regTicketForm);
		outputObject = getOutputObject(map, "regTicketService", "updateRegTicket");
		if(outputObject.getReturnCode().equals("0")){
			outputObject.setReturnMessage("成功激活");
			if("0".equals(abc)){
				outputObject.setReturnMessage("成功失效");
			}
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
		mav.setViewName("weixin/add-gticket");
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
		Map<String, Object> map = new HashMap<String, Object>();
		HttpServletRequest request = getRequest();
		map.put("id", request.getParameter("id"));
		outputObject = getOutputObject(map,"regTicketService","getById");
		model.addAttribute("output", outputObject);
		mav.setViewName("weixin/edit-gticket");
		return mav;
	}
	/**
	 * 删除券码
	 * @param departForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteRegTicket")
	public OutputObject deleteRegTicket(@ModelAttribute("RegTicketForm") RegTicketForm regTicketForm,
			BindingResult result,  Model model, ModelMap mm) {
		if (result.hasErrors()) {
			return returnValidatorAjaxResult(result);
		}
		OutputObject outputObject = null;
		Map<String, Object> map = BeanUtil.convertBean2Map(regTicketForm);
		outputObject = getOutputObject(map, "regTicketService", "deleteRegTicket");
		if(outputObject.getReturnCode().equals("0")){
			outputObject.setReturnMessage("券码删除成功!");
		}
		return outputObject;
	}
	/**
	 * 逻辑删除券码
	 * @param departForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "logicDeleteRegTicket")
	public OutputObject logicDeleteRegTicket(@ModelAttribute("RegTicketForm") RegTicketForm regTicketForm,
			BindingResult result,  Model model, ModelMap mm) {
		if (result.hasErrors()) {
			return returnValidatorAjaxResult(result);
		}
		OutputObject outputObject = null;
		Map<String, Object> map = BeanUtil.convertBean2Map(regTicketForm);
		outputObject = getOutputObject(map, "regTicketService", "logicDeleteRegTicket");
		if(outputObject.getReturnCode().equals("0")){
			outputObject.setReturnMessage("逻辑删除成功!");
		}
		return outputObject;
	}
	
}
