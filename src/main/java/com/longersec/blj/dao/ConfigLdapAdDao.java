package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ConfigLdapAd;

public interface ConfigLdapAdDao {

	public boolean editConfigLdapAd(ConfigLdapAd configLdapAd);

	public boolean addConfigLdapAd(ConfigLdapAd configLdapAd);

	public boolean delConfigLdapAd(List<Integer> ids);

	public List<Object> findAll(@Param("configLdapAd")ConfigLdapAd configLdapAd, @Param("page_start")int page_start, @Param("page_length")int page_length);

	public ConfigLdapAd getConfigLdapById(@Param("id") int id);
}
