package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.AlertLog;

public interface AlertLogService {

	public boolean addAlertLog(AlertLog alertLog);

	public boolean editAlertLog(AlertLog alertLog);

	public boolean delAlertLog(List<Integer> ids);

	public List<Object> findAll(AlertLog alertLog, int page_start, int page_length);
	
	public int total();

}

