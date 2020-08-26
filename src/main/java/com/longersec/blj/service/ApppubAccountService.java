package com.longersec.blj.service;

import com.longersec.blj.domain.ApppubAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ApppubAccountService {

	boolean addApppubAccount(ApppubAccount apppubAccount);

	boolean editApppubAccount(ApppubAccount apppubAccount);

	boolean delApppubAccount(List<Integer> ids);

	List<Object> findAll(String sname,Integer type,ApppubAccount apppubAccount, int page_start, int page_length,List<Integer> depart_ids);

	List<Object> queryByappserver(Integer apppub_program_id,String sname,Integer type, ApppubAccount apppubAccount, int page_start, int page_length);

	List<Object> getApppubAccountByPolicies(Integer userid, ArrayList<Integer> policy_ids, HashMap<String, Object> where, int page_start, int page_length);

	List<Object> getApppubAccount(ApppubAccount apppubAccount, int page_start, int page_length);

	int total();

	List<Map<String, Integer>> totalByProgram();

	int checkName(Integer id, String name);

	String selectName(Integer id, String name);

	ApppubAccount getById(Integer id);

	boolean insertMore(ApppubAccount apppubAccount);

	boolean editApppubAccountList(ArrayList<ApppubAccount> update_apppubAccounts);

	ApppubAccount getApppubAccountByName (String name);
}

