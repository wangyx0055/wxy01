package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.RoleMenu;

public interface RoleMenuService {

	boolean addRoleMenu(RoleMenu roleMenu);

	boolean editRoleMenu(RoleMenu roleMenu);

	boolean delRoleMenu(Integer id);

	List<Object> findAll(RoleMenu roleMenu, int page_start, int page_length);

}

