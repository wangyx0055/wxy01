package com.longersec.blj.dao;

import com.longersec.blj.domain.DTO.Users;
import com.longersec.blj.domain.UserGroupUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserGroupUserDao {
	boolean editUsergroupUser(@Param("group_id")Integer policy_id, @Param("users") List<Integer> users);

	List<Users> selectById(@Param("group_id")Integer group_id);

	Boolean deleteByUserGroupUserId(@Param("group_id")Integer group_id);

	/** 用户分组关联用户成员数 **/
	int selectUsergroupUserCounts(@Param("group_id") int group_id);
}