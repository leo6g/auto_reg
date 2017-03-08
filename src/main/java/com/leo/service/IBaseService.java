package com.leo.service;

import com.leo.dao.impl.BaseDaoImpl;

public interface IBaseService {

	BaseDaoImpl getBaseDao();

	void setBaseDao(BaseDaoImpl baseDao);

}
