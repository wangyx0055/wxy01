package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.CmdPolicyGroup;
import com.longersec.blj.domain.DTO.DeviceGroup;
import com.longersec.blj.domain.DTO.UserGroup;

public interface CmdPolicyGroupDao {

    public boolean editCmdPolicyUserGroup(@Param("policy_id")Integer policy_id,@Param("usergroup")List<Integer> usergroup);
	
	public boolean editCmdPolicyDeviceGroup(@Param("policy_id")Integer policy_id,@Param("devicegroup")List<Integer> devicegroup);

	public boolean addCmdPolicyUserGroup(@Param("policy_id") Integer policy_id,@Param("usergroup") List<Integer> usergroup);
	
	public boolean addCmdPolicyDeviceGroup(@Param("policy_id") Integer policy_id,@Param("devicegroup") List<Integer> deicegroup);

	public boolean delCmdPolicyGroup(List<String> ids);

	public List<Object> findAll(@Param("cmdPolicyGroup")CmdPolicyGroup cmdPolicyGroup, @Param("page_start")int page_start, @Param("page_length")int page_length);

    public List<UserGroup> selectByIdUser(@Param("policy_id") Integer policy_id, @Param("type") int type);
	
	public List<DeviceGroup> selectBydIdDevice(@Param("policy_id") Integer policy_id, @Param("type") int type);
	
	Boolean deleteBypolicy_id(@Param("policy_id")Integer policy_id, @Param("type") int type);
}
