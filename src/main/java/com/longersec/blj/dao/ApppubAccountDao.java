package com.longersec.blj.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.longersec.blj.domain.ApppubServer;
import com.longersec.blj.domain.User;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ApppubAccount;
import com.longersec.blj.domain.DeviceRecord;

public interface ApppubAccountDao {

	public boolean editApppubAccount(ApppubAccount apppubAccount);
	public boolean addApppubAccount(ApppubAccount apppubAccount);

	public boolean delApppubAccount(List<Integer> ids);

	public List<Object> findAll(@Param("sname") String sname,@Param("type") Integer type,@Param("apppubAccount")ApppubAccount apppubAccount, @Param("page_start")int page_start, @Param("page_length")int page_length,@Param("depart_ids") List<Integer> depart_ids);

    public List<Object> queryByappserver(@Param("apppub_server_id")Integer apppub_server_id,@Param("sname")String sname,@Param("type")Integer type, @Param("apppubAccount")ApppubAccount apppubAccount, @Param("page_start")int page_start, @Param("page_length")int page_length);
    
    public List<Object> getApppubAccountByPolicies(@Param("userid")Integer userid, @Param("policy_ids")ArrayList<Integer> policy_ids, @Param("where")HashMap<String, Object> where, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public List<Object> getApppubAccount(@Param("apppubAccount")ApppubAccount apppubAccount, @Param("page_start")int page_start, @Param("page_length")int page_length);
     
    public int total();
    
    public List<Map<String, Integer>> totalByProgram();

	public int checkName( @Param("id")Integer id,  @Param("name")String name);

	public String selectName(@Param("id") Integer id,@Param("name") String name);

	public ApppubAccount getById(@Param("id") Integer id);

	public List<ApppubAccount> selectAll();

	boolean insertMore(ApppubAccount apppubAccount);

	boolean editApppubAccountList(@Param("ArrayList")ArrayList<ApppubAccount> update_apppubAccounts);

	public  ApppubAccount getApppubAccountByName (@Param("name") String name);

}
