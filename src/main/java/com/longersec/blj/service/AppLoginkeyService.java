package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.AppLoginkey;

public interface AppLoginkeyService {

	public boolean addAppLoginkey(AppLoginkey appLoginkey);

	public boolean editAppLoginkey(AppLoginkey appLoginkey);

	public boolean delAppLoginkey(List<Integer> ids);

	public List<Object> findAll(AppLoginkey appLoginkey, int page_start, int page_length);

	public AppLoginkey getById(Integer id);
	

}

