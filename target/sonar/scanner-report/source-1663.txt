package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.LogDeletePolicyUser;

public interface LogDeletePolicyUserService {

	public boolean addLogDeletePolicyUser(LogDeletePolicyUser logDeletePolicyUser);

	public boolean editLogDeletePolicyUser(LogDeletePolicyUser logDeletePolicyUser);

	public boolean delLogDeletePolicyUser(List<Integer> ids);

	public List<Object> findAll(LogDeletePolicyUser logDeletePolicyUser, int page_start, int page_length);

}

