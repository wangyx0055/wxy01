package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ApppubResourceAccount;

public interface ApppubResourceAccountDao {

	public boolean editApppubResourceAccount(ApppubResourceAccount apppubResourceAccount);

	public boolean addApppubResourceAccount(ApppubResourceAccount apppubResourceAccount);

	public boolean delApppubResourceAccount(List<Integer> ids);

	public List<Object> findAll(@Param("apppubResourceAccount")ApppubResourceAccount apppubResourceAccount, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
