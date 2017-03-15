package com.leo.form;

import java.util.Date;
public class WeixinUserForm extends BaseForm{

	/*
	字段注释：余额
	列名称:WALLET
	字段类型:int*/
	private int wallet;
	/*
	字段注释：openid
	列名称:OPENID
	字段类型:varchar*/
	private String openid;
	/*
	字段注释：id
	列名称:ID
	字段类型:varchar*/
	private String id;
	/*
	字段注释：签到日期
	列名称:SIGN_DATE
	字段类型:date*/
	private Date signDate;
	/*
	字段注释：取关时间 
	列名称:UNFOLLOW_DATE
	字段类型:timestamp*/
	private Date unfollowDate;
	/*
	字段注释：关注时间 
	列名称:FOLLOW_DATE
	字段类型:timestamp*/
	private Date followDate;
	/*
	字段注释：关注状态
	列名称:FOLLOW_STATUS
	字段类型:char*/
	private String followStatus;
	
	
	public void setWallet(int wallet){
		this.wallet = wallet;
	}

	public int getWallet(){
		return wallet;
	}

	public void setOpenid(String openid){
		this.openid = openid;
	}

	public String getOpenid(){
		return openid;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setSignDate(Date signDate){
		this.signDate = signDate;
	}

	public Date getSignDate(){
		return signDate;
	}

	public void setUnfollowDate(Date unfollowDate){
		this.unfollowDate = unfollowDate;
	}

	public Date getUnfollowDate(){
		return unfollowDate;
	}

	public void setFollowDate(Date followDate){
		this.followDate = followDate;
	}

	public Date getFollowDate(){
		return followDate;
	}

	public void setFollowStatus(String followStatus){
		this.followStatus = followStatus;
	}

	public String getFollowStatus(){
		return followStatus;
	}

	
}