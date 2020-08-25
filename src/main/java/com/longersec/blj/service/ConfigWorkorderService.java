package com.longersec.blj.service;

import com.longersec.blj.domain.ConfigWorkorder;

import java.util.List;

public interface ConfigWorkorderService {

	boolean editConfigWorkorder(ConfigWorkorder configWorkorder);

	ConfigWorkorder getById(Integer id);
}

