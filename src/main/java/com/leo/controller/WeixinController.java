package com.leo.controller;

import java.util.Date;
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
import com.github.sd4324530.fastweixin.message.req.BaseReqMsg;
import com.github.sd4324530.fastweixin.message.req.ImageReqMsg;
import com.github.sd4324530.fastweixin.message.req.MenuEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.message.req.VoiceReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import com.leo.form.RegRcordForm;
import com.leo.form.RegTicketForm;
import com.leo.util.ConfigHelper;
import com.leo.util.PropertiesUtil;
import com.leo.util.SendMailUtil;
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
	private String charge_success;
	private String charge_failed;
	private String empty_chargeCode;
	private String emailContent;
	private int softPrice;
	@Autowired
	private WeixinUserController weixinUserController;
	@Autowired
	private RegTicketController regTicketController;
	@Autowired
	private RegRcordController regRcordController;
	
	public  WeixinController(){
		this.appToken=PropertiesUtil.getString("appToken");
		this.appId=PropertiesUtil.getString("appId");
		this.appSecret=PropertiesUtil.getString("appSecret");
		this.sign_yet=ConfigHelper.getValue("sign_yet");
		this.sign_success=ConfigHelper.getValue("sign_success");
		this.voice_reply=ConfigHelper.getValue("voice_reply");
		this.dh_reply=ConfigHelper.getValue("dh_reply");
		this.buy_success=ConfigHelper.getValue("buy_success");
		this.buy_failed=ConfigHelper.getValue("buy_failed");
		this.not_enough_money=ConfigHelper.getValue("not_enough_money");
		this.query_money_sucss=ConfigHelper.getValue("query_money_sucss");
		this.query_moeny_fail=ConfigHelper.getValue("query_moeny_fail");
		this.charge_success=ConfigHelper.getValue("charge_success");
		this.charge_failed=ConfigHelper.getValue("charge_failed");
		this.empty_chargeCode=ConfigHelper.getValue("empty_chargeCode");
		this.cz_reply=ConfigHelper.getValue("cz_reply");
		this.sign_charge=Integer.parseInt(ConfigHelper.getValue("sign_charge"));
		this.softPrice=Integer.parseInt(ConfigHelper.getValue("softPrice"));
		this.subscribe=ConfigHelper.getValue("subscribe").replace("[sign_charge]", String.valueOf(this.sign_charge));;
		this.menu=ConfigHelper.getValue("menu").replace("[sign_charge]", String.valueOf(this.sign_charge));
		this.emailContent=ConfigHelper.getValue("emailContent");
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
		if(msgContent.contains("签到")){
			replyMes = sign(msg);
		}else if(msgContent.contains("兑换")){
			replyMes = exchangeCode(openid);
		}else if(msgContent.contains("余额")){
			replyMes = queryBalance(openid);
		}else if(msgContent.endsWith("@充值")){
			replyMes = chargeBalance(msgContent, openid);
		}else if(msgContent.endsWith("#buy")){
			replyMes = superBuy(msgContent);
		}else if(msgContent.endsWith("#send")){
			replyMes = superSend(msgContent);
		}else if(msgContent.endsWith("@zhzcm")){
			replyMes = regCodeFindBack(msgContent);
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
	 /**
	  * 注册码找回
	  * @param msgContent
	  * @return
	  */
	 private String regCodeFindBack(String msgContent){
		String replyMes = "";
		String ticketCode = msgContent.split("@")[0];
		if(StringUtil.isNotEmpty(ticketCode)){
			RegRcordForm regRcordForm = new RegRcordForm();
			regRcordForm.setTicketCode(ticketCode);
			regRcordForm.setLimit(1);
			regRcordForm.setStart(0);
			Map<String, Object> resultMap = (Map<String, Object>)regRcordController.getList(regRcordForm);
			List<Map<String, Object>> rows = (List<Map<String, Object>>)resultMap.get("rows");
			if(rows.size()>0){
				String regCode = (String)(rows.get(0).get("regCode"));
				if(StringUtil.isNotEmpty(regCode)&&regCode.length()>60){
					replyMes = "该券码注册过的注册码为:"+regCode;
				}else{
					replyMes="该券码注册时遇到问题，请联系客服QQ2388937779解决";
				}
			}else{
				replyMes="该券码貌似还没有被使用呐，请检查";
			}
		}else{
			replyMes = "券码不能为空";
		}
		return replyMes;
	 }
	 /**
	  * 超级发送
	  * @param msgContent
	  * @return
	  */
	 private String superSend(String msgContent){
		String replyMes = "";
		//购买券码
		String mailAddr = msgContent.split("#")[0];
		if(!StringUtil.isEmpty(mailAddr)){
			//购买券码
			OutputObject out = regTicketController.getTicket(null,"1");
			if("0".equals(out.getReturnCode())){
				Map<String,Object> outMap = (Map<String,Object>)out.getObject();
				String ticketCode =(String)(outMap.get("ticketCode"));
				String sendContent=emailContent.replace("[ticketCode]", ticketCode);
				if(SendMailUtil.sendMail(sendContent, "您的券码，请查收", mailAddr)){
					replyMes = "券码已发送";
				}else{
					replyMes = "券码发送失败";
				}
				
			}
		}else{
			replyMes = "邮箱不能为空";
		}
		return replyMes;
	 }
	 /**
	  * 超级购买
	  * @param msgContent
	  * @return
	  */
	 private String superBuy(String msgContent){
		 String replyMes = "";
			//购买券码
			String price = msgContent.split("#")[0];
			if(!StringUtil.isEmpty(price)){
				OutputObject out = regTicketController.getTicket(Integer.parseInt(price),"2");
				if("0".equals(out.getReturnCode())){
					Map<String,Object> outMap = (Map<String,Object>)out.getObject();
					String ticketCode =(String)(outMap.get("ticketCode"));
					replyMes = ticketCode+"@充值";
				}else{
					replyMes = "爸爸 对不起,获取现金卷时 失败了";
				}
			}else{
				replyMes = "爸爸,说下价格";
			}
		return replyMes;
	 }
	/**
	 * 充值
	 * @param msgContent
	 * @param openid
	 * @return
	 */
	 private String chargeBalance(String msgContent,String openid){
	 	String replyMes = "";
		String chargeCode = msgContent.split("@")[0];
		if(StringUtil.isEmpty(chargeCode)){
			replyMes =this.empty_chargeCode;
		}else{
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("available", '1');
			map.put("ticketCode", chargeCode);
			map.put("ticketType", "2");
			OutputObject out = regTicketController.getOne(map);
			Object obj = out.getObject();
			if(obj!=null){
				//验证成功 开始充值
				int chargeMoney = (int)((Map<String, Object>)obj).get("priceValue");
				String id = (String)((Map<String, Object>)obj).get("id");
				OutputObject tempOut = weixinUserController.userCharge2(openid, chargeMoney);
				//失效现金券
				RegTicketForm  regTicketForm = new RegTicketForm();
				regTicketForm.setId(id);
				regTicketForm.setAvailable("0");
				regTicketForm.setUsedTime(new Date());
				regTicketController.updateRegTicket(regTicketForm);
				replyMes = this.charge_success.replace("[chargeMoney]", String.valueOf(chargeMoney)).replace("[money]", tempOut.getReturnMessage());
			}else{
				replyMes=this.charge_failed;
			}
		}
			return replyMes;
	 }
	 /**
	  * 查询余额
	  * @param openid
	  * @return
	  */
	 private String queryBalance(String openid){
		String replyMes = "";
		OutputObject out =weixinUserController.getByOpenId(openid);
		if("0".equals(out.getReturnCode())){
			int money = (int)((Map<String,Object>)out.getObject()).get("wallet");
			replyMes=this.query_money_sucss.replace("[money]", String.valueOf(money));
		}else{
			replyMes=this.query_moeny_fail;
		}
		return replyMes;
	 }
	/**
	 * 兑换
	 * @param openid
	 * @return
	 */
	private String exchangeCode(String openid){
		String replyMes ="";
		OutputObject tempOut =weixinUserController.getByOpenId(openid);
		if("0".equals(tempOut.getReturnCode())){
			Map<String,Object> outMap = (Map<String,Object>)tempOut.getObject();
			int money = 0;
			if(outMap!=null&&outMap.get("wallet")!=null){
				money = (int)outMap.get("wallet");
			}
			//扣除费用
			if(money>=softPrice){
				money = money-softPrice;
				outMap.clear();
				outMap.put("openid", openid);
				outMap.put("wallet", money);
				tempOut = weixinUserController.updateByOpenid(outMap);
				if("0".equals(tempOut.getReturnCode())){
					//获取券码
					OutputObject out = regTicketController.getTicket(null,"1");
					if("0".equals(out.getReturnCode())){
						String ticketCode =(String)(((Map<String,Object>)out.getObject()).get("ticketCode"));
						replyMes = this.buy_success.replace("[ticketCode]", ticketCode);
						logger.info("用户兑换成功，消费：20元 openid="+openid+"券码="+ticketCode);
					}else{
						logger.info("用户兑换失败，费用已扣除："+softPrice+" openid="+openid+"请检查");
						replyMes="兑换出错，费用已扣除，请及时联系客服qq2388937779";
					}
				}else{
					logger.info("用户兑换失败， openid="+openid+"原因：扣除费用时失败");
					replyMes=this.buy_failed;
				}
			}else{
				replyMes = this.not_enough_money.replace("[money]", String.valueOf(softPrice-money)).replace("[softPrice]", String.valueOf(softPrice));
			}
		}else{
			replyMes=this.buy_failed;
		}
		return replyMes;
	}
	private String sign(BaseReqMsg msg){
		String replyMes ="";
		String openId = msg.getFromUserName();
		int sign_charge = this.sign_charge;
		int money = weixinUserController.signIn(openId,sign_charge);
		if(money==-999){
			replyMes = this.sign_yet;
		}else{
			replyMes=this.sign_success.replace("[charge]", String.valueOf(sign_charge)).replace("[money]", String.valueOf(money));
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
