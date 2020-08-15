package com.longersec.blj.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.longersec.blj.domain.User;
import com.longersec.blj.domain.DTO.Users;

public interface UserService {

	public boolean addUser(User user);

	public boolean editUser(User user);

	public boolean delUser(List<Integer> ids);

	public List<Object> findAll(User user, int page_start, int page_length,List<Integer> depart_ids);

	boolean deleteUselessUser(int department);

	int selectOldDepartment(int id);

	public List<Users> selectNameAndId();

    public User checkLogin(String username);
    
    public User getUserByID(String id);
    
    public List<User> checkName();
    
    public Boolean editName(User user);

    public User checkUsername(String username, Integer id);

    public User checkEmail(String email, Integer id);

    public User checkMobile(String mobile, Integer id);

    public Boolean editstatus(User user);
    
    public int total();

    public User checkUser(Integer id);

    boolean insertMore(User user);

    boolean editUserList(ArrayList<User> update_users);

    ArrayList<Users> finduserGroup(int i);
}
