package com.leo.form;

import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
public class RegRcordForm extends BaseForm{

	/*
	字段注释：券码
	列名称:TICKET_CODE
	字段类型:varchar*/
	private String ticketCode;
	/*
	字段注释：顾客
	列名称:CONSTOMER
	字段类型:varchar*/
	private String constomer;
	/*
	字段注释：id
	列名称:ID
	字段类型:varchar*/
	private String id;
	/*
	字段注释：注册渠道
	列名称:REG_ORIGIN
	字段类型:char*/
	private String regOrigin;
	/*
	字段注释：注册状态
	列名称:REG_STATUS
	字段类型:char*/
	private String regStatus;
	/*
	字段注释：注册时间
	列名称:REG_TIME
	字段类型:timestamp*/
	private Date regTime;
	/*
	字段注释：机器码
	列名称:MACHINE_CODE
	字段类型:varchar*/
	private String machineCode;
	/*
	字段注释：注册码
	列名称:REG_CODE
	字段类型:varchar*/
	private String regCode;
	/*
	字段注释：券类型
	列名称:TICKET_TYPE
	字段类型:char*/
	private String ticketType;
	
	
	public void setTicketCode(String ticketCode){
		this.ticketCode = ticketCode;
	}

	public String getTicketCode(){
		return ticketCode;
	}

	public void setConstomer(String constomer){
		this.constomer = constomer;
	}

	public String getConstomer(){
		return constomer;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setRegOrigin(String regOrigin){
		this.regOrigin = regOrigin;
	}

	public String getRegOrigin(){
		return regOrigin;
	}

	public void setRegStatus(String regStatus){
		this.regStatus = regStatus;
	}

	public String getRegStatus(){
		return regStatus;
	}

	public void setRegTime(Date regTime){
		this.regTime = regTime;
	}

	public Date getRegTime(){
		return regTime;
	}

	public void setMachineCode(String machineCode){
		this.machineCode = machineCode;
	}

	public String getMachineCode(){
		return machineCode;
	}

	public void setRegCode(String regCode){
		this.regCode = regCode;
	}

	public String getRegCode(){
		return regCode;
	}

	public void setTicketType(String ticketType){
		this.ticketType = ticketType;
	}

	public String getTicketType(){
		return ticketType;
	}

	
}