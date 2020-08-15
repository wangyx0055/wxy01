package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.RecordAudit;

public interface RecordAuditService {

	public boolean addRecordAudit(RecordAudit recordAudit);

	public boolean editRecordAudit(RecordAudit recordAudit);

	public boolean delRecordAudit(List<Integer> ids);

	public List<Object> findAll(RecordAudit recordAudit, int page_start, int page_length);

}

