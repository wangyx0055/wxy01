package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.SystemUsage;

public interface SystemUsageService {

	public boolean addSystemUsage(SystemUsage systemUsage);

	public boolean editSystemUsage(SystemUsage systemUsage);

	public boolean delSystemUsage(List<Integer> ids);

	public List<SystemUsage> findAll(String interval,String start_date, String end_date,SystemUsage systemUsage);

	public SystemUsage getById(Integer id);

}

