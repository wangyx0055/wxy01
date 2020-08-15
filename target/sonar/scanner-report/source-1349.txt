package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.Syslog;

public interface SyslogDao {

	public boolean editSyslog(Syslog syslog);

	public boolean addSyslog(Syslog syslog);

	public boolean delSyslog(List<Integer> ids);

	public List<Object> findAll(@Param("syslog")Syslog syslog, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
