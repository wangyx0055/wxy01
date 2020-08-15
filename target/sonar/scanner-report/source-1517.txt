package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ConfigEmail;

public interface ConfigEmailService {

	public boolean addConfigEmail(ConfigEmail configEmail);

	public boolean editConfigEmail(ConfigEmail configEmail);

	public boolean delConfigEmail(List<Integer> ids);

	public List<Object> findAll(ConfigEmail configEmail, int page_start, int page_length);

}

