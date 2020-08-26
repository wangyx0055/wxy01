package com.longersec.blj.service;

import com.longersec.blj.domain.DTO.Users;

import java.util.List;
public interface UserGroupUserService {
	boolean editUsergroupUser(Integer group_id,List<Integer> users);

	List<Users> selectById(Integer group_id);

	Boolean deleteByUserGroupUserId(Integer group_id);

	/** 用户分组关联用户成员数 **/
	int selectUsergroupUserCounts(int group_id);

}

