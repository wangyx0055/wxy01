package com.longersec.blj.dao;

import com.longersec.blj.domain.ConfigWorkorder;

public interface ConfigWorkorderDao {

	boolean editConfigWorkorder(ConfigWorkorder configWorkorder);

	ConfigWorkorder getById(Integer id);

}
