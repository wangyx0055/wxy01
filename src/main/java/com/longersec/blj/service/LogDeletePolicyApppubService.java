package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.LogDeletePolicyApppub;

public interface LogDeletePolicyApppubService {

	public boolean addLogDeletePolicyApppub(LogDeletePolicyApppub logDeletePolicyApppub);

	public boolean editLogDeletePolicyApppub(LogDeletePolicyApppub logDeletePolicyApppub);

	public boolean delLogDeletePolicyApppub(List<Integer> ids);

	public List<Object> findAll(LogDeletePolicyApppub logDeletePolicyApppub, int page_start, int page_length);

}

