package com.longersec.blj.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.MenuDao;
import com.longersec.blj.domain.Menu;
import com.longersec.blj.service.MenuService;


@Transactional
@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuDao MenuDao;

	@Override
	public boolean editMenu(Menu menu) {
		// TODO Auto-generated method stub
		return this.MenuDao.editMenu(menu);
	}

	@Override
	public boolean addMenu(Menu menu) {
		// TODO Auto-generated method stub
		return this.MenuDao.addMenu(menu);
	}

	@Override
	public boolean delMenu(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.MenuDao.delMenu(ids);
	}

	@Override
	public List<Object> findAll(Menu menu, int page_start, int page_length) {
//		return MenuDao.findAll(menu, page_start, page_length);
		return null;
	}

}
