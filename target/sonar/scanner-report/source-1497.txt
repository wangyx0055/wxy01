package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.Apppub;
import com.longersec.blj.domain.ApppubServer;
import com.longersec.blj.domain.DTO.App;

public interface ApppubService {

	public boolean addApppub(Apppub apppub);

	public boolean editApppub(Apppub apppub);

	public boolean delApppub(List<Integer> ids);

	public List<Object> findAll(Apppub apppub, int page_start, int page_length);

	public List<App> selectNameAndId();
	
	public int total();
}

