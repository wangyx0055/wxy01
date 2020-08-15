package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.BackupLogDao;
import com.longersec.blj.domain.BackupLog;
import com.longersec.blj.service.BackupLogService;


@Transactional
@Service
public class BackupLogServiceImpl implements BackupLogService{

	@Autowired
	private BackupLogDao BackupLogDao;

	@Override
	public boolean editBackupLog(BackupLog backupLog) {
		// TODO Auto-generated method stub
		return this.BackupLogDao.editBackupLog(backupLog);
	}

	@Override
	public boolean addBackupLog(BackupLog backupLog) {
		// TODO Auto-generated method stub
		return this.BackupLogDao.addBackupLog(backupLog);
	}

	@Override
	public boolean delBackupLog(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.BackupLogDao.delBackupLog(ids);
	}

	@Override
	public List<Object> findAll(BackupLog backupLog, int page_start, int page_length) {
		return BackupLogDao.findAll(backupLog, page_start, page_length);
	}

}
