package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.Record;

public interface RecordDao {

	public boolean editRecord(Record record);

	public boolean addRecord(Record record);

	public boolean delRecord(List<Integer> ids);

	public List<Object> findAll(@Param("record")Record record, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
