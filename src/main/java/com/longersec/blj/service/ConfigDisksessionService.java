package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ConfigDisksession;

public interface ConfigDisksessionService {

	public boolean addConfigDisksession(ConfigDisksession configDisksession);

	public boolean editConfigDisksession(ConfigDisksession configDisksession);

	public boolean delConfigDisksession(List<Integer> ids);

	public List<Object> findAll(ConfigDisksession configDisksession, int page_start, int page_length);

	public ConfigDisksession get();
}

