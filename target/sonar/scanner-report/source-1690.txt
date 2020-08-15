package com.longersec.blj.utils;

import com.longersec.blj.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author wxy
 * @description 定时删除无用信息
 * @data 2020/8/4
 */
@Component
public class DeleteDepartmentQuartz {
	@Autowired
	private DepartmentService departmentService;

	/** 每天9点执行定时清理无用部门信息 **/
	@Scheduled(cron = "0 46 15 * * ?")
	public void deleteDepartment() {
		//删除无用的部门
		while (true) {
			int selectUselessDepartments = departmentService.selectUselessDepartments();
			if (selectUselessDepartments > 0) {
				boolean deleteUselessDepartments = departmentService.deleteUselessDepartments();
			} else {
				break ;
			}
		}
		//自动更新部门用户和设备数量
		UpdateDepartmentCount.AutoUpdateDepartmentDeviceCounts(departmentService);
		UpdateDepartmentCount.AutoUpdateDepartmentUserCounts(departmentService);
		System.out.println("执行完成");
	}
}
