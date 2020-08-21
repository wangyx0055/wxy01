package com.longersec.blj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ApppubAccountDao;
import com.longersec.blj.domain.ApppubAccount;
import com.longersec.blj.service.ApppubAccountService;


@Transactional
@Service
public class ApppubAccountServiceImpl implements ApppubAccountService{

	@Autowired
	private ApppubAccountDao ApppubAccountDao;

	@Override
	public boolean editApppubAccount(ApppubAccount apppubAccount) {
		// TODO Auto-generated method stub
		return this.ApppubAccountDao.editApppubAccount(apppubAccount);
	}

	@Override
	public boolean addApppubAccount(ApppubAccount apppubAccount) {
		// TODO Auto-generated method stub
		return this.ApppubAccountDao.addApppubAccount(apppubAccount);
	}

	@Override
	public boolean delApppubAccount(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ApppubAccountDao.delApppubAccount(ids);
	}

	@Override
	public List<Object> findAll(String sname,Integer type,ApppubAccount apppubAccount, int page_start, int page_length) {
		return ApppubAccountDao.findAll(sname,type,apppubAccount, page_start, page_length);
	}

	@Override
	public List<Object> queryByappserver(Integer apppub_server_id, String sname,Integer type, ApppubAccount apppubAccount, int page_start, int page_length) {
		return ApppubAccountDao.queryByappserver(apppub_server_id,sname,type,apppubAccount, page_start, page_length);
	}
	
	@Override
	public List<Object> getApppubAccountByPolicies(Integer userid, ArrayList<Integer> policy_ids, HashMap<String, Object> where, int page_start, int page_length) {
		// TODO Auto-generated method stub
		return ApppubAccountDao.getApppubAccountByPolicies(userid, policy_ids, where, page_start, page_length);
	}

	@Override
	public List<Object> getApppubAccount(ApppubAccount apppubAccount, int page_start, int page_length) {
		// TODO Auto-generated method stub
		return ApppubAccountDao.getApppubAccount(apppubAccount, page_start, page_length);
	}

	@Override
	public int total() {
		// TODO Auto-generated method stub
		return ApppubAccountDao.total();
	}

	@Override
	public List<Map<String, Integer>> totalByProgram() {
		// TODO Auto-generated method stub
		return ApppubAccountDao.totalByProgram();
	}

	@Override
	public int checkName(Integer id, String name) {
		return ApppubAccountDao.checkName(id,name);
	}


	@Override
	public String selectName(Integer id, String name) {
		return ApppubAccountDao.selectName(id,name);
	}

	@Override
	public ApppubAccount getById(Integer id) {
		// TODO Auto-generated method stub
		return ApppubAccountDao.getById(id);
	}

	@Override
	public boolean insertMore(ApppubAccount apppubAccount) {
		return ApppubAccountDao.insertMore(apppubAccount);
	}

	@Override
	public boolean editApppubAccountList(ArrayList<ApppubAccount> update_apppubAccounts) {
		return ApppubAccountDao.editApppubAccountList(update_apppubAccounts);
	}

	@Override
	public ApppubAccount getApppubAccountByName(String name) {
		return ApppubAccountDao.getApppubAccountByName(name);
	}
}
