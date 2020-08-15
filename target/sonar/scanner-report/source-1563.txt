package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ApppubResourceGroupDao;
import com.longersec.blj.domain.ApppubResourceGroup;
import com.longersec.blj.service.ApppubResourceGroupService;


@Transactional
@Service
public class ApppubResourceGroupServiceImpl implements ApppubResourceGroupService{

	@Autowired
	private ApppubResourceGroupDao ApppubResourceGroupDao;

	@Override
	public boolean editApppubResourceGroup(ApppubResourceGroup apppubResourceGroup) {
		// TODO Auto-generated method stub
		return this.ApppubResourceGroupDao.editApppubResourceGroup(apppubResourceGroup);
	}

	@Override
	public boolean addApppubResourceGroup(ApppubResourceGroup apppubResourceGroup) {
		// TODO Auto-generated method stub
		return this.ApppubResourceGroupDao.addApppubResourceGroup(apppubResourceGroup);
	}

	@Override
	public boolean delApppubResourceGroup(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ApppubResourceGroupDao.delApppubResourceGroup(ids);
	}

	@Override
	public List<Object> findAll(ApppubResourceGroup apppubResourceGroup, int page_start, int page_length) {
		return ApppubResourceGroupDao.findAll(apppubResourceGroup, page_start, page_length);
	}

}
