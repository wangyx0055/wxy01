package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ConfigFinger;
import com.longersec.blj.domain.Device;

public interface ConfigFingerDao {

	public boolean editConfigFinger(ConfigFinger configFinger);

	public boolean addConfigFinger(ConfigFinger configFinger);

	public boolean delConfigFinger(List<Integer> ids);

	public List<Object> findAll(@Param("configFinger")ConfigFinger configFinger, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public ConfigFinger getById(@Param("id")Integer id);
}
