package com.longersec.blj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;
import com.longersec.blj.domain.Group;
import com.longersec.blj.domain.User;
import org.apache.ibatis.annotations.Param;

public interface GroupService {

	boolean addGroup(Group group);
	
	boolean editGroup(Group group);

	boolean delGroup(List<Integer> ids);

	List<Object> findAll(Group group, int page_start, int page_length, Integer id);
	
	List<UserGroup> selectNameAndId(Integer id, int page_start, int page_length);

	List<UserGroup> selectNameAndIdTop();

	List<DeviceGroup> selectNameAndIdTop1();

	List<DeviceGroup> selectNameAnddId(Integer id,int page_start, int page_length);

	Group checkname(Group group);

	ArrayList<Group> listByType(Integer type,Integer id);

    boolean insertMore(Group group);

	Group selectByname( String name,Integer type);

	boolean editGroupList(Group group);

	String selectById(Integer groupid, int type);

	boolean updateGroupCount(int count, int group_id);
}

