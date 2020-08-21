package com.longersec.blj.dao;

import com.longersec.blj.domain.SshScript;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SshScriptDao {

	boolean editSshScript(SshScript sshScript);

	boolean addSshScript(SshScript sshScript);

	boolean delSshScript(List<Integer> ids);

	List<Object> findAll(@Param("sshScript")SshScript sshScript, @Param("page_start")int page_start, @Param("page_length")int page_length);

	SshScript getById(@Param("id")Integer id);
}