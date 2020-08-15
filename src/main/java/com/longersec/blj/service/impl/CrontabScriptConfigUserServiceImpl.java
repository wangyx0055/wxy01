package com.longersec.blj.service.impl;

import com.longersec.blj.dao.CrontabScriptConfigUserDao;
import com.longersec.blj.domain.DTO.Users;
import com.longersec.blj.service.CrontabScriptConfigUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CrontabScriptConfigUserServiceImpl implements CrontabScriptConfigUserService {

    @Autowired
    private CrontabScriptConfigUserDao crontabScriptConfigUserDao;

    @Override
    public boolean addCrontabScriptConfigUser(Integer config_id, List<Integer> users) {
        return crontabScriptConfigUserDao.addCrontabScriptConfigUser(config_id,users);
    }

    @Override
    public List<Users> selectById(Integer config_id) {
        return crontabScriptConfigUserDao.selectById(config_id);
    }

    @Override
    public Boolean deleteByid(Integer config_id) {
        return crontabScriptConfigUserDao.deleteByid(config_id);
    }

    @Override
    public Boolean editCrontabScriptconfigUse(Integer config_id, List<Integer> users) {
        return crontabScriptConfigUserDao.editCrontabScriptconfigUse(config_id,users);
    }
}
