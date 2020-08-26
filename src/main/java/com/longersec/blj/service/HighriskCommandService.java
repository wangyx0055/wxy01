package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.HighriskCommand;

public interface HighriskCommandService {

	public boolean addHighriskCommand(HighriskCommand highriskCommand);

	public boolean editHighriskCommand(HighriskCommand highriskCommand);

	public boolean delHighriskCommand(List<Integer> ids);

	public List<Object> findAll(HighriskCommand highriskCommand, int page_start, int page_length);

	public HighriskCommand getById(Integer id);

}

