package com.leo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leo.form.RegRcordForm;
import com.leo.util.ActionUtil;
import com.leo.util.StringUtil;
import com.leo.util.UUIDGenerator;
import com.lfc.core.bean.OutputObject;

@Controller
@RequestMapping("/front")
public class FrontController extends BaseController{
	@Autowired
	private RegRcordController regRcordController;
	protected static Logger logger = LoggerFactory.getLogger("FrontController");
	@RequestMapping("/business")
	public ModelAndView go_reg_code(HttpServletRequest request,ModelAndView mv){
		String page = request.getParameter("page");
		mv.setViewName("front/"+page);
		return mv;
	}
	
	@RequestMapping("/mobile/business")
	public ModelAndView gm_reg_code(HttpServletRequest request,ModelAndView mv){
		String page = request.getParameter("page");
		mv.setViewName("front/mobile/"+page);
		return mv;
	}
	@ResponseBody
	@RequestMapping("/regMachine")
	public OutputObject regMachine(HttpServletRequest request,HttpServletResponse response){
		String machineCode = request.getParameter("machineCode");
		String ticketCode = request.getParameter("ticketCode");
		String regOrigin = request.getParameter("regOrigin");
		String softType = request.getParameter("softType");
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
					String regCode = "";
					if("1".equals(softType)){
						regCode = ActionUtil.do1(machineCode);
						outputObject.setOption("机器码填写：888");
					}else{
						regCode = ActionUtil.do2(machineCode);
						outputObject.setOption("机器码填写：777");
					}
					outputObject.setReturnMessage(regCode);
					outputObject.setReturnCode("1");
					//失效券码
					map.clear();
					map.put("available", '0');
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
					regRcordController.insertRegRcord(regRcordForm);
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
	
	
	
}
