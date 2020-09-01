package com.longersec.blj.service;

import com.longersec.blj.domain.DTO.DepartDTO;
import com.longersec.blj.domain.Department;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface DepartmentService {

	boolean addDepartment(Department department);

	boolean editDepartment(Department department);

	/** 获取某个部门下的所有子部门**/
	JSONObject getAllDepartmentsByParentId();

	/**先执行删除,插入部门缓存表 **/
	boolean cacheDepartmentId();

	boolean delDepartment(List<Integer> ids);

	ArrayList<Department> findAll(Department department, int id);

	/** 获取某个部门下的所有id**/
	List<Integer> getAllIdByParentId(Integer id);


	/** 查询所有部门id**/
	List<Integer> selectAllDepartmentid();

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

	/**删除策略和执行任务关联信息 **/
	boolean deleteUseless(Integer id);
}

