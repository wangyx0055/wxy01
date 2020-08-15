package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ConfigSyslog;

public interface ConfigSyslogDao {

	public boolean editConfigSyslog(ConfigSyslog configSyslog);

	public boolean addConfigSyslog(ConfigSyslog configSyslog);

	public boolean delConfigSyslog(List<Integer> ids);

	public List<Object> findAll(@Param("configSyslog")ConfigSyslog configSyslog, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
