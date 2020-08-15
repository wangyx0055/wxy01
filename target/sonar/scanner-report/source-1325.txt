package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.RecordCommand;

public interface RecordCommandDao {

	public boolean editRecordCommand(RecordCommand recordCommand);

	public boolean addRecordCommand(RecordCommand recordCommand);

	public boolean delRecordCommand(List<Integer> ids);

	public List<Object> findAll(@Param("recordCommand")RecordCommand recordCommand, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public RecordCommand getById(Integer id);

}
