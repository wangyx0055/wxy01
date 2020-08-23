package com.longersec.blj.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ApppubServerDao;
import com.longersec.blj.domain.ApppubServer;
import com.longersec.blj.service.ApppubServerService;


@Transactional
@Service
public class ApppubServerServiceImpl implements ApppubServerService{

	@Autowired
	private ApppubServerDao ApppubServerDao;

	@Override
	public boolean editApppubServer(ApppubServer apppubServer) {
		// TODO Auto-generated method stub
		return this.ApppubServerDao.editApppubServer(apppubServer);
	}

	@Override
	public boolean addApppubServer(ApppubServer apppubServer) {
		// TODO Auto-generated method stub
		return this.ApppubServerDao.addApppubServer(apppubServer);
	}

	@Override
	public boolean delApppubServer(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ApppubServerDao.delApppubServer(ids);
	}

	@Override
	public boolean delUselessApppubServer(Integer id) {
		return ApppubServerDao.delUselessApppubServer(id);
	}

	@Override
	public List<Object> findAll(ApppubServer apppubServer,String sname,Integer type, int page_start, int page_length,List<Integer> depart_ids) {
		return ApppubServerDao.findAll(apppubServer,sname,type, page_start, page_length,depart_ids);
	}

	@Override
	public String selectName(Integer id,String name) {
		return ApppubServerDao.selectName(id,name);
	}

	@Override
	public ApppubServer getById(Integer id) {
		// TODO Auto-generated method stub
		return ApppubServerDao.getById(id);
	}

	@Override
	public ApppubServer checkname(Integer id, String name) {
		return ApppubServerDao.checkname(id,name);
	}

	@Override
	public ApppubServer checkip(String ip, Integer id) {
		// TODO Auto-generated method stub
		return ApppubServerDao.checkip(ip, id);
	}

	@Override
	public ApppubServer getApppubServerByName(String name) {
		return ApppubServerDao.getApppubServerByName(name);
	}

	@Override
	public boolean insertMore(ArrayList<ApppubServer> listApppubservers) {
		// TODO Auto-generated method stub
		return ApppubServerDao.insertMore(listApppubservers);
	}

	@Override
	public boolean editApppubServerList(ArrayList<ApppubServer> updatelistApppubservers) {
		// TODO Auto-generated method stub
		return ApppubServerDao.editApppubServerList(updatelistApppubservers);
	}

}
