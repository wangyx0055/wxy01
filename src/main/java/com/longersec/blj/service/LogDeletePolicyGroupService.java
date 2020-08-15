package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.LogDeletePolicyGroup;

public interface LogDeletePolicyGroupService {

	public boolean addLogDeletePolicyGroup(LogDeletePolicyGroup logDeletePolicyGroup);

	public boolean editLogDeletePolicyGroup(LogDeletePolicyGroup logDeletePolicyGroup);

	public boolean delLogDeletePolicyGroup(List<Integer> ids);

	public List<Object> findAll(LogDeletePolicyGroup logDeletePolicyGroup, int page_start, int page_length);

}

