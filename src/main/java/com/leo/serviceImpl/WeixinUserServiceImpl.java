package com.leo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leo.service.IWeixinUserService ;
import com.leo.util.DateUtil;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public class WeixinUserServiceImpl extends BaseServiceImpl implements IWeixinUserService   {
	protected static Logger logger = LoggerFactory.getLogger("WeixinUserServiceImpl");
	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String, Object>> list= getBaseDao().queryForList("WeixinUserMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int totalcount = getBaseDao().getTotalCount("WeixinUserMapper.countAll", inputObject.getParams());
		outputObject.setObject(totalcount);
		 logger.info("getList success");
	}
	@Override
	public void getById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("WeixinUserMapper.getById", inputObject.getParams());
		outputObject.setObject(object);
		outputObject.setReturnCode("0");

	}
	@Override
	public void getByOpenId(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("WeixinUserMapper.getByOpenId", inputObject.getParams());
		outputObject.setObject(object);
		outputObject.setReturnCode("0");
	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("WeixinUserMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public void updateByOpenid(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("WeixinUserMapper.getByOpenId", inputObject.getParams());
		if(object!=null){
			getBaseDao().insert("WeixinUserMapper.updateByOpenid", inputObject.getParams());
			outputObject.setReturnCode("0");
		}else{
			outputObject.setReturnCode("0");
		}
	}
	@Override
	public void insertWeixinUser(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("WeixinUserMapper.getByOpenId", inputObject.getParams());
		if(object==null){
			getBaseDao().insert("WeixinUserMapper.insert", inputObject.getParams());
			outputObject.setReturnCode("0");
		}else{
			//不清空余额
			inputObject.getParams().remove("wallet");
			getBaseDao().insert("WeixinUserMapper.updateByOpenid", inputObject.getParams());
			outputObject.setReturnCode("0");
		}
	}

	@Override
	public void updateWeixinUser(InputObject inputObject,
			OutputObject outputObject) throws Exception {
			getBaseDao().update("WeixinUserMapper.update", inputObject.getParams());
			outputObject.setReturnCode("0");

	}
	@Override
	public int deleteWeixinUser(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("WeixinUserMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteWeixinUser(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		String updateTime = DateUtil.date2String(new Date(),DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
		inputObject.getParams().put("updateTime", updateTime);
		return getBaseDao().update("WeixinUserMapper.logicDelete", inputObject.getParams());

	}
	

}
