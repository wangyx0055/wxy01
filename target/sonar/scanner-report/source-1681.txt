package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.Syslog;

public interface SyslogService {

	public boolean addSyslog(Syslog syslog);

	public boolean editSyslog(Syslog syslog);

	public boolean delSyslog(List<Integer> ids);

	public List<Object> findAll(Syslog syslog, int page_start, int page_length);

}

