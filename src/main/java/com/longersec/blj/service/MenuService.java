package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.Menu;

public interface MenuService {

	public boolean addMenu(Menu menu);

	public boolean editMenu(Menu menu);

	public boolean delMenu(List<Integer> ids);

	public List<Object> findAll(Menu menu, int page_start, int page_length);

}

