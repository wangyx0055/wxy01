package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.GConnectionHistory;

public interface GConnectionHistoryService {

	public boolean addGConnectionHistory(GConnectionHistory gConnectionHistory);

	public boolean editGConnectionHistory(GConnectionHistory gConnectionHistory);

	public boolean delGConnectionHistory(List<Integer> ids);

	public List<Object> findAll(GConnectionHistory gConnectionHistory, int page_start, int page_length);

}

