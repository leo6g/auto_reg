package com.leo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.ImageReqMsg;
import com.github.sd4324530.fastweixin.message.req.MenuEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.message.req.VoiceReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import com.leo.util.PropertiesUtil;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

@Controller
@RequestMapping(value = "weixin")
public class WeixinController extends WeixinControllerSupport{

	private String appToken;
	private String appId;
	private String appSecret;
	@Autowired
	private HttpServletRequest request;
	public  WeixinController(){
		this.appToken=PropertiesUtil.getString("appToken");
		this.appId=PropertiesUtil.getString("appId");
		this.appSecret=PropertiesUtil.getString("appSecret");
	}
	/**
	 * 处理接收到的文本消息
	 */
	@Override
	protected BaseMsg handleTextMsg(TextReqMsg msg) {
		String msgContent = msg.getContent();
		// 微信用户openId
		String openId = msg.getFromUserName();
		//保存用户回复信息
		System.out.println(openId);
		if(!"".equals(msgContent.trim())){
			Map<String, Object> map = new HashMap<String, Object>();
			//根据openId查询用户信息
			map.put("wxUserId", openId);
			map.put("msg", msgContent);
			
			
			//保存用户回复信息时用多线程来缓解服务器压力 beg
//			inputObject.setParams(map);
//			inputObject.setService("userMessageService");
//			inputObject.setMethod("insertUserMessage");
//			HandleTextMsgThread thread = new HandleTextMsgThread(inputObject);
//			thread.setControlService(controlService);
//			thread.start();
			//保存用户回复信息时用多线程来缓解服务器压力 end
			
			
			
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyWord", msgContent.trim());
		
		return new TextMsg("你好");
	}

	protected Logger logger = LoggerFactory.getLogger(getClass());


	 @Override
	protected BaseMsg handleMenuClickEvent(MenuEvent event) {
		String fromUserName = event.getFromUserName();
		String eventKey = event.getEventKey();
		return null;
		

	}
	

	/**
	 * 处理语音消息，有需要时子类重写
	 *
	 * @param msg
	 *            请求消息对象
	 * @return 响应消息对象
	 */
	@Override
	protected BaseMsg handleVoiceMsg(VoiceReqMsg msg) {
		return new TextMsg("您说的是：" + msg.getRecognition());
	}

	/**
	 * 处理图片消息，有需要时子类重写
	 *
	 * @param msg
	 *            请求消息对象
	 * @return 响应消息对象
	 */
	@Override
	protected BaseMsg handleImageMsg(ImageReqMsg imageMessage) {
		return new TextMsg("这是个图片消息");
	}

	/**
	 * 处理添加关注事件
	 * 
	 * @param event
	 *            添加关注事件对象
	 * @return 响应消息对象
	 */
	protected BaseMsg handleSubscribe(BaseEvent event,String eventKey) {
		logger.info("----------------关注事件----------------");
		// 添加关注用户到数据库
		String opendId = event.getFromUserName();

		return new TextMsg("欢迎关注");
		// 文本消息回复语

	}

	/**
	 * 获取微信 accesstoken
	 * 
	 * @return
	 */
	public String getAccessToken() {
		String accessToken = "";
		return accessToken;
	}


	/**
	 * 处理取消关注事件，有需要时子类重写
	 * 
	 * @param event
	 *            取消关注事件对象
	 * @return 响应消息对象
	 */
	protected BaseMsg handleUnsubscribe(BaseEvent event) {
		logger.info("-----------------取消关注---------------");
		Map<String, Object> userMap = new HashMap<String, Object>();
		// openId
		String openId = event.getFromUserName();
		
		return null;
	}
	/**
	 * 关注语回复
	 * 
	 * @param templateId
	 *            模板ID
	 * @param type
	 *            类型 text为文本 news为图文
	 * @return BaseMsg
	 */
	protected BaseMsg createMsgByTemplateId(String templateId, String type) {

		return null;
	}

	@Override
	protected String getToken() {
		return this.appToken;
	}

	@RequestMapping(value = "forward")
	public void forward(HttpServletRequest request ,HttpServletResponse response){
		
		System.out.println(request.getParameter("code"));
	}

	
}
