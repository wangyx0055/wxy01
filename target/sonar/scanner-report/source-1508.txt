package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.CmdPolicyApppub;

public interface CmdPolicyApppubService {

	public boolean addCmdPolicyApppub(CmdPolicyApppub cmdPolicyApppub);

	public boolean editCmdPolicyApppub(CmdPolicyApppub cmdPolicyApppub);

	public boolean delCmdPolicyApppub(List<String> ids);

	public List<Object> findAll(CmdPolicyApppub cmdPolicyApppub, int page_start, int page_length);

}

