package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ResourceUser;

public interface ResourceUserDao {

	public boolean editResourceUser(ResourceUser resourceUser);

	public boolean addResourceUser(ResourceUser resourceUser);

	public boolean delResourceUser(List<Integer> ids);

	public List<Object> findAll(@Param("resourceUser")ResourceUser resourceUser, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
