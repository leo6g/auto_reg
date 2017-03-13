package com.leo.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IRegRcordService {
	
	/**
	 * 分页查询注册流水信息	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询注册流水信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有注册流水信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的注册流水信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void insertRegRcord(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新注册流水信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int updateRegRcord(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除注册流水信息(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteRegRcord(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除注册流水信息
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteRegRcord(InputObject inputObject, OutputObject outputObject) throws Exception;
}
