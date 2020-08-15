package com.longersec.blj.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.longersec.blj.dao.GroupDao;
import com.longersec.blj.dao.RoleDao;
import com.longersec.blj.domain.DTO.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.UserDao;
import com.longersec.blj.domain.User;
import com.longersec.blj.service.UserService;


@Transactional
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao UserDao;

	@Override
	public boolean editUser(User user) {
		return this.UserDao.editUser(user);
	}

	@Override
	public boolean addUser(User user) {
		return this.UserDao.addUser(user);

	}

	@Override
	public boolean delUser(List<Integer> ids)
	{
		// TODO Auto-generated method stub
		return this.UserDao.delUser(ids);
	}

	@Override
	public List<Object> findAll(User user, int page_start, int page_length,List<Integer> depart_ids) {
		return UserDao.findAll(user, page_start, page_length ,depart_ids);
	}

	@Override
	public boolean deleteUselessUser(int department) {
		return UserDao.deleteUselessUser(department);
	}

	@Override
	public int selectOldDepartment(int id) {
		return UserDao.selectOldDepartment(id);
	}

	@Override
	public List<Users> selectNameAndId() {
		return UserDao.selectNameAndId();
	}

	@Override
	public User checkLogin(String username) {
		User user = UserDao.checkLogin(username);
		return user;
	}

	@Override
	public List<User> checkName() {
		// TODO Auto-generated method stub
		return UserDao.checkName();
	}

	@Override
	public User getUserByID(String id) {
		// TODO Auto-generated method stub
		return UserDao.getUserByID(id);
	}

	@Override
	public Boolean editName(User user) {
		// TODO Auto-generated method stub
		return UserDao.editName(user);
	}

	@Override
	public User checkUsername(String username, Integer id) {
		// TODO Auto-generated method stub
		return UserDao.checkUsername(username, id);
	}

	@Override
	public User checkEmail(String email, Integer id) {
		// TODO Auto-generated method stub
		return UserDao.checkEmail(email, id);
	}

	@Override
	public User checkMobile(String mobile, Integer id) {
		// TODO Auto-generated method stub
		return UserDao.checkMobile(mobile, id);
	}

	@Override
	public Boolean editstatus(User user) {
		return this.UserDao.editstatus(user);
	}

	@Override
	public User checkUser(Integer id) {
		return this.UserDao.checkUser(id);
	}

	@Override
	public int total() {
		return UserDao.total();
	}

	@Override
	public boolean insertMore(User user) {
		return UserDao.insertMore(user);
	}

	@Override
	public boolean editUserList(ArrayList<User> update_users) {
		return UserDao.editUserList(update_users);
	}

	@Override
	public ArrayList<Users> finduserGroup(int i) {
		return UserDao.finduserGroup(i);
	}
}
