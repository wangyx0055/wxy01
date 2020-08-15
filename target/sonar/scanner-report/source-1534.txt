package com.longersec.blj.service;

import com.longersec.blj.domain.CrontabScriptConfigGroup;
import com.longersec.blj.domain.DTO.Users;

import java.util.List;

public interface CrontabScriptConfigUserService {

    public boolean addCrontabScriptConfigUser(Integer config_id, List<Integer> users);

    public List<Users> selectById(Integer config_id);

    public Boolean deleteByid(Integer config_id);

    public Boolean editCrontabScriptconfigUse(Integer config_id, List<Integer> users);
}
