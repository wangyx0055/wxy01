package com.longersec.blj.service;

import java.util.ArrayList;
import java.util.List;

import com.longersec.blj.domain.DTO.RoleMenuDTO;
import com.longersec.blj.domain.Role;

public interface RoleService {

	boolean addRole(Role role,Integer[] list);

	boolean editRole(Role role);

	boolean delRole(Integer ids);

	List<RoleMenuDTO> findAll();

	ArrayList<Role> list();

	Role checkname(String name);

	String selectByid(Integer role_id);
}

