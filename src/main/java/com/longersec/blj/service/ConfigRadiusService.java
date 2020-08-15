package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ConfigRadius;

public interface ConfigRadiusService {

	public boolean addConfigRadius(ConfigRadius configRadius);

	public boolean editConfigRadius(ConfigRadius configRadius);

	public boolean delConfigRadius(List<Integer> ids);

	public List<Object> findAll(ConfigRadius configRadius, int page_start, int page_length);

}

