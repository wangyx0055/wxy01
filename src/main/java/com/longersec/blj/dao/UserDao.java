package com.longersec.blj.dao;

import java.util.ArrayList;
import java.util.List;

import com.longersec.blj.domain.DTO.Users;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.User;

public interface UserDao {

	boolean editUser(User user);

	boolean addUser(User user);

	boolean delUser(List<Integer> ids);

	List<Object> findAll(@Param("user")User user, @Param("page_start")int page_start, @Param("page_length")int page_length,@Param("depart_ids") List<Integer> depart_ids);

	int selectOldDepartment(@Param("id") int id);

	boolean deleteUselessUser(@Param("department") int department);
		
	List<Users> selectNameAndId(@Param("id")Integer id, @Param("page_start")int page_start, @Param("page_length")int page_length);

	User checkLogin(String username);
    
    User getUserByID(String id);
    
    List<User> checkName();
    
    boolean editName(User user) ;
    
    User checkUsername(@Param("username")String username, @Param("id")Integer id) ;

    User checkEmail(@Param("email")String email, @Param("id")Integer id) ;
    
    User checkMobile(@Param("mobile")String mobile, @Param("id")Integer id) ;

    Boolean editstatus(User user);

    List<User> selectAll(@Param("id") int id);
    
    int total();

    User checkUser(Integer id);

    boolean insertMore(User user);

    boolean editUserList(User user);

    User checkADUsername(@Param("username") String username);
}
