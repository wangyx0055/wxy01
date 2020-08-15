package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ConfigSms;

public interface ConfigSmsDao {

	public boolean editConfigSms(ConfigSms configSms);

	public boolean addConfigSms(ConfigSms configSms);

	public boolean delConfigSms(List<Integer> ids);

	public List<Object> findAll(@Param("configSms")ConfigSms configSms, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
