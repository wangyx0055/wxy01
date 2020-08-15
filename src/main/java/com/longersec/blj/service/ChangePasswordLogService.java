package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ChangePasswordLog;

public interface ChangePasswordLogService {

	public boolean addChangePasswordLog(ChangePasswordLog changePasswordLog);

	public boolean editChangePasswordLog(ChangePasswordLog changePasswordLog);

	public boolean delChangePasswordLog(List<Integer> ids);

	public List<Object> findAll(ChangePasswordLog changePasswordLog, int page_start, int page_length);

}

