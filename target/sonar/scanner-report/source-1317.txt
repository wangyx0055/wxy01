package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.Menu;

public interface MenuDao {

	public boolean editMenu(Menu menu);

	public boolean addMenu(Menu menu);

	public boolean delMenu(List<Integer> ids);

	public List<String> findSources(@Param("id")Integer id);

}
