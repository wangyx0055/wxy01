package com.longersec.blj.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ProtocolDao;
import com.longersec.blj.domain.DeviceAccount;
import com.longersec.blj.domain.Protocol;
import com.longersec.blj.service.ProtocolService;


@Transactional
@Service
public class ProtocolServiceImpl implements ProtocolService{

	@Autowired
	private ProtocolDao ProtocolDao;

	@Override
	public boolean editProtocol(Protocol protocol) {
		// TODO Auto-generated method stub
		return this.ProtocolDao.editProtocol(protocol);
	}

	@Override
	public boolean addProtocol(Protocol protocol) {
		// TODO Auto-generated method stub
		return this.ProtocolDao.addProtocol(protocol);
	}

	@Override
	public boolean delProtocol(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ProtocolDao.delProtocol(ids);
	}

	@Override
	public List<Object> findAll(Protocol protocol, int page_start, int page_length) {
		return ProtocolDao.findAll(protocol, page_start, page_length);
	}

	@Override
	public String getById(Integer id) {
		// TODO Auto-generated method stub
		return ProtocolDao.getById(id).getName();
	}

	@Override
	public ArrayList<Protocol> listType() {
		return ProtocolDao.listType();
	}

}
