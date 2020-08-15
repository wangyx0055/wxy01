package com.longersec.blj.service;

import com.longersec.blj.domain.LinkManage;

import java.util.List;

public interface LinkManageService {

	public boolean editLinkManage(LinkManage linkManage);

	public boolean addLinkManage(LinkManage linkManage);

	public boolean delLinkManage(List<String> ids);

}

