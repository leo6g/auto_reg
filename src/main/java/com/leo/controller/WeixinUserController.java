package com.leo.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.leo.form.WeixinUserForm;
import com.lfc.core.bean.OutputObject;
import com.lfc.core.util.BeanUtil;

/**
 * <h2></br>
 * 
 * @descript 
 * @author leo
 * @date 2017-03-15 15:42
 * @since JDK 1.7
 *
 */
@Controller
@RequestMapping("/admin/wei_user")
public class WeixinUserController extends BaseController{
	protected static Logger logger = LoggerFactory.getLogger("WeixinUserController");
	
	
	/**
	 * 跳转到微信用户列表页面
	 * @param request
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView list( ModelAndView mv) {
		mv.setViewName("weixin/ixinuser-list");
		return mv;
	}
	/**
	 * 分页查询微信用户列表
	 * @param WeixinUserForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getList")
	public Object getList(@ModelAttribute("weixinUserForm") WeixinUserForm weixinUserForm) {
		OutputObject outputObject = null;
		Map<String, Object> map = BeanUtil.convertBean2Map(weixinUserForm);
		map.put("orderByClause", "FOLLOW_DATE DESC");
		outputObject = getOutputObject(map, "weixinUserService", "getList");
		map.clear();
		map.put("total", outputObject.getObject());
		map.put("rows", outputObject.getBeans());
		return map;
	}
	/**
	 *根据ID查询微信用户
	 * @param WeixinUserForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getById")
	public OutputObject getById(@ModelAttribute("WeixinUserForm") WeixinUserForm weixinUserForm,BindingResult result, Model model,ModelMap mm) {
		OutputObject outputObject = null;
		if (result.hasErrors()) {
			returnValidatorAjaxResult(result);
		}
		Map<String, Object> map = BeanUtil.convertBean2Map(weixinUserForm);	
		outputObject = getOutputObject(map,"weixinUserService","getById");
		return outputObject;
	}
	@ResponseBody
	@RequestMapping(value = "getByOpenId")
	public OutputObject getByOpenId(String openId) {
		OutputObject outputObject = null;
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("openid", openId);
		outputObject = getOutputObject(map,"weixinUserService","getByOpenId");
		return outputObject;
	}
	/**
	 * 查看所有微信用户
	 * @param "WeixinUserForm"
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getAll")
	public OutputObject getAll(@ModelAttribute("WeixinUserForm") WeixinUserForm weixinUserForm,BindingResult result, Model model,ModelMap mm) {
		OutputObject outputObject = null;
		if (result.hasErrors()) {
			returnValidatorAjaxResult(result);
		}
		Map<String, Object> map = BeanUtil.convertBean2Map(weixinUserForm);	
		outputObject = getOutputObject(map,"weixinUserService","getAll");
		return outputObject;
	}
	/**
	 * 添加微信用户
	 * @param WeixinUserForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	public OutputObject insertWeixinUser(Map<String, Object> map) {
			OutputObject outputObject = getOutputObject(map, "weixinUserService", "insertWeixinUser");
			if("0".equals(outputObject.getReturnCode())){
				outputObject.setReturnMessage("微信用户添加成功!");
			}
			return outputObject;
	}
	public OutputObject updateByOpenid(Map<String, Object> map) {
		OutputObject outputObject = getOutputObject(map, "weixinUserService", "updateByOpenid");
		if("0".equals(outputObject.getReturnCode())){
			outputObject.setReturnMessage("微信用户修改成功!");
		}
		return outputObject;
}
	/**
	 * 编辑微信用户
	 * @param WeixinUserForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateWeixinUser")
	public OutputObject updateWeixinUser(@ModelAttribute("WeixinUserForm") @Valid WeixinUserForm weixinUserForm,BindingResult result, Model model,ModelMap mm) {
		if (result.hasErrors()) {
			return returnValidatorAjaxResult(result);
		}
		OutputObject outputObject = null;
		Map<String, Object> map = BeanUtil.convertBean2Map(weixinUserForm);
		outputObject = getOutputObject(map, "weixinUserService", "updateWeixinUser");
		if(outputObject.getReturnCode().equals("0")){
			outputObject.setReturnMessage("微信用户编辑成功!");
		}
		return outputObject;
	}
	//后台充值
	@ResponseBody
	@RequestMapping(value = "userCharge")
	public OutputObject userCharge(@ModelAttribute("WeixinUserForm") @Valid WeixinUserForm weixinUserForm,HttpServletRequest request) {
		OutputObject outputObject = null;
		Map<String, Object> paramsMap = BeanUtil.convertBean2Map(weixinUserForm);
		int chargeMoney = Integer.parseInt(request.getParameter("money"));
		outputObject = getOutputObject(paramsMap,"weixinUserService","getById");
		if("0".equals(outputObject.getReturnCode())){
			//原有金额
			int money = (int)((Map<String,Object>)outputObject.getObject()).get("wallet");
			String openid = (String)((Map<String,Object>)outputObject.getObject()).get("openid");
			chargeMoney = money+chargeMoney;
			//修改金额
			paramsMap.put("wallet", chargeMoney);
			outputObject = getOutputObject(paramsMap, "weixinUserService", "updateWeixinUser");
			outputObject.setReturnMessage("充值成功");
			logger.info("充值成功--openid="+openid+"充值金额："+chargeMoney+"余额："+chargeMoney);
		}
		return outputObject;
	}
	//微信命令充值
	public OutputObject userCharge2(String openid,int chargeMoney) {
		OutputObject outputObject = null;
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("openid", openid);
		outputObject = getOutputObject(paramsMap,"weixinUserService","getByOpenId");
		if("0".equals(outputObject.getReturnCode())){
			//原有金额
			int money = (int)((Map<String,Object>)outputObject.getObject()).get("wallet");
			money = money+chargeMoney;
			//修改金额
			paramsMap.put("wallet", money);
			outputObject = getOutputObject(paramsMap, "weixinUserService", "updateByOpenid");
			outputObject.setReturnMessage(String.valueOf(money));
			logger.info("充值成功--openid="+openid+"充值金额："+chargeMoney+"余额："+money);
		}
		return outputObject;
	}
	public int signIn(String openId,int charge) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("openid", openId);
		OutputObject outputObject = getOutputObject(paramMap, "weixinUserService", "getByOpenId");
		Map<String,Object> tempMap = (Map<String,Object>)outputObject.getObject();
		if(tempMap!=null){
			if(tempMap.get("signDate")!=null){
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String lastSignDate = sf.format((Date)tempMap.get("signDate"));
				String signDate = sf.format(new Date());
				if(!signDate.equals(lastSignDate)){
					charge = doSignIn(tempMap, paramMap, openId, charge);
					return charge;
				}else{
					return -999;
				}
			}else{
				charge = doSignIn(tempMap, paramMap, openId, charge);
				return charge;
			}
		}else{
			logger.info("签到失败--无此用户-openid"+openId);
			return 0;
		}
	}
	private int doSignIn(Map<String,Object> tempMap,Map<String, Object> paramMap,String openId,int charge){
		int money = (int)tempMap.get("wallet");
		charge += money;
		logger.info("签到-openid:"+openId+"-奖励:"+money+"元"+"-余额应为："+charge+"元");
		paramMap.put("id", (String)tempMap.get("id"));
		paramMap.remove("openid");
		paramMap.put("wallet",charge);
		paramMap.put("signDate",new Date());
		OutputObject outputObject = getOutputObject(paramMap, "weixinUserService", "updateWeixinUser");
		if(outputObject.getReturnCode().equals("0")){
			logger.info("签到成功-openid"+openId);
		}
		return charge;
	}
	/**
	 * 去往添加页面
	 * @return
	 */
	@RequestMapping(value = "add")
	public ModelAndView add() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("weixin/add-ixinuser");
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
		outputObject = getOutputObject(map,"weixinUserService","getById");
		model.addAttribute("output", outputObject);
		mav.setViewName("weixin/edit-ixinuser");
		return mav;
	}
	/**
	 * 删除微信用户
	 * @param departForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteWeixinUser")
	public OutputObject deleteWeixinUser(@ModelAttribute("WeixinUserForm") WeixinUserForm weixinUserForm,
			BindingResult result,  Model model, ModelMap mm) {
		if (result.hasErrors()) {
			return returnValidatorAjaxResult(result);
		}
		OutputObject outputObject = null;
		Map<String, Object> map = BeanUtil.convertBean2Map(weixinUserForm);
		outputObject = getOutputObject(map, "weixinUserService", "deleteWeixinUser");
		if(outputObject.getReturnCode().equals("0")){
			outputObject.setReturnMessage("微信用户删除成功!");
		}
		return outputObject;
	}
	/**
	 * 逻辑删除微信用户
	 * @param departForm
	 * @param result
	 * @param request
	 * @param model
	 * @param mm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "logicDeleteWeixinUser")
	public OutputObject logicDeleteWeixinUser(@ModelAttribute("WeixinUserForm") WeixinUserForm weixinUserForm,
			BindingResult result,  Model model, ModelMap mm) {
		if (result.hasErrors()) {
			return returnValidatorAjaxResult(result);
		}
		OutputObject outputObject = null;
		Map<String, Object> map = BeanUtil.convertBean2Map(weixinUserForm);
		outputObject = getOutputObject(map, "weixinUserService", "logicDeleteWeixinUser");
		if(outputObject.getReturnCode().equals("0")){
			outputObject.setReturnMessage("逻辑删除成功!");
		}
		return outputObject;
	}
	
}
