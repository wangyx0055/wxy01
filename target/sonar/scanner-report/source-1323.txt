package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.RecordAudit;

public interface RecordAuditDao {

	public boolean editRecordAudit(RecordAudit recordAudit);

	public boolean addRecordAudit(RecordAudit recordAudit);

	public boolean delRecordAudit(List<Integer> ids);

	public List<Object> findAll(@Param("recordAudit")RecordAudit recordAudit, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
