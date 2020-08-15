package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.BackupLog;

public interface BackupLogService {

	public boolean addBackupLog(BackupLog backupLog);

	public boolean editBackupLog(BackupLog backupLog);

	public boolean delBackupLog(List<Integer> ids);

	public List<Object> findAll(BackupLog backupLog, int page_start, int page_length);

}

