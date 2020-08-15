package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ApppubResourceUser;

public interface ApppubResourceUserDao {

	public boolean editApppubResourceUser(ApppubResourceUser apppubResourceUser);

	public boolean addApppubResourceUser(ApppubResourceUser apppubResourceUser);

	public boolean delApppubResourceUser(List<Integer> ids);

	public List<Object> findAll(@Param("apppubResourceUser")ApppubResourceUser apppubResourceUser, @Param("page_start")int page_start, @Param("page_length")int page_length);

}
