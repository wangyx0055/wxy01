package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.CrontabScriptConfigDevice;
import com.longersec.blj.domain.DTO.Deviceaccess;

public interface CrontabScriptConfigDeviceService {

    public  List<Deviceaccess> selectById(Integer config_id);

    public boolean addCrontabScriptConfigDevice(Integer config_id, List<Integer> device);

	public boolean editCrontabScriptConfigDevice(Integer config_id,List<Integer> list);

	public boolean delCrontabScriptConfigDevice(List<Integer> ids);

	public List<Object> findAll(CrontabScriptConfigDevice crontabScriptConfigDevice, int page_start, int page_length);

	public Boolean deleteByid(Integer config_id);

}

