package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.BackupConfig;

public interface BackupConfigDao {

	public boolean editBackupConfig(BackupConfig backupConfig);

	public boolean addBackupConfig(BackupConfig backupConfig);

	public boolean delBackupConfig(List<Integer> ids);

	public List<Object> findAll(@Param("backupConfig")BackupConfig backupConfig, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
