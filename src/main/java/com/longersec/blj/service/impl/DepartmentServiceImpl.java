package com.longersec.blj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.longersec.blj.domain.DTO.DepartDTO;
import com.longersec.blj.domain.Department;
import com.longersec.blj.service.DepartmentService;
import com.longersec.blj.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.DepartmentDao;


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
	public boolean addDepartment(Department department) {
		return this.DepartmentDao.addDepartment(department);
	}

	@Override
	public boolean delDepartment(List<Integer> ids) {
		return this.DepartmentDao.delDepartment(ids);
	}

	@Override
	public List<Object> findAll(Department department, int id, int page_start, int page_length) {
		return DepartmentDao.findAll(department, id, page_start, page_length);
	}

	@Override
	public ArrayList<DepartDTO> findIdName(int depart_id) {
		ArrayList<DepartDTO> list = DepartmentDao.findIdName(depart_id);
		ArrayList<DepartDTO> Tree_list = DepartmentDao.findIdParent(depart_id);
		Tree_list.addAll(list);
		return (ArrayList<DepartDTO>) TreeUtil.rebuildList2Tree(Tree_list);
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
