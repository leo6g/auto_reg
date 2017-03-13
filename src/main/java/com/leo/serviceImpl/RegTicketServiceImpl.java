package com.leo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leo.service.IRegTicketService;
import com.leo.util.DateUtil;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public class RegTicketServiceImpl extends BaseServiceImpl implements IRegTicketService   {
	private Logger logger = LoggerFactory.getLogger("BaseContoller");
	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String, Object>> list= getBaseDao().queryForList("RegTicketMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		 logger.info("getList success");
	}
	@Override
	public void getById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("RegTicketMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("RegTicketMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public void insertRegTicket(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			int count =getBaseDao().insert("RegTicketMapper.insert", inputObject.getParams());
			outputObject.setReturnCode("0");
	}

	@Override
	public void updateRegTicket(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			getBaseDao().update("RegTicketMapper.update", inputObject.getParams());
			outputObject.setReturnCode("0");
	}
	@Override
	public int deleteRegTicket(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("RegTicketMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteRegTicket(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		String updateTime = DateUtil.date2String(new Date(),DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
		inputObject.getParams().put("updateTime", updateTime);
		return getBaseDao().update("RegTicketMapper.logicDelete", inputObject.getParams());

	}
	

}
