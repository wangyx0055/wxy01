package com.longersec.blj.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ApppubAccount;

public interface ApppubAccountDao {

	boolean editApppubAccount(ApppubAccount apppubAccount);
	boolean addApppubAccount(ApppubAccount apppubAccount);

	boolean delApppubAccount(List<Integer> ids);

	List<Object> findAll(@Param("sname") String sname,@Param("type") Integer type,@Param("apppubAccount")ApppubAccount apppubAccount, @Param("page_start")int page_start, @Param("page_length")int page_length,@Param("depart_ids") List<Integer> depart_ids);

	List<Object> queryByappserver(@Param("apppub_server_id")Integer apppub_server_id,@Param("sname")String sname,@Param("type")Integer type, @Param("apppubAccount")ApppubAccount apppubAccount, @Param("page_start")int page_start, @Param("page_length")int page_length);

	List<Object> getApppubAccountByPolicies(@Param("userid")Integer userid, @Param("policy_ids")ArrayList<Integer> policy_ids, @Param("where")HashMap<String, Object> where, @Param("page_start")int page_start, @Param("page_length")int page_length);

	List<Object> getApppubAccount(@Param("apppubAccount")ApppubAccount apppubAccount, @Param("page_start")int page_start, @Param("page_length")int page_length);

	int total();

	List<Map<String, Integer>> totalByProgram();

	int checkName( @Param("id")Integer id,  @Param("name")String name);

	String selectName(@Param("id") Integer id,@Param("name") String name);

	ApppubAccount getById(@Param("id") Integer id);

	List<ApppubAccount> selectAll(@Param("id") Integer id);

	boolean insertMore(ApppubAccount apppubAccount);

	boolean editApppubAccountList(@Param("ArrayList")ArrayList<ApppubAccount> update_apppubAccounts);

	ApppubAccount getApppubAccountByName (@Param("name") String name);

}
