package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.HighriskCommand;

public interface HighriskCommandDao {

	public boolean editHighriskCommand(HighriskCommand highriskCommand);

	public boolean addHighriskCommand(HighriskCommand highriskCommand);

	public boolean delHighriskCommand(List<Integer> ids);

	public List<Object> findAll(@Param("highriskCommand")HighriskCommand highriskCommand, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public HighriskCommand getById(Integer id);

}
