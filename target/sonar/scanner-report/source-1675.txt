package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.RoleMenu;

public interface RoleMenuService {

	public boolean addRoleMenu(RoleMenu roleMenu);

	public boolean editRoleMenu(RoleMenu roleMenu);

	public boolean delRoleMenu(Integer id);

	public List<Object> findAll(RoleMenu roleMenu, int page_start, int page_length);

}

