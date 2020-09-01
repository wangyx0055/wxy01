package com.longersec.blj.service.impl;

import com.longersec.blj.dao.DepartmentDao;
import com.longersec.blj.domain.DTO.DepartDTO;
import com.longersec.blj.domain.Department;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.DepartmentService;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao DepartmentDao;

	@Override
	public boolean editDepartment(Department department) {
		return this.DepartmentDao.editDepartment(department);
	}

	@Override
	public JSONObject getAllDepartmentsByParentId() {
		JSONObject result = new JSONObject();
		User p_user = (User) SecurityUtils.getSubject().getPrincipal();
		DepartDTO idParent = (DepartDTO) DepartmentDao.findIdParent(p_user.getDepartment());
		ArrayList<DepartDTO> departments = (ArrayList<DepartDTO>) DepartmentDao.getAllDepartmentsByParentId(p_user.getDepartment());
		idParent.setNodes(departments);
		ArrayList<DepartDTO> departDTOS = new ArrayList<>();
		departDTOS.add(0,idParent);
		result.put("data",departDTOS);
		return result;
	}

	@Override
	public boolean cacheDepartmentId() {
		//查询所有部门id
		List<Integer> integers1 = DepartmentDao.selectAllDepartmentid();
		for (Integer i : integers1) {
			//查询所有父级id
			List<Integer> integers = DepartmentDao.selectParentIdForCache(i);
			//先删除在插入
			DepartmentDao.cacheDepartmentId(integers, i);
		}
		return true;
	}

	@Override
	public boolean addDepartment(Department department) {
		return this.DepartmentDao.addDepartment(department);
	}

	@Override
	public boolean delDepartment(List<Integer> ids) {
		return this.DepartmentDao.delDepartment(ids);
	}

	@Override
	public 	ArrayList<Department> findAll(Department department, int id) {
		return DepartmentDao.findAll(department, id);
	}

	@Override
	public List<Integer> getAllIdByParentId(Integer id) {
		User p_user = (User) SecurityUtils.getSubject().getPrincipal();
		List<Integer> allIdByParentId = DepartmentDao.getAllIdByParentId(id);
		allIdByParentId.add(p_user.getDepartment());
		return allIdByParentId;
	}

	@Override
	public List<Integer> selectAllDepartmentid() {
		return DepartmentDao.selectAllDepartmentid();
	}

	@Override
	public String findName(int id) {
		return DepartmentDao.findName(id);
	}

	@Override
	public Department findParentName(int parent_id) {
		return DepartmentDao.findParentName(parent_id);
	}

	@Override
	public int topDepartmentIfExists(String parent_name) {
		return DepartmentDao.TopDepartmentIfExists(parent_name);
	}

	@Override
	public int selectUselessDepartments() {
		return DepartmentDao.selectUselessDepartments();
	}

	@Override
	public boolean updateDepartmentCounts(int department, int num) {
		return DepartmentDao.updateDepartmentCounts(department, num);
	}

	@Override
	public boolean updateDepartmentDeviceCount(int department, int num) {
		return DepartmentDao.updateDepartmentDeviceCount(department,num);
	}

	@Override
	public Department selectParentId(int parent_id) {
		return DepartmentDao.selectParentId(parent_id);
	}

	@Override
	public boolean deleteUselessDepartments() {
		return DepartmentDao.deleteUselessDepartments();
	}

	@Override
	public List<Department> findTopNodes(int id) {
		return DepartmentDao.findTopNodes(id);
	}

	@Override
	public List<Department> findSubNodes(int id) {
		return DepartmentDao.findSubNodes(id);
	}

	@Override
	public List<Integer> selectById(int depart_id) {
		return DepartmentDao.selectById(depart_id);
	}

	@Override
	public boolean insertMore(Department listDepartment) {
		return DepartmentDao.insertMore(listDepartment);
	}

	@Override
	public boolean editMore(Department department) {
		return DepartmentDao.editMore(department);
	}

	@Override
	public Department selectByname(String s) {
		return DepartmentDao.selectByname(s);
	}

	@Override
	public Department getById(Integer id) {
		return DepartmentDao.getById(id);
	}

	@Override
	public List<String> findAllParentName(int id) {
		return DepartmentDao.findAllParentName(id);
	}

	@Override
	public List<Integer> selectUserId() {
		return DepartmentDao.selectUserId();
	}

	@Override
	public Integer selectUserDepartment(int id) {
		return DepartmentDao.selectUserDepartment(id);
	}

	@Override
	public boolean updateDepartmentUserCounts(List<Integer> ids) {
		return DepartmentDao.updateDepartmentUserCounts(ids);
	}

	@Override
	public List<Integer> selectDeviceId() {
		return DepartmentDao.selectDeviceId();
	}

	@Override
	public Integer selectDeviceDepartment(int id) {
		return DepartmentDao.selectDeviceDepartment(id);
	}

	@Override
	public boolean updateDepartmentDeviceCounts(List<Integer> ids) {
		return DepartmentDao.updateDepartmentDeviceCounts(ids);
	}

	@Override
	public List<Integer> selectTopId(int id) {
		return DepartmentDao.selectTopId(id);
	}

	@Override
	public List<Integer> selectIdByname(String name) {
		return DepartmentDao.selectIdByname(name);
	}

	@Override
	public Department selectByNameAndParentId(String name, int parent_id) {
		return DepartmentDao.selectByNameAndParentId(name,parent_id);
	}

	@Override
	public boolean deleteUseless(Integer id) {
		return DepartmentDao.deleteUseless(id);
	}
}
