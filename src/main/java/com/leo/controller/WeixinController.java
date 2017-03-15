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

import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.ImageReqMsg;
import com.github.sd4324530.fastweixin.message.req.MenuEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.message.req.VoiceReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import com.leo.util.PropertiesUtil;
import com.leo.util.StringUtil;
import com.leo.util.UUIDGenerator;

@Controller
@RequestMapping(value = "weixin")
public class WeixinController extends WeixinControllerSupport{
	protected static Logger logger = LoggerFactory.getLogger("WeixinController");
	private String appToken;
	private String appId;
	private String appSecret;
	@Autowired
	private WeixinUserController weixinUserController;
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
		String msgContent = msg.getContent().trim();
		// 微信用户openId
		String openId = msg.getFromUserName();
		String replyMes = "";
		
		//保存用户回复信息
			if("签到".equals(msgContent)){
				int charge = 2;
				int money = weixinUserController.signIn(openId,charge);
				if(money==-999){
					replyMes = "今天已签到，明天再来吧";
				}else{
					replyMes = "恭喜，签到成功\n充值: "+charge+" 元\n余额："+money+"元";
				}
			}else if(msgContent.endsWith("@DH")){
				replyMes = "暂不支持兑换";
			}else if(msgContent.endsWith("@CZ")){
				replyMes = "暂不支持充值";
			}else{
				replyMes = "菜单：\n\n<a href=\"http://5be84764.ittun.com/auto_reg/front/business?page=reg_code\">注册/微笑</a>\n\ntips:每天签到可充值 2 元，余额可用来注册软件\n回复'签到'，可签到--支持语音说签到哦";
			}
		return new TextMsg(replyMes);
	}

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
		String replyMes = "";
		String content = msg.getRecognition();
		if(StringUtil.isNotEmpty(content)&&content.contains("签到")){
			String openId = msg.getFromUserName();
			int charge = 2;
			int money = weixinUserController.signIn(openId,charge);
			if(money==-999){
				replyMes = "今天已签到，明天再来吧";
			}else{
				replyMes = "恭喜，签到成功\n充值: "+charge+" 元\n余额："+money+"元";
			}
		}else{
			replyMes = "小咖不懂，说点别的吧，如\"签到\"";
		}
		return new TextMsg(replyMes);
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
	protected BaseMsg handleSubscribe(BaseEvent event) {
		// 添加关注用户到数据库
		String openId = event.getFromUserName();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id", UUIDGenerator.getJavaUUID());
		map.put("openid", openId);
		map.put("followDate", new Date());
		map.put("followStatus", "1");
		map.put("wallet", 0);
		weixinUserController.insertWeixinUser(map);
		logger.info("关注成功--openid:"+openId);
		return new TextMsg("感谢关注/微笑\n");
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
