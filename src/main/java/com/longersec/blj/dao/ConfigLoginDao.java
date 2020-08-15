package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ConfigLogin;

public interface ConfigLoginDao {

	public boolean editConfigLogin(ConfigLogin configLogin);

	public boolean addConfigLogin(ConfigLogin configLogin);

	public boolean delConfigLogin(List<Integer> ids);

	public List<Object> findAll(@Param("configLogin")ConfigLogin configLogin, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
