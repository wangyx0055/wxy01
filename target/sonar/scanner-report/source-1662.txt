package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.LogDeletePolicy;

public interface LogDeletePolicyService {

	public boolean addLogDeletePolicy(LogDeletePolicy logDeletePolicy);

	public boolean editLogDeletePolicy(LogDeletePolicy logDeletePolicy);

	public boolean delLogDeletePolicy(List<Integer> ids);

	public List<Object> findAll(LogDeletePolicy logDeletePolicy,String name, int page_start, int page_length);

}

