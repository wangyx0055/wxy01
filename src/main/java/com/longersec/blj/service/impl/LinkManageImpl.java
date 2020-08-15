package com.longersec.blj.service.impl;

import com.longersec.blj.dao.LinkManageDao;
import com.longersec.blj.domain.LinkManage;
import com.longersec.blj.service.LinkManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class LinkManageImpl implements LinkManageService {
    @Autowired
    private LinkManageDao linkManageDao;


	@Override
	public boolean editLinkManage(LinkManage linkManage) {
		return this.linkManageDao.editLinkManage(linkManage);
	}

	@Override
	public boolean addLinkManage(LinkManage linkManage) {
		return this.linkManageDao.addLinkManage(linkManage);
	}

	@Override
	public boolean delLinkManage(List<String> ids) {
		return this.linkManageDao.delLinkManage(ids);
	}
}
