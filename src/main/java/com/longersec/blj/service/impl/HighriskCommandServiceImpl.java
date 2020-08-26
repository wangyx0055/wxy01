package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.HighriskCommandDao;
import com.longersec.blj.domain.HighriskCommand;
import com.longersec.blj.service.HighriskCommandService;


@Service
@Transactional
public class HighriskCommandServiceImpl implements HighriskCommandService{

	@Autowired
	private HighriskCommandDao HighriskCommandDao;

	@Override
	public boolean editHighriskCommand(HighriskCommand highriskCommand) {
		// TODO Auto-generated method stub
		return this.HighriskCommandDao.editHighriskCommand(highriskCommand);
	}

	@Override
	public boolean addHighriskCommand(HighriskCommand highriskCommand) {
		// TODO Auto-generated method stub
		return this.HighriskCommandDao.addHighriskCommand(highriskCommand);
	}

	@Override
	public boolean delHighriskCommand(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.HighriskCommandDao.delHighriskCommand(ids);
	}

	@Override
	public List<Object> findAll(HighriskCommand highriskCommand, int page_start, int page_length) {
		return HighriskCommandDao.findAll(highriskCommand, page_start, page_length);
	}

	@Override
	public HighriskCommand getById(Integer id) {
		return HighriskCommandDao.getById(id);
	}

}
