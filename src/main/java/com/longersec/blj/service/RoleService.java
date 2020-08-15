package com.longersec.blj.service;

import java.util.ArrayList;
import java.util.List;

import com.longersec.blj.domain.DTO.RoleMenuDTO;
import com.longersec.blj.domain.Role;

public interface RoleService {

	public boolean addRole(Role role,Integer[] list);

	public boolean editRole(Role role);

	public boolean delRole(Integer ids);

	public List<RoleMenuDTO> findAll();

    public ArrayList<Role> list();

    public Role checkname(String name);

	public String selectByid(Integer role_id);
}

