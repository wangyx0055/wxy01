package com.longersec.blj.dao;

import java.util.ArrayList;
import java.util.List;

import com.longersec.blj.domain.DTO.Users;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.User;

public interface UserDao {

	public boolean editUser(User user);

	public boolean addUser(User user);

	public boolean delUser(List<Integer> ids);

	public List<Object> findAll(@Param("user")User user, @Param("page_start")int page_start, @Param("page_length")int page_length,@Param("depart_ids") List<Integer> depart_ids);

	int selectOldDepartment(@Param("id") int id);

	boolean deleteUselessUser(@Param("department") int department);
		
	public List<Users> selectNameAndId(@Param("id")Integer id);

    public User checkLogin(String username);
    
    public User getUserByID(String id);
    
    public List<User> checkName();
    
    public boolean editName(User user) ;
    
    public User checkUsername(@Param("username")String username, @Param("id")Integer id) ;
    
    public User checkEmail(@Param("email")String email, @Param("id")Integer id) ;
    
    public User checkMobile(@Param("mobile")String mobile, @Param("id")Integer id) ;

    public Boolean editstatus(User user);

    public List<User> selectAll();
    
    public int total();

    public int getCounts(int group_id);

    public User checkUser(Integer id);

    boolean insertMore(User user);

    boolean editUserList(User user);

    ArrayList<Users> finduserGroup(int i);

    public User checkADUsername(@Param("username") String username);
}
