package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.Record;

public interface RecordService {

	public boolean addRecord(Record record);

	public boolean editRecord(Record record);

	public boolean delRecord(List<Integer> ids);

	public List<Object> findAll(Record record, int page_start, int page_length);

}

