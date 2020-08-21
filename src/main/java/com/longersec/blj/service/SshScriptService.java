package com.longersec.blj.service;

import com.longersec.blj.domain.SshScript;

import java.util.List;

public interface SshScriptService {
	boolean editSshScript(SshScript sshScript);

	boolean addSshScript(SshScript sshScript);

	boolean delSshScript(List<Integer> ids);

	List<Object> findAll(SshScript sshScript, int page_start, int page_length);

	SshScript getById(int id);
}
