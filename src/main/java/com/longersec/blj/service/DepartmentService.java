package com.longersec.blj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.longersec.blj.domain.DTO.DepartDTO;
import com.longersec.blj.domain.Department;
import org.apache.ibatis.annotations.Param;

public interface DepartmentService {

	boolean addDepartment(Department department);

	boolean editDepartment(Department department);

	boolean delDepartment(List<Integer> ids);

	List<Object> findAll(Department department, int id, int page_start, int page_length);

    ArrayList<DepartDTO> findIdName(int depart_id);

	String findName(int id);

	Department findParentName(int parent_id);

	int topDepartmentIfExists(String parent_name);

	int selectUselessDepartments();

	boolean updateDepartmentCounts(int department, int num);

	boolean updateDepartmentDeviceCount(int department, int num);

	Department selectParentId(@Param("parent_id") int parent_id);

	boolean deleteUselessDepartments();

	List<Department> findTopNodes(int id);

	List<Department> findSubNodes(int id);

    List<Integer> selectById(int depart_id);

    boolean insertMore(Department listDepartment);

	boolean editMore(Department department);

    Department selectByname(String s);
	
    Department getById(Integer id);

	List<String> findAllParentName(int id);

	/**查询所有用户 **/
	List<Integer> selectUserId();

	/** 查询用户所在部门**/
	Integer selectUserDepartment(int id);

	/** 更新部门用户数量**/
	boolean updateDepartmentUserCounts(List<Integer> ids);

	/**查询所有设备 **/
	List<Integer> selectDeviceId();

	/** 查询设备所在部门**/
	Integer selectDeviceDepartment(int id);

	/** 更新部门设备数量**/
	boolean updateDepartmentDeviceCounts(List<Integer> ids);

	/**查询登录角色所拥有的部门id **/
	List<Integer> selectTopId(int id);

	List<Integer> selectIdByname(String name);

	/** AD域导入部门判断**/
	Department selectByNameAndParentId(String name,int parent_id);
}

