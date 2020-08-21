package com.longersec.blj.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.longersec.blj.domain.ApppubAccount;
import org.apache.ibatis.annotations.Param;

public interface ApppubAccountService {

	public boolean addApppubAccount(ApppubAccount apppubAccount);

	public boolean editApppubAccount(ApppubAccount apppubAccount);

	public boolean delApppubAccount(List<Integer> ids);

	public List<Object> findAll(String sname,Integer type,ApppubAccount apppubAccount, int page_start, int page_length);

	public List<Object> queryByappserver(Integer apppub_program_id,String sname,Integer type, ApppubAccount apppubAccount, int page_start, int page_length);
    
    public List<Object> getApppubAccountByPolicies(Integer userid, ArrayList<Integer> policy_ids, HashMap<String, Object> where, int page_start, int page_length);
    
    public List<Object> getApppubAccount(ApppubAccount apppubAccount, int page_start, int page_length);
	
	public int total();
	
	public List<Map<String, Integer>> totalByProgram();

	public int checkName(Integer id, String name);

	public String selectName(Integer id, String name);

	public ApppubAccount getById(Integer id);

	boolean insertMore(ApppubAccount apppubAccount);

	boolean editApppubAccountList(ArrayList<ApppubAccount> update_apppubAccounts);

	public  ApppubAccount getApppubAccountByName (String name);
}

