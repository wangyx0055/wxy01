package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.GConnectionParameter;

public interface GConnectionParameterService {

	public boolean addGConnectionParameter(GConnectionParameter gConnectionParameter);

	public boolean editGConnectionParameter(GConnectionParameter gConnectionParameter);

	public boolean delGConnectionParameter(List<Integer> ids);

	public List<Object> findAll(GConnectionParameter gConnectionParameter, int page_start, int page_length);

}

