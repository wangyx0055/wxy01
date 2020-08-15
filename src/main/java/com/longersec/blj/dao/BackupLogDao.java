package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.BackupLog;

public interface BackupLogDao {

	public boolean editBackupLog(BackupLog backupLog);

	public boolean addBackupLog(BackupLog backupLog);

	public boolean delBackupLog(List<Integer> ids);

	public List<Object> findAll(@Param("backupLog")BackupLog backupLog, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
