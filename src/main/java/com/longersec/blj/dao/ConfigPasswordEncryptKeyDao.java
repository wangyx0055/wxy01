package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ConfigPasswordEncryptKey;

public interface ConfigPasswordEncryptKeyDao {

	public boolean editConfigPasswordEncryptKey(ConfigPasswordEncryptKey configPasswordEncryptKey);

	public boolean addConfigPasswordEncryptKey(ConfigPasswordEncryptKey configPasswordEncryptKey);

	public boolean delConfigPasswordEncryptKey(List<Integer> ids);

	public List<Object> findAll(@Param("configPasswordEncryptKey")ConfigPasswordEncryptKey configPasswordEncryptKey, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public ConfigPasswordEncryptKey getById(Integer id);
	
	public String getKey();


}
