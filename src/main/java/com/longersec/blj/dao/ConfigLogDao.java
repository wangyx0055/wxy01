package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ConfigLog;
public interface ConfigLogDao{
    public boolean editConfigLog(ConfigLog configLog);

    public boolean addConfigLog(ConfigLog configLog);

    public boolean delConfigLog(List<Integer> ids);

    public List<Object> findAll(@Param("configLog")ConfigLog configLog, @Param("page_start")int page_start, @Param("page_length")int page_length);

	int selectCount();

	int checkname( @Param("id")Integer id,  @Param("name")String name);

    int checksort( @Param("id")Integer id, @Param("sort")Integer sort);
}

