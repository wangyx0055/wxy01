package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.EmailLog;

public interface EmailLogDao {

	public boolean editEmailLog(EmailLog emailLog);

	public boolean addEmailLog(EmailLog emailLog);

	public boolean delEmailLog(List<Integer> ids);

	public List<Object> findAll(@Param("emailLog")EmailLog emailLog, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public EmailLog getById(Integer id);

}
