package com.leo.controller;

import java.io.IOException;
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
import com.leo.util.SendMailUtil;
import com.leo.util.StringUtil;
import com.leo.util.UUIDGenerator;
import com.lfc.core.bean.OutputObject;

@Controller
@RequestMapping("/front")
public class FrontController extends BaseController{
	@Autowired
	private RegRcordController regRcordController;
	@Autowired
	private WeixinUserController weixinUserController;
	@Autowired
	private WeixinController weixinController;
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
					RegRcordForm regRcordForm = new RegRcordForm();
					regRcordForm.setId(UUIDGenerator.getJavaUUID());
					regRcordForm.setMachineCode(machineCode);
					regRcordForm.setRegOrigin(regOrigin);
					regRcordForm.setRegTime(currentDate);
					regRcordForm.setTicketCode(ticketCode);
					regRcordForm.setTicketType((String)resultMap.get("ticketType"));
					regRcordForm.setRegCode(regCode);
					//判断是否注册成功
					if(StringUtil.isNotEmpty(regCode)&&regCode.length()>60){
						outputObject.setReturnMessage(regCode);
						outputObject.setReturnCode("1");
						//失效券码
						map.clear();
						map.put("available", '0');
						map.put("consumeTime", currentDate);
						map.put("id", resultMap.get("id"));
						getOutputObject(map, "regTicketService", "updateRegTicket");
						regRcordForm.setRegStatus("1");
					}else{
						outputObject.setReturnCode("0");
						if(regCode.contains("invalid")){
							outputObject.setReturnMessage("无效的机器码，请检查软件类型是否正确");
						}else{
							outputObject.setReturnMessage("请稍后再试.....");
						}
						regRcordForm.setRegStatus("0");
					}
					//插入注册记录
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
	
	@RequestMapping("/auth")
	public void auth(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=UTF-8");
		String outHtml = "<div style=\"text-align: center\"><span style=\"color: red;font-size: 12px;\" >绑定失败,可能您已绑定过邮箱</span><br><br><br><span><a href=\"http://120.92.149.186/front/business?page=index\">咨询官网</a></span> </div>";
		String openId = request.getParameter("openId");
		String emailAddr = request.getParameter("emailAddr");
		OutputObject outputObject = weixinUserController.getByOpenId(openId);
		Map<String,Object> map = (Map<String,Object>)outputObject.getObject();
		if(StringUtil.isNotEmpty(emailAddr)&&emailAddr.equalsIgnoreCase((String)map.get("emailAddr"))){
			if(map.get("isParticipate")==null||"0".equals((String)map.get("isParticipate"))){
				//修改参加活动的状态
				map.clear();
				map.put("openid", openId);
				map.put("isParticipate", '1');
				outputObject = weixinUserController.updateByOpenid(map);
				if("0".equals(outputObject.getReturnCode())){
					//修改成功 发送卷码
					String result = weixinController.superSend(emailAddr+"#send","45天 ");
					if("券码已发送".equals(result)){
						outHtml = "<div style=\"text-align: center\"><img src=\"../images/rightMark.png\"><span style=\"color: green;font-size: 12px;\" >恭喜，绑定成功 你免费获得一张券码 已发送您的邮箱，请及时查收</span><br><br><br><span><a href=\"http://120.92.149.186/front/business?page=index\">咨询官网</a></span> </div>";
					}
				}
			}
		}
		try {
			response.getWriter().println(outHtml);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
