package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ResourceHostAccount;

public interface ResourceHostAccountDao {

	public boolean editResourceHostAccount(ResourceHostAccount resourceHostAccount);

	public boolean addResourceHostAccount(ResourceHostAccount resourceHostAccount);

	public boolean delResourceHostAccount(List<Integer> ids);

	public List<Object> findAll(@Param("resourceHostAccount")ResourceHostAccount resourceHostAccount, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
