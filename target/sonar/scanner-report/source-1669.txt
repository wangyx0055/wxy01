package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.RecordCommand;

public interface RecordCommandService {

	public boolean addRecordCommand(RecordCommand recordCommand);

	public boolean editRecordCommand(RecordCommand recordCommand);

	public boolean delRecordCommand(List<Integer> ids);

	public List<Object> findAll(RecordCommand recordCommand, int page_start, int page_length);

	public RecordCommand getById(Integer id);

}

