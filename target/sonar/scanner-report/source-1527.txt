package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ConfigSms;

public interface ConfigSmsService {

	public boolean addConfigSms(ConfigSms configSms);

	public boolean editConfigSms(ConfigSms configSms);

	public boolean delConfigSms(List<Integer> ids);

	public List<Object> findAll(ConfigSms configSms, int page_start, int page_length);

}

