package com.longersec.blj.dao;

import java.util.ArrayList;
import java.util.List;

import com.longersec.blj.domain.DTO.RoleMenuDTO;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.Role;

public interface RoleDao {

	public boolean editRole(Role role);

	public boolean addRole(Role role);

	public boolean delRole(@Param("id") Integer id);

//	public List<Object> findAll(@Param("role")Role role, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<RoleMenuDTO> findAll();

    public ArrayList<Role> list();

    public Role checkname(@Param("name") String name);

	public String selectByid(@Param("role_id") Integer role_id);
}
