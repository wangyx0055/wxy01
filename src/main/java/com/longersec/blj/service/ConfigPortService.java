package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ConfigPort;

public interface ConfigPortService {

	public boolean addConfigPort(ConfigPort configPort);

	public boolean editConfigPort(ConfigPort configPort);

	public boolean delConfigPort(List<Integer> ids);

	public List<Object> findAll(ConfigPort configPort, int page_start, int page_length);

}

