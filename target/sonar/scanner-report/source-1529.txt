package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ConfigSyslog;

public interface ConfigSyslogService {

	public boolean addConfigSyslog(ConfigSyslog configSyslog);

	public boolean editConfigSyslog(ConfigSyslog configSyslog);

	public boolean delConfigSyslog(List<Integer> ids);

	public List<Object> findAll(ConfigSyslog configSyslog, int page_start, int page_length);

}

