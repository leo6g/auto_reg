package com.leo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.frame.bean.InputObject;
import com.ai.frame.bean.OutputObject;
import com.leo.service.ISYSUserService ;
import com.leo.util.DateUtil;

public class SYSUserServiceImpl extends BaseServiceImpl implements ISYSUserService   {
	protected static Logger logger = LoggerFactory.getLogger("SYSUserServiceImpl");
	
	@Override
	public void checkUser(InputObject inputObject, OutputObject outputObject)
			throws Exception {
			Map<String,String> map = (Map<String,String> )getBaseDao().queryForObject("SYSUserMapper.checkUser", inputObject.getParams());
			outputObject.setBean(map);
			
	}
	@Override
	public void getList(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		List<Map<String, String>> list= getBaseDao().queryForList("SYSUserMapper.getList", inputObject.getParams());
		outputObject.setBeans(list);
		int totalcount = getBaseDao().getTotalCount("SYSUserMapper.queryUserCount", inputObject.getParams());
		outputObject.setObject(totalcount);
		 logger.info("getList success");
	}
	@Override
	public void getById(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		Object object=getBaseDao().queryForObject("SYSUserMapper.getById", inputObject.getParams());
		outputObject.setObject(object);

	}
	@Override
	public void getAll(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		inputObject.getParams().put("deleteFlag","0");
		List<Map<String,String>> list = getBaseDao().queryForList("SYSUserMapper.getAll", inputObject.getParams());
		outputObject.setBeans(list);
	
	}
	@Override
	public int insertSYSUser(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		String createTime = DateUtil.date2String(new Date(),DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
		//查询系统用户是否已经存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("SYSUserMapper.getByCode", inputObject.getParams());
	//	if(object==null){
			inputObject.getParams().put("deleteFlag", "0");
			inputObject.getParams().put("createTime", createTime);
			return getBaseDao().insert("SYSUserMapper.insert", inputObject.getParams());
	//	}else{
	//		outputObject.setReturnCode("-1");
	//		outputObject.setReturnMessage("系统用户已经存在，请修改!");
	//		return -1;
	//	}
	}

	@Override
	public int updateSYSUser(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		String updateTime = DateUtil.date2String(new Date(),DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
		//查询系统用户是否存在 有code验证时放开
		//Object object = getBaseDao().queryForObject("SYSUserMapper.getByCode", inputObject.getParams());
		//if(object==null){
			inputObject.getParams().put("updateTime", updateTime);
			return getBaseDao().update("SYSUserMapper.update", inputObject.getParams());
		//}else{
		//	outputObject.setReturnCode("-1");
		//	outputObject.setReturnMessage("系统用户已经存在，请修改!");
		//	return -1;
		//}

	}
	@Override
	public int deleteSYSUser(InputObject inputObject, OutputObject outputObject)
			throws Exception {
		return getBaseDao().delete("SYSUserMapper.delete", inputObject.getParams());
	}
	
	@Override
	public int logicDeleteSYSUser(InputObject inputObject,
			OutputObject outputObject) throws Exception {
		String updateTime = DateUtil.date2String(new Date(),DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
		inputObject.getParams().put("updateTime", updateTime);
		return getBaseDao().update("SYSUserMapper.logicDelete", inputObject.getParams());

	}
	

}
