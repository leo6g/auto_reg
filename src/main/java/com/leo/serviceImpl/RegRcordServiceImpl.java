package com.leo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leo.service.IRegRcordService ;
import com.leo.util.DateUtil;
import com.lfc.core.bean.InputObject;
import com.lfc.core.bean.OutputObject;

public class RegRcordServiceImpl extends BaseServiceImpl implements IRegRcordService   {
	protected static Logger logger = LoggerFactory.getLogger("RegRcordServiceImpl");
	@Override
	public void getList(InputObject inputObject,           
			OutputObject outputObject) throws Exception {
		List<Map<String, Object>> list= getBaseDao().queryForList("RegRcordMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int totalcount = getBaseDao().getTotalCount("RegRcordMapper.queryUserCount", inputObject.getParams());
		outputObject.setObject(totalcount);
		 logger.info("getList success");
	}
	@Override
	public void getById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("RegRcordMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String, Object>> list = getBaseDao().queryForList("RegRcordMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertRegRcord(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		String createTime = DateUtil.date2String(new Date(),DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
		//查询注册流水信息是否已经存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("RegRcordMapper.getByCode", inputObject.getParams());
	//	if(object==null){
			inputObject.getParams().put("deleteFlag", "0");
			inputObject.getParams().put("createTime", createTime);
			return getBaseDao().insert("RegRcordMapper.insert", inputObject.getParams());
	//	}else{
	//		outputObject.setReturnCode("-1");
	//		outputObject.setReturnMessage("注册流水信息已经存在，请修改!");
	//		return -1;
	//	}
	}

	@Override
	public int updateRegRcord(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		String updateTime = DateUtil.date2String(new Date(),DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
		//查询注册流水信息是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("RegRcordMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", updateTime);
			return getBaseDao().update("RegRcordMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("注册流水信息已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteRegRcord(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("RegRcordMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteRegRcord(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		String updateTime = DateUtil.date2String(new Date(),DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
		inputObject.getParams().put("updateTime", updateTime);
		return getBaseDao().update("RegRcordMapper.logicDelete", inputObject.getParams());

	}
	

}
