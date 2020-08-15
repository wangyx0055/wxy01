package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ConfigEmail;

public interface ConfigEmailDao {

	public boolean editConfigEmail(ConfigEmail configEmail);

	public boolean addConfigEmail(ConfigEmail configEmail);

	public boolean delConfigEmail(List<Integer> ids);

	public List<Object> findAll(@Param("configEmail")ConfigEmail configEmail, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
