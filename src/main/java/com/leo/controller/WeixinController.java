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
import com.github.sd4324530.fastweixin.message.req.BaseReqMsg;
import com.github.sd4324530.fastweixin.message.req.ImageReqMsg;
import com.github.sd4324530.fastweixin.message.req.MenuEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.message.req.VoiceReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import com.leo.util.ConfigHelper;
import com.leo.util.StringUtil;
import com.leo.util.UUIDGenerator;
import com.lfc.core.bean.OutputObject;

@Controller
@RequestMapping(value = "weixin")
public class WeixinController extends WeixinControllerSupport{
	protected static Logger logger = LoggerFactory.getLogger("WeixinController");
	private String appToken;
	private String appId;
	private String appSecret;
	private String sign_yet;
	private String sign_success;
	private String voice_reply;
	private String dh_reply;
	private String cz_reply;
	private String buy_success;
	private String buy_failed;
	private String query_money_sucss;
	private String query_moeny_fail;
	private String menu;
	private String subscribe;
	private int sign_charge;
	private String not_enough_money;
	
	@Autowired
	private WeixinUserController weixinUserController;
	@Autowired
	private RegTicketController regTicketController;
	
	public  WeixinController(){
		this.appToken=ConfigHelper.getValue("appToken");
		this.appId=ConfigHelper.getValue("appId");
		this.appSecret=ConfigHelper.getValue("appSecret");
		this.sign_yet=ConfigHelper.getValue("sign_yet");
		this.sign_success=ConfigHelper.getValue("sign_success");
		this.voice_reply=ConfigHelper.getValue("voice_reply");
		this.dh_reply=ConfigHelper.getValue("dh_reply");
		this.buy_success=ConfigHelper.getValue("buy_success");
		this.buy_failed=ConfigHelper.getValue("buy_failed");
		this.not_enough_money=ConfigHelper.getValue("not_enough_money");
		this.query_money_sucss=ConfigHelper.getValue("query_money_sucss");
		this.query_moeny_fail=ConfigHelper.getValue("query_moeny_fail");
		this.subscribe=ConfigHelper.getValue("subscribe");
		this.cz_reply=ConfigHelper.getValue("cz_reply");
		this.sign_charge=Integer.parseInt(ConfigHelper.getValue("sign_charge"));
		this.menu=ConfigHelper.getValue("menu").replace("[sign_charge]", String.valueOf(this.sign_charge));
	}
	/**
	 * 处理接收到的文本消息
	 */
	@Override
	protected BaseMsg handleTextMsg(TextReqMsg msg) {
		String msgContent = msg.getContent().trim();
		// 微信用户openId
		String openid = msg.getFromUserName();
		String replyMes = "";
		
		//保存用户回复信息
			if(msgContent.contains("签到")){
				replyMes = sign(msg);
			}else if(msgContent.endsWith("@购买")){
				//购买券码
				OutputObject out = regTicketController.getOne(null);
				if("0".equals(out.getReturnCode())){
					Map<String,Object> outMap = (Map<String,Object>)out.getObject();
					String ticketCode =(String)(outMap.get("ticketCode"));
					//扣除费用
					outMap.clear();
					OutputObject tempOut =weixinUserController.getByOpenId(openid);
					if("0".equals(tempOut.getReturnCode())){
						int money = (int)((Map<String,Object>)tempOut.getObject()).get("wallet");
						//这里将软件价格先写死为 20
						int softPrice = 20;
						if(money>=softPrice){
							money = money-20;
							outMap.put("openid", openid);
							outMap.put("wallet", money);
							tempOut = weixinUserController.updateByOpenid(outMap);
							if("0".equals(tempOut.getReturnCode())){
								replyMes = this.buy_success.replace("[ticketCode]", ticketCode);
								logger.info("用户购买成功，消费：20元 openid="+openid+"券码="+ticketCode);
							}else{
								logger.info("用户购买失败， openid="+openid+"券码="+ticketCode);
							}
						}else{
							replyMes = this.not_enough_money.replace("[money]", String.valueOf(softPrice-money)).replace("[softPrice]", String.valueOf(softPrice));
						}
					}else{
						replyMes=this.buy_failed;
					}
				}else{
					replyMes=this.buy_failed;
				}
			}else if(msgContent.endsWith("@余额")){
				OutputObject out =weixinUserController.getByOpenId(openid);
				if("0".equals(out.getReturnCode())){
					int money = (int)((Map<String,Object>)out.getObject()).get("wallet");
					replyMes=this.query_money_sucss.replace("[money]", String.valueOf(money));
				}else{
					replyMes=this.query_moeny_fail;
				}
			}else{
				replyMes = this.menu;
			}
		return new TextMsg(replyMes);
	}

	 @Override
	protected BaseMsg handleMenuClickEvent(MenuEvent event) {
		String fromUserName = event.getFromUserName();
		String eventKey = event.getEventKey();
		return null;
		

	}
	private String sign(BaseReqMsg msg){
		String replyMes ="";
		String openId = msg.getFromUserName();
		int sign_charge = this.sign_charge;
		int money = weixinUserController.signIn(openId,sign_charge);
		if(money==-999){
			replyMes = this.sign_yet;
		}else{
			this.sign_success=this.sign_success.replace("[charge]", String.valueOf(sign_charge)).replace("[money]", String.valueOf(money));
			replyMes=this.sign_success.replace("[money]", String.valueOf(money));
			logger.info("openId="+openId+replyMes);
		}
		return replyMes;
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
		if(StringUtil.isNotEmpty(content)){
			if(content.contains("签到")){
				replyMes=sign(msg);
			}else if(content.contains("软件")){
				replyMes ="亲，目前只注册豪迪普通版";
			}else{
				replyMes = this.voice_reply;
			}
		}else{
			replyMes = this.voice_reply;
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
		logger.info("关注成功--openid="+openId);
		return new TextMsg(this.subscribe);
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
		String openId = event.getFromUserName();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("openid", openId);
		map.put("unfollowDate", new Date());
		map.put("followStatus", "0");
		weixinUserController.updateByOpenid(map);
		logger.info("取关成功--openid="+openId);
		return new TextMsg("success");
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
