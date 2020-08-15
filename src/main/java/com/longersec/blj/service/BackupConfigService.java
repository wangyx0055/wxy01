package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.BackupConfig;

public interface BackupConfigService {

	public boolean addBackupConfig(BackupConfig backupConfig);

	public boolean editBackupConfig(BackupConfig backupConfig);

	public boolean delBackupConfig(List<Integer> ids);

	public List<Object> findAll(BackupConfig backupConfig, int page_start, int page_length);

}

