package com.longersec.blj.service;

import java.util.List;

import com.longersec.blj.domain.ApppubAccount;
import com.longersec.blj.domain.ApppubProgram;

public interface ApppubProgramService {

	public boolean addApppubProgram(ApppubProgram apppubProgram);

	public boolean editApppubProgram(ApppubProgram apppubProgram);

	public boolean delApppubProgram(List<Integer> ids);

	public List<Object> findAll(ApppubProgram apppubProgram,String sname,Integer type, int page_start, int page_length);
	
	public int total();
	
	public ApppubProgram getById(Integer id);

	public ApppubProgram checkAppName(String name);

}

