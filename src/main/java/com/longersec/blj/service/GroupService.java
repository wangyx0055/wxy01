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

	public boolean addGroup(Group group);
	
	public boolean editGroup(Group group);

	public boolean delGroup(List<Integer> ids);

	public List<Object> findAll(Group group, int page_start, int page_length, Integer id);
	
	public List<UserGroup> selectNameAndId();

	public List<DeviceGroup> selectNameAnddId();

	public Group checkname(Group group);
	
	public List<UserGroup> select_two(int type);

	public ArrayList<Group> listByType(Integer type);

    boolean insertMore(ArrayList<Group> listGroup);

	Group selectByname( String name,Integer type);

	boolean editGroupList(ArrayList<Group> updatelistGroup);

    public String selectById(Integer groupid, int type);

	boolean updateGroupCount(int count, int group_id);
}
