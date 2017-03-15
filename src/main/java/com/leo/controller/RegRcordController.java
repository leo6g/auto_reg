package com.leo.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leo.form.RegRcordForm;
import com.leo.util.ActionUtil;
import com.leo.util.StringUtil;
import com.leo.util.UUIDGenerator;
import com.lfc.core.bean.OutputObject;
import com.lfc.core.util.BeanUtil;

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
@RequestMapping("/reg_record")
public class RegRcordController extends BaseController{
	protected static Logger logger = LoggerFactory.getLogger("RegRcordController");
	@ResponseBody
	@RequestMapping("/regMachine")
	public OutputObject regMachine(HttpServletRequest request,HttpServletResponse response){
		String machineCode = request.getParameter("machineCode");
		String ticketCode = request.getParameter("ticketCode");
		String regOrigin = request.getParameter("regOrigin");
		OutputObject outputObject = new OutputObject();
		if(StringUtil.isEmpty(ticketCode)||StringUtil.isEmpty(machineCode)){
			return null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ticketCode", ticketCode);
		//检验券码
		outputObject = getOutputObject(map, "regTicketService", "getByTicket");
		Map<String,Object> resultMap = (Map<String,Object>)outputObject.getObject();
		if(resultMap!=null){
			if("1".equals((String)resultMap.get("available"))){
				try {
					Date currentDate = new Date();
					outputObject.setObject(null);
					//控制注册
					String regCode = ActionUtil.do2(machineCode);
					outputObject.setReturnCode("1");
					outputObject.setReturnMessage(regCode);
					outputObject.setOption("机器码填写：888");
					//失效券码
					map.clear();
					map.put("available", "0");
					map.put("consumeTime", currentDate);
					map.put("id", resultMap.get("id"));
					getOutputObject(map, "regTicketService", "updateRegTicket");
					RegRcordForm regRcordForm = new RegRcordForm();
					regRcordForm.setId(UUIDGenerator.getJavaUUID());
					regRcordForm.setMachineCode(machineCode);
					regRcordForm.setRegOrigin(regOrigin);
					regRcordForm.setRegStatus("1");
					regRcordForm.setRegTime(currentDate);
					regRcordForm.setTicketCode(ticketCode);
					regRcordForm.setTicketType((String)resultMap.get("ticketType"));
					regRcordForm.setRegCode(regCode);
					insertRegRcord(regRcordForm);
				} catch (Exception e) {
					outputObject.setReturnCode("0");
					outputObject.setReturnMessage("注册失败请联系QQ:2388937779");
					logger.error("注册时出现严重错误",e);
				}
			}else{
				outputObject.setReturnCode("0");
				outputObject.setReturnMessage("券码已使用");
			}
		}else{
			outputObject.setReturnCode("0");
			outputObject.setReturnMessage("无效的券码");
		}
		return outputObject;
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
	public Object getList(@ModelAttribute("regRcordForm") RegRcordForm regRcordForm,HttpServletRequest request) {
		OutputObject outputObject = null;
		String limit = request.getParameter("rows");
		String pageNo = request.getParameter("page");
		Map<String, Object> map = BeanUtil.convertBean2Map(regRcordForm);
//		map.put("limit", Integer.parseInt(limit));
//		map.put("start", (Integer.parseInt(pageNo)-1)*Integer.parseInt(limit));
		outputObject = getOutputObject(map, "regRcordService", "getList");
		map.clear();
		map.put("total", outputObject.getObject());
		map.put("rows", outputObject.getBeans());
		return map;
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
		Map<String, Object> map = BeanUtil.convertBean2Map(regRcordForm);	
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
		Map<String, Object> map = BeanUtil.convertBean2Map(regRcordForm);	
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
	public void insertRegRcord(RegRcordForm regRcordForm) {
			OutputObject outputObject = null;
			Map<String, Object> map = BeanUtil.convertBean2Map(regRcordForm);
			outputObject = getOutputObject(map, "regRcordService", "insertRegRcord");
			if(outputObject.getReturnCode().equals("1")){
				logger.info("注册流水信息添加成功!");
			}
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
		Map<String, Object> map = BeanUtil.convertBean2Map(regRcordForm);
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
		Map<String, Object> map = new HashMap<String, Object>();
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
		Map<String, Object> map = BeanUtil.convertBean2Map(regRcordForm);
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
		Map<String, Object> map = BeanUtil.convertBean2Map(regRcordForm);
		outputObject = getOutputObject(map, "regRcordService", "logicDeleteRegRcord");
		if(outputObject.getReturnCode().equals("0")){
			outputObject.setReturnMessage("逻辑删除成功!");
		}
		return outputObject;
	}
	
}
