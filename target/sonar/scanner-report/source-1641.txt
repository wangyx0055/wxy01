package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.RoleMenuDao;
import com.longersec.blj.domain.RoleMenu;
import com.longersec.blj.service.RoleMenuService;


@Transactional
@Service
public class RoleMenuServiceImpl implements RoleMenuService{

	@Autowired
	private RoleMenuDao RoleMenuDao;

	@Override
	public boolean editRoleMenu(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
		return this.RoleMenuDao.editRoleMenu(roleMenu);
	}

	@Override
	public boolean addRoleMenu(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
//		return this.RoleMenuDao.addRoleMenu(roleMenu);
		return true;
	}

	@Override
	public boolean delRoleMenu(Integer id) {
		// TODO Auto-generated method stub
		return this.RoleMenuDao.delRoleMenu(id);
	}

	@Override
	public List<Object> findAll(RoleMenu roleMenu, int page_start, int page_length) {
//		return RoleMenuDao.findAll(roleMenu, page_start, page_length);
		return null;
	}

}
