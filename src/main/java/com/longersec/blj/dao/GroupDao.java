package com.longersec.blj.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;

import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.Group;
import com.longersec.blj.domain.User;

public interface GroupDao {

	public boolean editGroup(Group group);

	public boolean addGroup(Group group);

	public boolean delGroup(List<Integer> ids);

	public List<Object> findAll(@Param("group")Group group, @Param("page_start")int page_start, @Param("page_length")int page_length,@Param("id") Integer id);

	boolean updateGroupCount(@Param("count") int count, @Param("group_id") int group_id);
	
	List<UserGroup> selectNameAndId(@Param("id") int id,@Param("page_start")int page_start, @Param("page_length")int page_length);
	
	List<DeviceGroup> selectNameAnddId(@Param("id") int id,@Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<UserGroup> selectNameAndIdTop();

	public List<DeviceGroup> selectNameAndIdTop1();

	Group checkname(@Param("group")Group group);

	List<UserGroup> select_two(@Param("type") int type);

    ArrayList<Group> listByType(@Param("type")Integer type,@Param("id") Integer id);

	String selectById(@Param("groupid") Integer groupid, @Param("type") int type);

    boolean updateCountdown(Integer groupid);

	boolean insertMore(Group group);

	Group selectByname(@Param("name")String name, @Param("type") Integer type);

    boolean editGroupList(Group group);
}
