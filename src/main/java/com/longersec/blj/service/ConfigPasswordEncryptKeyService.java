package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ConfigPasswordEncryptKey;

public interface ConfigPasswordEncryptKeyService {

	public boolean addConfigPasswordEncryptKey(ConfigPasswordEncryptKey configPasswordEncryptKey);

	public boolean editConfigPasswordEncryptKey(ConfigPasswordEncryptKey configPasswordEncryptKey);

	public boolean delConfigPasswordEncryptKey(List<Integer> ids);

	public List<Object> findAll(ConfigPasswordEncryptKey configPasswordEncryptKey, int page_start, int page_length);

	public ConfigPasswordEncryptKey getById(Integer id);
	
	public String getKey();

}

