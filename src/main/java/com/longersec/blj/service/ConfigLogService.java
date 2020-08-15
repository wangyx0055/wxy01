package com.longersec.blj.service;

import com.longersec.blj.domain.ConfigLog;
import java.util.List;

public interface ConfigLogService {
    boolean addConfigLog(ConfigLog configLog);

    boolean editConfigLog(ConfigLog configLog);

    boolean delConfigLog(List<Integer> ids);

    List<Object> findAll(ConfigLog configLog, int page_start, int page_length);

    int selectCount();

    int checkname(Integer id, String name);
}
