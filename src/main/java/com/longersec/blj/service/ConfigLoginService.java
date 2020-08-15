package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ConfigLogin;

public interface ConfigLoginService {

	public boolean addConfigLogin(ConfigLogin configLogin);

	public boolean editConfigLogin(ConfigLogin configLogin);

	public boolean delConfigLogin(List<Integer> ids);

	public List<Object> findAll(ConfigLogin configLogin, int page_start, int page_length);

}

