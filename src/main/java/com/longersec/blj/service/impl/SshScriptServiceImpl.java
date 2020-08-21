package com.longersec.blj.service.impl;

import com.longersec.blj.dao.SshScriptDao;
import com.longersec.blj.domain.SshScript;
import com.longersec.blj.service.SshScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wxy
 * @description
 * @data 2020/8/19
 */
@Service
@Transactional
public class SshScriptServiceImpl implements SshScriptService {
	@Autowired
	private SshScriptDao sshScriptDao;

	@Override
	public boolean editSshScript(SshScript sshScript) {
		return this.sshScriptDao.editSshScript(sshScript);
	}

	@Override
	public boolean addSshScript(SshScript sshScript) {
		return this.sshScriptDao.addSshScript(sshScript);
	}

	@Override
	public boolean delSshScript(List<Integer> ids) {
		return this.sshScriptDao.delSshScript(ids);
	}

	@Override
	public List<Object> findAll(SshScript sshScript, int page_start, int page_length) {
		return sshScriptDao.findAll(sshScript, page_start, page_length);
	}

	@Override
	public SshScript getById(int id) {
		return sshScriptDao.getById(id);
	}
}
