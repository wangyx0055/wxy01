package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.GConnection;

public interface GConnectionService {

	public boolean addGConnection(GConnection gConnection);

	public boolean editGConnection(GConnection gConnection);

	public boolean delGConnection(List<Integer> ids);

	public List<Object> findAll(GConnection gConnection, int page_start, int page_length);

}

