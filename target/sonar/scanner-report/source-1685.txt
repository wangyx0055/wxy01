package com.longersec.blj.service;

import com.longersec.blj.domain.DTO.Users;
import com.longersec.blj.domain.UserGroupUser;

import java.util.List;
public interface UserGroupUserService {
	public boolean editUsergroupUser(Integer group_id,List<Integer> users);

	public boolean addUsergroupUser(Integer group_id,List<Integer> users);


	public List<Object> findAll(UserGroupUser userGroupUser, int page_start, int page_length);

	public List<Users> selectById(Integer group_id);

	Boolean deleteByUserGroupUserId(Integer group_id);

	/** 用户分组关联用户成员数 **/
	int selectUsergroupUserCounts(int group_id);

}

