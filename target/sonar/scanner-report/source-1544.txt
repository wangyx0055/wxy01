package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.EmailLog;

public interface EmailLogService {

	public boolean addEmailLog(EmailLog emailLog);

	public boolean editEmailLog(EmailLog emailLog);

	public boolean delEmailLog(List<Integer> ids);

	public List<Object> findAll(EmailLog emailLog, int page_start, int page_length);

	public EmailLog getById(Integer id);

}

