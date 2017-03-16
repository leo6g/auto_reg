package com.leo.form;

import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
public class RegTicketForm extends BaseForm{

	/*
	字段注释：券码
	列名称:TICKET_CODE
	字段类型:varchar*/
	private String ticketCode;
	/*
	字段注释：种类
	列名称:TICKET_TYPE
	字段类型:varchar*/
	private String ticketType;
	/*
	字段注释：id
	列名称:ID
	字段类型:varchar*/
	private String id;
	/*
	字段注释：价值
	列名称:PRICE_VALUE
	字段类型:float*/
	private Float priceValue;
	/*
	字段注释：消费时间-消费后券不可用
	列名称:CONSUME_TIME
	字段类型:timestamp*/
	private Date consumeTime;
	/*
	字段注释：售出时间
	列名称:USED_TIME
	字段类型:timestamp*/
	private Date usedTime;
	/*
	字段注释：生成时间
	列名称:CREATE_TIME
	字段类型:timestamp*/
	private Date createTime;
	/*
	字段注释：生成人
	列名称:CREATE_USER
	字段类型:varchar*/
	private String createUser;
	private String available;
	private String isSold;
	
	public String getIsSold() {
		return isSold;
	}

	public void setIsSold(String isSold) {
		this.isSold = isSold;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public void setTicketCode(String ticketCode){
		this.ticketCode = ticketCode;
	}

	public String getTicketCode(){
		return ticketCode;
	}

	public void setTicketType(String ticketType){
		this.ticketType = ticketType;
	}

	public String getTicketType(){
		return ticketType;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPriceValue(Float priceValue){
		this.priceValue = priceValue;
	}

	public Float getPriceValue(){
		return priceValue;
	}

	public void setConsumeTime(Date consumeTime){
		this.consumeTime = consumeTime;
	}

	public Date getConsumeTime(){
		return consumeTime;
	}

	public void setUsedTime(Date usedTime){
		this.usedTime = usedTime;
	}

	public Date getUsedTime(){
		return usedTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}

	public String getCreateUser(){
		return createUser;
	}

	
}