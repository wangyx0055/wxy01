package com.longersec.blj.dao;

import com.longersec.blj.domain.DTO.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrontabScriptConfigUserDao {
    public boolean addCrontabScriptConfigUser(@Param("config_id") Integer config_id,@Param("users") List<Integer> users);

    public List<Users> selectById(@Param("config_id") Integer config_id);

    public Boolean deleteByid(@Param("config_id") Integer config_id);

    public Boolean editCrontabScriptconfigUse(@Param("config_id") Integer config_id, @Param("users") List<Integer> users);
}
