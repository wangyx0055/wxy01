package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ChangePasswordLog;

public interface ChangePasswordLogDao {

	public boolean editChangePasswordLog(ChangePasswordLog changePasswordLog);

	public boolean addChangePasswordLog(ChangePasswordLog changePasswordLog);

	public boolean delChangePasswordLog(List<Integer> ids);

	public List<Object> findAll(@Param("changePasswordLog")ChangePasswordLog changePasswordLog, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
