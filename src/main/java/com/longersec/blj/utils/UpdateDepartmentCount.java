package com.longersec.blj.utils;

import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import com.longersec.blj.domain.Department;
import com.longersec.blj.service.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author wxy
 * @description 更新部门所有的用户数
 * @data 2020/8/4
 */
public class UpdateDepartmentCount {
	/** 用户列表的新建和编辑和删除和部门的编辑操作 **/
	public static synchronized List<Object> userUpdateDepartmentCount(DepartmentService departmentService, int departmentId, int num){
		List<Object> list = new ArrayList<Object> (10);
		//查询所有上级部门
		List<Department> topNodes = departmentService.findTopNodes(departmentId);
		for (Department department1 : topNodes) {
			//更新部门用户数量
			departmentService.updateDepartmentCounts(department1.getId() ,num);
			list.add(department1.getName());
		}
		return list;
	}

	/** 设备列表的新建和编辑和删除和部门的编辑操作 **/
	public static synchronized List<Object> deviceUpdateDepartmentCount(DepartmentService departmentService, int departmentId, int num){
		List<Object> list = new ArrayList<Object> (10);
		//查询所有上级部门
		List<Department> topNodes = departmentService.findTopNodes(departmentId);
		for (Department department1 : topNodes) {
			//更新部门用户数量
			departmentService.updateDepartmentDeviceCount(department1.getId() ,num);
			list.add(department1.getName());
		}
		return list;
	}

	/** 部门的删除,多选删除操作 ---同时删除用户设备
	 * @return**/
	public static synchronized List<Object> deleteUserUpdateDepartmentCount(DepartmentService departmentService, UserService userService, DeviceService deviceService,
	                                                                        GroupService groupService,UserGroupUserService userGroupUserService,
	                                                                        GroupDeviceAccountService groupDeviceAccountService,ApppubServerService apppubServerService,
	                                                                        List<Integer> departmentId){
		// todo 用于存储被删除的部门id和name做系统日志,当再次遍历到就跳出循环---完善initialCapacity的大小
		List<Object> list = new ArrayList<Object> (10);
		List<Object> list1 = new ArrayList<Object> (10);
		Department department = new Department();
		for (Integer integer:departmentId) {
			int parentId = integer;
			//查询删除部门的用户-设备数量
			department = departmentService.selectParentId(parentId);
			//查询所有上级部门
			List<Department> topNodes = departmentService.findTopNodes(parentId);
			for (Department department1 : topNodes) {
				if (department1.getId() != parentId) {
					//更新部门用户数量
					departmentService.updateDepartmentCounts(department1.getId() ,-department.getCount());
					//更新部门设备数量
					departmentService.updateDepartmentDeviceCount(department1.getId() ,-department.getDevice_count());
				}
			}
			//查询所有下级部门
			List<Department> subNodes = departmentService.findSubNodes(parentId);
			for (Department department2 : subNodes) {
				//不在删除被删除的部门
				if (!list.contains(department2.getId())) {
					//删除用户相关
					userService.deleteUselessUser(department2.getId());
					//删除设备相关
					deviceService.deleteUselessDevice(department2.getId());
					//删除和应用相关
					apppubServerService.delUselessApppubServer(department2.getId());
					//删除部门
					departmentService.delDepartment(Collections.singletonList(department2.getId()));
					list.add(department2.getId());
					list1.add(department2.getName());
				}
			}
		}
		AutoUpdateGroupCounts(groupService,userGroupUserService);
		AutoUpdateGroupDeviceCounts(groupService,groupDeviceAccountService);
		return list1;
	}

	/** 自动更新部门用户数量 **/
	public static synchronized void AutoUpdateDepartmentUserCounts(DepartmentService departmentService){
		ArrayList<Integer> arrayList = new ArrayList<Integer>(10);
		//1.查询用户id-让部门用户数量全是0
		List<Integer> selectUserId = departmentService.selectUserId();
		//2.遍历结果1
		for (Integer id:selectUserId) {
			//3.查询用户所在部门
			Integer selectUserDepartment = departmentService.selectUserDepartment(id);
			//4.查询部门的所有上级部门
			List<Department> topNodes = departmentService.findTopNodes(selectUserDepartment);
			for (Department department1:topNodes) {
				arrayList.add(department1.getId());
			}
			//5.递归增加部门用户数量
			departmentService.updateDepartmentUserCounts(arrayList);
			arrayList.clear();
		}
	}

	/** 自动更新部门设备数量 **/
	public static synchronized void AutoUpdateDepartmentDeviceCounts(DepartmentService departmentService){
		ArrayList<Integer> arrayList = new ArrayList<Integer>(10);
		//1.查询设备id-让部门设备数量全是0
		List<Integer> selectDeviceId = departmentService.selectDeviceId();
		//2.遍历结果1
		for (Integer id:selectDeviceId) {
			//3.查询设备所在部门
			Integer selectDeviceDepartment = departmentService.selectDeviceDepartment(id);
			//4.查询部门的所有上级部门
			List<Department> topNodes = departmentService.findTopNodes(selectDeviceDepartment);
			for (Department department1:topNodes) {
				arrayList.add(department1.getId());
			}
			//5.递归增加部门用户数量
			departmentService.updateDepartmentDeviceCounts(arrayList);
			arrayList.clear();
		}
	}

	/** 自动更新用户组用户数量 **/
	public static synchronized void AutoUpdateGroupCounts(GroupService groupService, UserGroupUserService userGroupUserService){
		//1.先查询用户组的id
		List<UserGroup> userGroups = groupService.selectNameAndIdTop();
		//2.遍历用户组id
		for (UserGroup userGroup:userGroups) {
			//3.查询用户组用户数量
			int selectUsergroupUserCounts = userGroupUserService.selectUsergroupUserCounts(userGroup.getGroup_id());
			//4.更新用户组用户数量
			groupService.updateGroupCount(selectUsergroupUserCounts,userGroup.getGroup_id());
		}
	}

	/** 自动更新设备组设备账号数量 **/
	public static synchronized void AutoUpdateGroupDeviceCounts(GroupService groupService,GroupDeviceAccountService groupDeviceAccountService){
		//1.先查询设备组的id
		List<DeviceGroup> deviceGroups = groupService.selectNameAndIdTop1();
		//2.遍历设备组id
		for (DeviceGroup deviceGroup:deviceGroups) {
			//3.查询设备组设备账号数量
			int selectDeviceAccountUserDeviceCounts = groupDeviceAccountService.selectDeviceAccountUserDeviceCounts(deviceGroup.getDgroup_id());
			//4.更新设备组设备账号数量
			groupService.updateGroupCount(selectDeviceAccountUserDeviceCounts,deviceGroup.getDgroup_id());
		}
	}
}
