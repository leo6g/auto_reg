package com.leo.serviceImpl;

import com.leo.dao.impl.BaseDaoImpl;
import com.leo.service.IBaseService;

public class BaseServiceImpl implements IBaseService {

	private BaseDaoImpl baseDao;

	public BaseDaoImpl getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDaoImpl baseDao) {
		this.baseDao = baseDao;
	}

}
