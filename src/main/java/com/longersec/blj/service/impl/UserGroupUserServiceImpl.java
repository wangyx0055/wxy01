package com.longersec.blj.service.impl;

import com.longersec.blj.dao.UserGroupUserDao;
import com.longersec.blj.domain.DTO.Users;
import com.longersec.blj.service.UserGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserGroupUserServiceImpl implements UserGroupUserService {
	@Autowired
	private UserGroupUserDao userGroupUserDao;

	@Override
	public boolean editUsergroupUser(Integer group_id, List<Integer> users) {
		return userGroupUserDao.editUsergroupUser(group_id, users);
	}

	@Override
	public List<Users> selectById(Integer group_id) {
		return userGroupUserDao.selectById(group_id);
	}

	@Override
	public Boolean deleteByUserGroupUserId(Integer group_id) {
		return userGroupUserDao.deleteByUserGroupUserId(group_id);
	}

	@Override
	public int selectUsergroupUserCounts(int group_id) {
		return userGroupUserDao.selectUsergroupUserCounts(group_id);
	}
}
