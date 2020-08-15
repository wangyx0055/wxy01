package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ConfigSnmp;

public interface ConfigSnmpService {

	public boolean addConfigSnmp(ConfigSnmp configSnmp);

	public boolean editConfigSnmp(ConfigSnmp configSnmp);

	public boolean delConfigSnmp(List<Integer> ids);

	public List<Object> findAll(ConfigSnmp configSnmp, int page_start, int page_length);

}

