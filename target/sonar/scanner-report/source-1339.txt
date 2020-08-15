package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.RoleMenu;

public interface RoleMenuDao {

	public boolean editRoleMenu(RoleMenu roleMenu);

	public boolean addRoleMenu(@Param("role_id")Integer role_id,@Param("list") Integer[] list);

	public boolean delRoleMenu(@Param("id") Integer id);

	public List<Object> findAll();

	public List<Integer> findById(@Param("id") int id);

	List<String> findByIdAse(int role_id);
}
