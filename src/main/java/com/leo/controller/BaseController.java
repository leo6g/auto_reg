package com.leo.controller;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ai.frame.bean.InputObject;
import com.ai.frame.bean.OutputObject;
import com.lfc.core.util.ControlConstants;
import com.lfc.core.util.JsonUtil;

public class BaseController {
	@Autowired
	BeanFactory beanFactory;
	private Logger logger = LoggerFactory.getLogger("BaseContoller");
	public HttpServletRequest getRequest(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	public HttpSession getSession(){
		HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		return session;
	}
	public OutputObject getOutputObject(Map<String, String> params,String service, String method){
		OutputObject outObj =null;
		if(StringUtils.isNotEmpty(method)&&StringUtils.isNotEmpty(service)){
			InputObject inputObject = new InputObject();
			inputObject.setService(service);
			inputObject.setMethod(method);
			inputObject.setParams(params);
			outObj=execute(inputObject);
		}else{
			logger.info("SERVICE NAME OR METHOD NAME IS NULL!!!");
		}
		return outObj;
	}
	
	public OutputObject getOutputObject(InputObject inputObj){
		OutputObject outObj =null;
		String service = inputObj.getService();
		String method = inputObj.getMethod();
		if(StringUtils.isNotEmpty(method)&&StringUtils.isNotEmpty(service)){
			outObj = new OutputObject();
			outObj = execute(inputObj);
		}else{
			logger.info("SERVICE NAME OR METHOD NAME IS NULL!!!");
		}
		return outObj;
	}
	private OutputObject execute(InputObject inputObj){
		long start = System.currentTimeMillis();
		OutputObject outputObj = new OutputObject();
		String service = inputObj.getService();
		String method = inputObj.getMethod();
		try {
			logger.info("INVOKE START!", "service=" + inputObj.getService() + "| method=" + inputObj.getMethod()+"|input="+JsonUtil.convertObject2Json(inputObj)+"|COST="+(System.currentTimeMillis() - start)+"ms");
			Object obj = beanFactory.getBean(service);
			Method mth = obj.getClass().getMethod(method, InputObject.class,OutputObject.class);
			mth.invoke(obj, inputObj,outputObj);
			logger.info("INVOKE SUCCESS!", "service=" + inputObj.getService() + "| method=" + inputObj.getMethod()+"|output="+JsonUtil.convertObject2Json(outputObj)+"|COST="+(System.currentTimeMillis() - start)+"ms");
		} catch (Exception e) {
			logger.info("execute INVOKE ERROR！");
			e.printStackTrace();
		}
		return outputObj;
		
	}
	
	/* bean校验ajax返回 */
	protected OutputObject returnValidatorAjaxResult(BindingResult result) {
		getParamValidateLog(result);
		OutputObject outputObject = new OutputObject();
		outputObject.setReturnCode(ControlConstants.RETURN_CODE.SYSTEM_ERROR);
		outputObject.setReturnMessage("后台参数校验失败！");
		return outputObject;
	}

	/* bean校验String返回 */
	protected String returnValidatorStrResult(BindingResult result) {
		getParamValidateLog(result);
		return "error/500";
	}

	/* bean校验ModelAndView返回 */
	protected ModelAndView returnValidatorMavResult(BindingResult result) {
		getParamValidateLog(result);
		return new ModelAndView("error/500");
	}
	/**
	 * 输出参数校验错误信息
	 * @param result
	 */
	private void getParamValidateLog(BindingResult result)
	{
	List<ObjectError> ls=result.getAllErrors();  
    for (int i = 0; i < ls.size(); i++) {  
        logger.error("参数校验错误{}",ls.get(i)); 
    }  
	}
}
