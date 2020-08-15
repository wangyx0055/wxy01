package com.longersec.blj.dao;

import com.longersec.blj.domain.LinkManage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LinkManageDao {

	public boolean editLinkManage(LinkManage linkManage);

	public boolean addLinkManage(LinkManage linkManage);

	public boolean delLinkManage(List<String> ids);
}
