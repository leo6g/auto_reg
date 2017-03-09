package com.leo.form;

import java.util.Date;
public class SYSUserForm extends BaseForm{

	/*
	字段注释：年龄
	列名称:AGE
	字段类型:int*/
	private int age;
	/*
	字段注释：名字
	列名称:NAME
	字段类型:varchar*/
	private String name;
	/*
	字段注释：id
	列名称:ID
	字段类型:varchar*/
	private String id;
	/*
	字段注释：最近登录时间
	列名称:LAST_LOGIN
	字段类型:timestamp*/
	private Date lastLogin;
	/*
	字段注释：pass
	列名称:PASS
	字段类型:varchar*/
	private String pass;
	/*
	字段注释：类型：1-普通，2-客服，3-超管
	列名称:TYPE
	字段类型:char*/
	private String type;
	/*
	字段注释：性别1-男，2-女，3-未知
	列名称:GENDER
	字段类型:char*/
	private String gender;
	/*
	字段注释：权限
	列名称:AUTHORITY
	字段类型:varchar*/
	private String authority;
	/*
	字段注释：状态：0-不可用，1-可用
	列名称:STATUS
	字段类型:char*/
	private String status;
	
	
	public void setAge(int age){
		this.age = age;
	}

	public int getAge(){
		return age;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setLastLogin(Date lastLogin){
		this.lastLogin = lastLogin;
	}

	public Date getLastLogin(){
		return lastLogin;
	}

	public void setPass(String pass){
		this.pass = pass;
	}

	public String getPass(){
		return pass;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setAuthority(String authority){
		this.authority = authority;
	}

	public String getAuthority(){
		return authority;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	
}