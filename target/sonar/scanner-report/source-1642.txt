package com.longersec.blj.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.longersec.blj.dao.RoleMenuDao;
import com.longersec.blj.domain.DTO.RoleMenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.RoleDao;
import com.longersec.blj.domain.Role;
import com.longersec.blj.service.RoleService;


@Transactional
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao RoleDao;
	@Autowired
	private RoleMenuDao roleMenuDao;

	@Override
	public boolean addRole(Role role, Integer[] list) {

//		roleMenuDao.addRoleMenu(id,list);
		Boolean b = RoleDao.addRole(role);
		role.getId();
		roleMenuDao.addRoleMenu(role.getId(),list);

		return b;
	}

	@Override
	public boolean editRole(Role role) {
		// TODO Auto-generated method stub
		return this.RoleDao.editRole(role);
	}


	@Override
	public boolean delRole(Integer id) {
		// TODO Auto-generated method stub
		return this.RoleDao.delRole(id);
	}

	@Override
	public List<RoleMenuDTO> findAll() {
		List<RoleMenuDTO> all = RoleDao.findAll();
		for (RoleMenuDTO l : all){
			int id = l.getId();
			List<Integer> byId = roleMenuDao.findById(id);
			l.setMenu_id(byId);

		}
		return all;
	}

	@Override
	public ArrayList<Role> list() {
		return this.RoleDao.list();
	}

	@Override
	public Role checkname(String name) {
		return this.RoleDao.checkname(name);
	}

	@Override
	 public String selectByid(Integer role_id){
		return RoleDao.selectByid(role_id);
	 }
}
