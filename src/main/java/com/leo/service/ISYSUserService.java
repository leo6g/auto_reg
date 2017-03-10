package com.leo.service;

import com.ai.frame.bean.InputObject;
import com.ai.frame.bean.OutputObject;

public interface ISYSUserService {
	
	/**
	 * 分页查询系统用户	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询系统用户
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有系统用户
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的系统用户
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int insertSYSUser(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新系统用户
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateSYSUser(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除系统用户(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteSYSUser(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除系统用户
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteSYSUser(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * check user
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void checkUser(InputObject inputObject, OutputObject outputObject) throws Exception;
}
