package com.leo.service;

import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public interface IRegTicketService {
	
	/**
	 * 分页查询券码	 
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 根据ID查询券码
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getById(InputObject inputObject,OutputObject outputObject)throws Exception;
	/**
	 * 查询所有券码
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void getAll(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 插入一条新的券码
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void insertRegTicket(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 更新券码
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public void updateRegTicket(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 删除券码(预留)
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int deleteRegTicket(InputObject inputObject, OutputObject outputObject) throws Exception;
	/**
	 * 逻辑删除券码
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	public int logicDeleteRegTicket(InputObject inputObject, OutputObject outputObject) throws Exception;
	public void getByTicket(InputObject inputObject, OutputObject outputObject) throws Exception;
	public void getCommonTicket(InputObject inputObject, OutputObject outputObject) throws Exception;
	public void getOne(InputObject inputObject, OutputObject outputObject) throws Exception;
}
