package com.leo.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IWeixinUserService {
	
	/**
	 * 分页查询微信用户	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询微信用户
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有微信用户
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的微信用户
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void insertWeixinUser(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新微信用户
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void updateWeixinUser(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除微信用户(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteWeixinUser(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除微信用户
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteWeixinUser(InputObject inputObject, OutputObject outputObject) throws Exception;
	public void getByOpenId(InputObject inputObject, OutputObject outputObject) throws Exception;
}
