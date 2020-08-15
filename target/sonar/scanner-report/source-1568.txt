package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.BackupConfigDao;
import com.longersec.blj.domain.BackupConfig;
import com.longersec.blj.service.BackupConfigService;


@Transactional
@Service
public class BackupConfigServiceImpl implements BackupConfigService{

	@Autowired
	private BackupConfigDao BackupConfigDao;

	@Override
	public boolean editBackupConfig(BackupConfig backupConfig) {
		// TODO Auto-generated method stub
		return this.BackupConfigDao.editBackupConfig(backupConfig);
	}

	@Override
	public boolean addBackupConfig(BackupConfig backupConfig) {
		// TODO Auto-generated method stub
		return this.BackupConfigDao.addBackupConfig(backupConfig);
	}

	@Override
	public boolean delBackupConfig(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.BackupConfigDao.delBackupConfig(ids);
	}

	@Override
	public List<Object> findAll(BackupConfig backupConfig, int page_start, int page_length) {
		return BackupConfigDao.findAll(backupConfig, page_start, page_length);
	}

}
